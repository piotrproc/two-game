package two.game.logic;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.events.game.GameEventCodec;

import java.util.Collections;
import java.util.Set;

public class GameEventConsumer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer(Channels.GAME_EVENTS, event -> logger.info("Received game event: " + event));
        getCodecs().forEach(vertx.eventBus()::registerCodec);
    }

    private Set<GameEventCodec> getCodecs(){
        return Collections.emptySet();
    }

}
