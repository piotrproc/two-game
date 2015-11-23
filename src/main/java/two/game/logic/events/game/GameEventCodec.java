package two.game.logic.events.game;

import io.vertx.core.eventbus.MessageCodec;

public interface GameEventCodec extends MessageCodec<GameEvent, GameEvent> {
    @Override
    default byte systemCodecID() {
        return -1;
    }
}
