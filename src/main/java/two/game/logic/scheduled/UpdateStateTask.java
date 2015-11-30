package two.game.logic.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;

public class UpdateStateTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStateTask.class);
    private final GameState gameState;

    public UpdateStateTask(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
        //todo: implement
        //logger.debug("updated state to {}", gameState);
    }
}
