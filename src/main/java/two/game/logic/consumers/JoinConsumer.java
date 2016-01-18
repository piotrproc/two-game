package two.game.logic.consumers;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.init.JoinMatchRequest;
import two.game.model.init.MatchInfo;

import javax.inject.Named;
import java.util.Set;

public class JoinConsumer implements EventConsumer<JoinMatchRequest> {
    private static final Logger logger = LoggerFactory.getLogger(JoinConsumer.class);

    private final Set<ChangePredicate> predicates;

    @Inject
    public JoinConsumer(@Named("JoinMatchRequest") Set<ChangePredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public void process(JoinMatchRequest event, GameState gameState) {
        logger.debug("got {}", event);
        boolean applicable = predicates.stream().allMatch(predicate -> predicate.applicable(event, gameState));

        logger.debug("change is applicable: {}", applicable);
        if (applicable) {
            /*gameState.getTeamStatuses().stream()
                    .filter(teamStatus -> teamStatus.getTeamId().equals(event.getSelectedTeamId()))
                    .findFirst().ifPresent(teamStatus -> teamStatus.getUserIds().add(event.getUserId()));*/

            try {
                gameState.getMatchInfo().addPlayer(event.getSelectedTeamId(),event.getUserId());
            } catch (MatchInfo.PlayerExistsException | MatchInfo.TeamException e) {
                logger.error(e.getMessage());
            }

            logger.debug("updated game state");
        }
    }
}