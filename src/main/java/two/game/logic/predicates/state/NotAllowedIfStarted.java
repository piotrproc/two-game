package two.game.logic.predicates.state;

import two.game.logic.GameState;

public class NotAllowedIfStarted extends StateBasedChangePredicate {
    @Override
    public boolean applicable(GameState state) {
        return !state.isStarted();
    }
}
