package two.game.logic.predicates.state;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;

public abstract class StateBasedChangePredicate implements ChangePredicate {
    @Override
    public boolean applicable(Object object, GameState state) {
        return applicable(state);
    }

    protected abstract boolean applicable(GameState state);
}
