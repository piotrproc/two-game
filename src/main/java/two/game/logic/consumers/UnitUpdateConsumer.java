package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
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
            gameState.getUnitStatuses().stream().filter(u -> u.getUnitId() == event.getUnitId()).forEach(u -> {
                u.setPosition(event.getMoveTarget());
                // todo
                // other changes?
            });

            logger.debug("updated game state (unitUpadate)");
        }
    }
}
