package two.game.communication;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.communication.message.RawMessage;

public class CommunicationServer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(CommunicationServer.class);
    private Gson gson = new Gson();

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().websocketHandler(ws -> ws.handler(data -> {
            logger.debug("got request {}", data.toString());
            RawMessage raw = gson.fromJson(data.toString(), RawMessage.class);
            logger.debug("parsed as {}", raw);
            handle(raw);
        })).listen(8077);

        logger.debug("successfully started handler");
    }

    private void handle(RawMessage raw) {
        String address = raw.getType().toString();
        Class messageClass = raw.getType().getMessageClass();
        Object message = gson.fromJson(raw.getActualMessage(), messageClass);
        vertx.eventBus().publish(address, message);
        logger.debug("sent to channel '{}' as {}", address, message);
    }
}