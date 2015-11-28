package two.game.logic.predicates.userupdate;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.update.UserUpdate;

public class UserSequenceIdNewer implements ChangePredicate<UserUpdate> {

    @Override
    public boolean applicable(UserUpdate object, GameState state) {
        return state.getUserIdToSequenceId().getOrDefault(object.getUserId(), 0L) < object.getUserSequenceId();
    }
}
