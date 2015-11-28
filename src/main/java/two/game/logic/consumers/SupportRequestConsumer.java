package two.game.logic.consumers;

import two.game.logic.GameState;
import two.game.model.update.SupportRequest;

public class SupportRequestConsumer implements EventConsumer<SupportRequest> {
    @Override
    public void process(SupportRequest event, GameState gameState) {
        //todo
    }
}
