package two.game.communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicationServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(CommunicationServer.class);

    @Override
    public void start() throws Exception {

        vertx.createHttpServer().websocketHandler(ws -> ws.handler(data -> {
            logger.debug("got request {}", data.toString());
            vertx.eventBus().publish("test-address", new JsonObject(data.toString()));
        })).listen(8077);

        logger.debug("successfully started handler");
    }
}