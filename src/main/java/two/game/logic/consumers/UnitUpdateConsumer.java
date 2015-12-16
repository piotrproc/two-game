package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.config.ControlPointConfig;
import two.game.config.GameConfig;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.ControlPoint;
import two.game.model.Point;
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
        logger.debug("got {}", event);
        boolean applicable = predicates.stream().allMatch(predicate -> predicate.applicable(event, gameState));

        logger.debug("change is applicable: {}", applicable);
        if (applicable) {

            synchronized (gameState) {
                gameState.getUnitStatuses().stream()
                        .filter(u -> u.getUnitId().equals(event.getUnitId()))
                        .forEach(u -> u.setTargetPosition(event.getMoveTarget()));

                markControlPoints(event, gameState);
            }

//            for (UnitAttack attack : event.getAttacks()) {
//                UnitStatus attackingUnit = unitStatuses.stream().filter(unit -> unit.getUnitId().equals(event.getUnitId())).findFirst().get();
//                UnitStatus attackedUnit = unitStatuses.stream().filter(unit -> unit.getUnitId().equals(attack.getTargetUnitId())).findFirst().get();
//                //todo
//                //unit has no type so there is no way to determine damage (set temporary to 10)
//                attackedUnit.setHealth(attackedUnit.getHealth() - 10);
//            }

            logger.debug("updated game state (unitUpadate)");
        }
    }

    public void markControlPoints(UnitUpdate event, GameState gameState) {
        Point targetPosition = event.getMoveTarget();
        Long unitId = event.getUnitId();

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
