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
            JsonObject json = new JsonObject(data.toString());
            handle(json);
        })).listen(8077);

        logger.debug("successfully started handler");
    }

    private void handle(JsonObject json) {
        String address = json.getString("type");
        JsonObject message = json.getJsonObject("actualMessage");
        vertx.eventBus().publish(address, message);
        logger.debug("sent to channel '{}' as {}", address, message);
    }
}