package two.game.logic.predicates.join;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.init.JoinMatchRequest;

public class UserIdAvailable implements ChangePredicate<JoinMatchRequest> {

    @Override
    public boolean applicable(JoinMatchRequest object, GameState state) {
        return state.getTeamStatuses().stream().allMatch(teamStatus ->
                        teamStatus.getUserIds().stream().noneMatch(userId -> userId.equals(object.getUserId()))
        );
    }
}
