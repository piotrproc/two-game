package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.update.SupportRequest;

import javax.inject.Named;
import java.util.Set;

public class SupportRequestConsumer implements EventConsumer<SupportRequest> {
    private static final Logger logger = LoggerFactory.getLogger(JoinConsumer.class);

//    private final Set<ChangePredicate> predicates;
//
//    @Inject
//    public SupportRequestConsumer(@Named("SupportRequest") Set<ChangePredicate> predicates) {
//        this.predicates = predicates;
//    }

    @Override
    public void process(SupportRequest event, GameState gameState) {
        logger.debug("@@@got {}", event);

        if(event.getAmount()!=0){
            gameState.addUnit();

        }

//        boolean applicable = predicates.stream().allMatch(predicate -> predicate.applicable(event, gameState));
//
//        logger.debug("change is applicable: {}", applicable);
//        if (applicable) {
            // todo
            // some notification to the user (with event.getAmount())
//        }
    }
}
