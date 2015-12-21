package two.game.logic.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.logic.GameState;
import two.game.model.constant.UnitType;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;
import two.game.model.update.SupportRequest;

public class SupportRequestConsumer implements EventConsumer<SupportRequest> {
    private static final Logger logger = LoggerFactory.getLogger(JoinConsumer.class);

    @Override
    public void process(SupportRequest event, GameState gameState) {
        logger.debug("got {}", event);

        String user = event.getUser();
        TeamStatus teamStatus = gameState.getTeamStatuses().stream()
                .filter(s -> s.getUserIds().contains(user)).findAny().get();
        Integer teamNumber = gameState.getTeamStatuses().indexOf(teamStatus);
        Double resourceAmount = teamStatus.getResourcesAmount();

        UnitStatus unitStatus = gameState.getUnitStatuses().stream()
                .filter(u -> u.getUser().equals(user)).findAny().get();
        UnitType unitType = unitStatus.getType();
        Double unitCost = unitType.getPrices();

        synchronized (gameState){
            if (resourceAmount > unitCost) {
                gameState.addUnit(unitType, user, teamNumber);
                teamStatus.setResourcesAmount(resourceAmount - unitCost);
            }
        }

    }
}
