package two.game.logic.scheduled;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTasksRunner extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasksRunner.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(12);
    private final GameState gameState;

    @Inject
    public ScheduledTasksRunner(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void start() throws Exception {
        scheduler.scheduleWithFixedDelay(new SendStatusTask(gameState, vertx), 0, 3000, TimeUnit.MILLISECONDS);
        scheduler.scheduleWithFixedDelay(new UpdateStateTask(gameState), 0, 3000, TimeUnit.MILLISECONDS);
//        logger.debug("started all scheduled tasks");
    }
}
