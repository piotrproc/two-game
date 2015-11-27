package two.game.model.update;

import two.game.model.Point;

import java.util.List;

public class UnitUpdate {
    private Long unitId;
    private Point moveTarget;
    private List<MissileLaunch> missileLaunches;
    private List<UnitAttack> attacks;
}
