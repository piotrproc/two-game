package two.game.logic.consumers;

import two.game.logic.GameState;

public interface EventConsumer<EventType> {
    void process(EventType event, GameState gameState);
}
