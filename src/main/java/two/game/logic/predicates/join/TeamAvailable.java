package two.game.logic.predicates.join;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.init.JoinMatchRequest;

public class TeamAvailable implements ChangePredicate<JoinMatchRequest> {
    @Override
    public boolean applicable(JoinMatchRequest object, GameState state) {
        return state.getTeamStatuses().stream().anyMatch(teamStatus -> teamStatus.getTeamId().equals(object.getSelectedTeamId()));
    }
}
