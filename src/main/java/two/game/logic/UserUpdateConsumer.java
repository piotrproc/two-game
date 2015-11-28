package two.game.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.model.update.UserUpdate;

public class UserUpdateConsumer implements EventConsumer<UserUpdate> {
    private static final Logger logger = LoggerFactory.getLogger(UserUpdateConsumer.class);

    @Override
    public void process(UserUpdate event) {
        logger.debug("got {}", event);
    }
}