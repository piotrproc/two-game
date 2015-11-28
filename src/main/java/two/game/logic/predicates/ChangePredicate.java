package two.game.logic.predicates;

import two.game.logic.GameState;

public interface ChangePredicate<T> {
    boolean applicable(T object, GameState state);
}
