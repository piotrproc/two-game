package two.game.logic.consumers;

import two.game.logic.GameState;
import two.game.model.update.UnitUpdate;

public class UnitUpdateConsumer implements EventConsumer<UnitUpdate> {
    @Override
    public void process(UnitUpdate event, GameState gameState) {
        // todo
    }
}
