package two.game.logic.predicates.unitupdate;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.status.UnitStatus;
import two.game.model.update.UnitUpdate;

public class UnitUpdatePredicate implements ChangePredicate<UnitUpdate> {

    @Override
    public boolean applicable(UnitUpdate object, GameState state) {
        // todo
        UnitStatus unit = state.getUnitStatuses().stream().filter(s -> s.getUnitId().equals(object.getUnitId())).findAny().get();
        if (unit.getPosition().getX() + 2 >= object.getMoveTarget().getX()  && unit.getPosition().getX() - 2 <= object.getMoveTarget().getX()) {
            if (unit.getPosition().getY() + 2 >= object.getMoveTarget().getY() && unit.getPosition().getY() + 2 <= object.getMoveTarget().getY()) {
                return true;
            }
        }
        return false;
    }

}
