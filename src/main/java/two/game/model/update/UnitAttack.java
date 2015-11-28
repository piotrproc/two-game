package two.game.model.update;

public class UnitAttack {
    private Long targetUnitId;

    public UnitAttack(Long targetUnitId) {
        this.targetUnitId = targetUnitId;
    }

    public UnitAttack() {
    }

    @Override
    public String toString() {
        return "UnitAttack{" +
                "targetUnitId=" + targetUnitId +
                '}';
    }

    public Long getTargetUnitId() {
        return targetUnitId;
    }

    public void setTargetUnitId(Long targetUnitId) {
        this.targetUnitId = targetUnitId;
    }
}
