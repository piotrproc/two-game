package two.game.logic.scheduled;

import com.google.gson.Gson;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.model.status.MatchStatus;

public class SendStatusTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(SendStatusTask.class);
    private final GameState gameState;
    private final Vertx vertx;

    public SendStatusTask(GameState gameState, Vertx vertx) {
        this.gameState = gameState;
        this.vertx = vertx;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        MatchStatus status = new MatchStatus();
        status.setAttackEvents(gameState.getAttackEvents());
        status.setTeamStatuses(gameState.getTeamStatuses());
        status.setMissileStatuses(gameState.getMissileStatuses());
        status.setUnitStatuses(gameState.getUnitStatuses());
        status.setSequenceId(gameState.getUpdateSequenceId());
        vertx.eventBus().publish("MatchStatus", new JsonObject(gson.toJson(status)));
//        logger.debug("published {}", status);
    }
}
