package two.game.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.model.init.JoinMatchRequest;

public class JoinRequestConsumer implements EventConsumer<JoinMatchRequest> {
    private static final Logger logger = LoggerFactory.getLogger(JoinRequestConsumer.class);

    @Override
    public void process(JoinMatchRequest event) {
        logger.debug("got {}", event);
    }
}