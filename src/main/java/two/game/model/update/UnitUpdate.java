package two.game.model.update;

import java.util.List;

public class UnitUpdate {
    private Long unitId;
    private Double moveDirection;
    private List<MissileLaunch> launches;

    public UnitUpdate() {
    }

    public UnitUpdate(Long unitId, Double moveDirection, List<MissileLaunch> launches) {

        this.unitId = unitId;
        this.moveDirection = moveDirection;
        this.launches = launches;
    }

    public Long getUnitId() {

        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Double getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(Double moveDirection) {
        this.moveDirection = moveDirection;
    }

    public List<MissileLaunch> getLaunches() {
        return launches;
    }

    public void setLaunches(List<MissileLaunch> launches) {
        this.launches = launches;
    }

    @Override
    public String toString() {
        return String.format("UnitUpdate{unitId=%d, moveDirection=%s, launches=%s}", unitId, moveDirection, launches);
    }
}
