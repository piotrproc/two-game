package two.game.logic.predicates.unitupdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.Point;
import two.game.model.constant.MapElement;
import two.game.model.status.UnitStatus;
import two.game.model.update.UnitUpdate;

import java.util.Optional;

public class MovePredicate implements ChangePredicate<UnitUpdate> {
    private static final Logger logger = LoggerFactory.getLogger(MovePredicate.class);

    @Override
    public boolean applicable(UnitUpdate object, GameState state) {
        Optional<UnitStatus> unitStatusOpt = AttackPredicate.getUnitStatus(object.getUnitId(), state);
        if (!unitStatusOpt.isPresent()) {
            return false;
        }
        UnitStatus unitStatus = unitStatusOpt.get();

        if (object.getMoveTarget() == null
                || object.getMoveTarget().equals(unitStatus.getPosition()))
            return true;

        Point target = object.getMoveTarget();
        MapElement targetMapElement = state.getMap().get(
                target.getX().intValue() / 32,
                target.getY().intValue() / 32);

        logger.info("target element {}, unit type {}", targetMapElement, unitStatus.getType());
        if (targetMapElement == null) {
            logger.info("Can not move outside the map");
            return false;
        }

        return true;
    }

}
