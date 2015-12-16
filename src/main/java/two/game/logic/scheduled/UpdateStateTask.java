package two.game.logic.scheduled;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.config.ControlPointConfig;
import two.game.logic.GameState;
import two.game.model.Point;

public class UpdateStateTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStateTask.class);
    private final GameState gameState;
    private DateTime lastUpdate;
    private long sumOfMillisElapsed = 0;

    private static final double MOVE_DELTA_PER_MS = 0.1;

    public UpdateStateTask(GameState gameState) {
        this.gameState = gameState;
        this.lastUpdate = DateTime.now();
    }

    @Override
    public void run() {
        DateTime now = DateTime.now();
        long millisElapsed = new Duration(now, lastUpdate).getMillis();

        synchronized (gameState) {
            updateUnitPositions(millisElapsed);
            lastUpdate = now;
            sumOfMillisElapsed = sumOfMillisElapsed + (-millisElapsed); //millisElapsed is negative
            gameState.bumpUpdateSequenceId();

            if(sumOfMillisElapsed > ControlPointConfig.resourcesIntervalInMillis){
                sumOfMillisElapsed = sumOfMillisElapsed - ControlPointConfig.resourcesIntervalInMillis;
                gameState.getTeamStatuses().forEach(team -> team.addResourcesByControlPoints());
            }
        }
    }

    private void updateUnitPositions(long millisElapsed) {
        gameState.getUnitStatuses().forEach(unit -> {
            Point position = unit.getPosition();
            Point target = unit.getTargetPosition();
            double dx = target.getX() - position.getX();
            double dy = target.getY() - position.getY();

            double distanceAllowed = Math.abs(MOVE_DELTA_PER_MS * millisElapsed);
            double scaler = (Math.abs(dx) + Math.abs(dy)) / distanceAllowed;
            scaler = Math.abs(Math.max(1., scaler));

            Point newPosition = new Point(position.getX() + dx / scaler, position.getY() + dy / scaler);
            unit.setPosition(newPosition);
        });
    }
}
