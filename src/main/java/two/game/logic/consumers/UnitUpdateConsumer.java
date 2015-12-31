package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.config.ControlPointConfig;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.ControlPoint;
import two.game.model.Point;
import two.game.model.status.AttackEvent;
import two.game.model.status.MissileStatus;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;
import two.game.model.update.UnitUpdate;

import javax.inject.Named;
import java.util.Set;

public class UnitUpdateConsumer implements EventConsumer<UnitUpdate> {
    private static final Logger logger = LoggerFactory.getLogger(JoinConsumer.class);

    private final Set<ChangePredicate> predicates;

    @Inject
    public UnitUpdateConsumer(@Named("UnitUpdate") Set<ChangePredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public void process(UnitUpdate event, GameState gameState) {
        logger.debug(gameState.toString());
        logger.debug("got {}", event);
        boolean applicable = predicates.stream()
                .allMatch(predicate -> predicate.applicable(event, gameState));

        logger.debug("change is applicable: {}", applicable);
        if (applicable) {

            synchronized (gameState) {
                updateMoveTargets(event, gameState);
                markControlPoints(event, gameState);
                //toDo if we change order by putting markControlPoints at the end, control points fail.
                addAttacks(event, gameState);
                addMissiles(event, gameState);
            }

            logger.debug("updated game state (unitUpadate)");
        }
    }

    private void addMissiles(UnitUpdate event, GameState gameState) {
        UnitStatus unit = gameState.getUnitStatuses().stream()
                .filter(status -> status.getUnitId().equals(event.getUnitId()))
                .findAny().get();
        event.getMissileLaunches().stream()
                .map(missileLaunch -> new MissileStatus(missileLaunch.getMissileId(), unit.getPosition(), missileLaunch.getTarget()))
                .forEach(gameState::addMissile);
    }

    private void addAttacks(UnitUpdate event, GameState gameState) {
        event.getAttacks().
                forEach(unitAttack -> gameState.addAttack(new AttackEvent(event.getUnitId(), unitAttack.getTargetUnitId())));
    }

    private void updateMoveTargets(UnitUpdate event, GameState gameState) {
        gameState.getUnitStatuses().stream()
                .filter(u -> u.getUnitId().equals(event.getUnitId()))
                .forEach(u -> u.setTargetPosition(event.getMoveTarget()));
    }

    public void markControlPoints(UnitUpdate event, GameState gameState) {
        Point targetPosition = event.getMoveTarget();
        Long unitId = event.getUnitId();

        if(targetPosition == null){
            return;
        }

        logger.debug("control point is on the field: " + ControlPointConfig.controlPointIsOnTheField(targetPosition));
        if (ControlPointConfig.controlPointIsOnTheField(targetPosition)) {
            UnitStatus unitStatus = gameState.getUnitStatuses().stream()
                    .filter(u -> u.getUnitId().equals(unitId)).findAny().get();
            TeamStatus teamStatus = gameState.getTeamStatuses().stream()
                    .filter(s -> s.getUserIds().contains(unitStatus.getUser())).findAny().get();

            boolean pointIsTakenByOurTeam = teamStatus.getControlPoints().stream()
                    .anyMatch(cp -> cp.isLocated(targetPosition));

            logger.debug("control point was taken by our team: " + pointIsTakenByOurTeam);

            if (!pointIsTakenByOurTeam) {
                TeamStatus oppositeTeamStatus = gameState.getTeamStatuses().stream()
                        .filter(s -> !s.getUserIds().contains(unitStatus.getUser())).findAny().get();

                boolean pointIsTakenByOppositeTeam = oppositeTeamStatus.getControlPoints().stream()
                        .anyMatch(cp -> cp.isLocated(targetPosition));

                logger.debug("control point was taken by opposite team: " + pointIsTakenByOppositeTeam);

                oppositeTeamStatus.getControlPoints().removeIf(cp -> cp.isLocated(targetPosition));
                teamStatus.getControlPoints().add(new ControlPoint(targetPosition));
            }

        }
    }
}
