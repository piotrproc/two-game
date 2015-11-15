package two.game;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import two.game.communication.WebSocketServer;
import two.game.logic.TestConsumer;

public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        logger.debug("starting websocket server");
        vertx.deployVerticle(new WebSocketServer());

        logger.debug("starting test consumers");
        vertx.deployVerticle(new TestConsumer());
        vertx.deployVerticle(new TestConsumer());
        vertx.deployVerticle(new TestConsumer());

        logger.debug("all vertices started");
    }

}