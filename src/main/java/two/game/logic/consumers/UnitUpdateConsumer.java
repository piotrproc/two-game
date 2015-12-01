package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.status.UnitStatus;
import two.game.model.update.UnitAttack;
import two.game.model.update.UnitUpdate;

import javax.inject.Named;
import java.util.List;
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
            List<UnitStatus> unitStatuses = gameState.getUnitStatuses();
            gameState.getUnitStatuses().stream().filter(u -> u.getUnitId().equals(event.getUnitId()))
                    .forEach(u -> u.setPosition(event.getMoveTarget()));

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
}
