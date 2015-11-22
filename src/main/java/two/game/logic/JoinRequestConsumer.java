package two.game.logic;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.communication.message.MessageType;
import two.game.model.init.JoinMatchRequest;

public class JoinRequestConsumer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(JoinRequestConsumer.class);

    @Override
    public void start() throws Exception {
        Gson gson = new Gson();

        vertx.eventBus().consumer(MessageType.JOIN_REQUEST.toString(), message -> {
            logger.debug("got {}", message.body());
            JoinMatchRequest object = gson.fromJson(message.body().toString(), JoinMatchRequest.class);
            logger.debug("parsed {}", object);
        });
    }
}