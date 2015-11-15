package two.game.logic;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConsumer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("test-address", message -> {
            logger.debug("got {}", message.body());
        });
    }
}