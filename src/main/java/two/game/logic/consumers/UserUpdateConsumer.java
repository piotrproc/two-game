package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.update.SupportRequest;
import two.game.model.update.UnitUpdate;
import two.game.model.update.UserUpdate;

import javax.inject.Named;
import java.util.Set;

public class UserUpdateConsumer implements EventConsumer<UserUpdate> {
    private static final Logger logger = LoggerFactory.getLogger(UserUpdateConsumer.class);

    private final Set<ChangePredicate> predicates;
    private final EventConsumer<UnitUpdate> unitUpdateConsumer;
    private final EventConsumer<SupportRequest> supportRequestConsumer;

    @Inject
    public UserUpdateConsumer(@Named("UserUpdate") Set<ChangePredicate> predicates, EventConsumer<UnitUpdate> unitUpdateConsumer, EventConsumer<SupportRequest> supportRequestConsumer) {
        this.predicates = predicates;
        this.unitUpdateConsumer = unitUpdateConsumer;
        this.supportRequestConsumer = supportRequestConsumer;
    }

    @Override
    public void process(UserUpdate event, GameState state) {
        logger.debug("got {}", event);

        boolean applicable = predicates.stream().allMatch(predicate -> predicate.applicable(event, state));
        logger.debug("change is applicable: {}", applicable);

        if (applicable) {
            state.getUserIdToSequenceId().put(event.getUserId(), event.getUserSequenceId());
            logger.debug("updated game state with user sequence id ({} -> {})", event.getUserId(), event.getUserSequenceId());

            event.getUnitUpdates().forEach(unitUpdate -> unitUpdateConsumer.process(unitUpdate, state));
            logger.debug("finished updating units");

            supportRequestConsumer.process(event.getRequest(), state);
            logger.debug("finished updating support");
        }
    }
}