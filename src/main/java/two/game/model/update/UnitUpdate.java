package two.game.model.update;

import two.game.model.Point;

import java.util.List;

public class UnitUpdate {
    private Long unitId;
    private Point moveTarget;
    private List<MissileLaunch> missileLaunches;
    private List<UnitAttack> attacks;

    public UnitUpdate() {

    }

    public UnitUpdate(Long unitId, Point moveTarget, List<MissileLaunch> missileLaunches, List<UnitAttack> attacks) {

        this.unitId = unitId;
        this.moveTarget = moveTarget;
        this.missileLaunches = missileLaunches;
        this.attacks = attacks;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Point getMoveTarget() {
        return moveTarget;
    }

    public void setMoveTarget(Point moveTarget) {
        this.moveTarget = moveTarget;
    }

    public List<MissileLaunch> getMissileLaunches() {
        return missileLaunches;
    }

    public void setMissileLaunches(List<MissileLaunch> missileLaunches) {
        this.missileLaunches = missileLaunches;
    }

    public List<UnitAttack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<UnitAttack> attacks) {
        this.attacks = attacks;
    }

    @Override
    public String toString() {
        return "UnitUpdate{" +
                "unitId=" + unitId +
                ", moveTarget=" + moveTarget +
                ", missileLaunches=" + missileLaunches +
                ", attacks=" + attacks +
                '}';
    }
}
