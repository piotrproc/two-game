package two.game.logic;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.communication.message.MessageType;
import two.game.model.init.JoinMatchRequest;
import two.game.model.update.UserUpdate;

public class UserUpdateConsumer extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(UserUpdateConsumer.class);

    @Override
    public void start() throws Exception {
        Gson gson = new Gson();

        vertx.eventBus().consumer(MessageType.USER_UPDATE.toString(), message -> {
            logger.debug("got {}", message.body());
            UserUpdate object = gson.fromJson(message.body().toString(), UserUpdate.class);
            logger.debug("parsed {}", object);
        });
    }
}