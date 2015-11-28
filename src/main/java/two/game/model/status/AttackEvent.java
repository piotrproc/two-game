package two.game.model.status;

public class AttackEvent {
    private Long sourceUnitId;
    private Long targetUnitId;

    public AttackEvent(Long sourceUnitId, Long targetUnitId) {
        this.sourceUnitId = sourceUnitId;
        this.targetUnitId = targetUnitId;
    }

    public AttackEvent() {

    }

    public Long getSourceUnitId() {
        return sourceUnitId;
    }

    public void setSourceUnitId(Long sourceUnitId) {
        this.sourceUnitId = sourceUnitId;
    }

    public Long getTargetUnitId() {
        return targetUnitId;
    }

    public void setTargetUnitId(Long targetUnitId) {
        this.targetUnitId = targetUnitId;
    }

    @Override
    public String toString() {
        return "AttackEvent{" +
                "sourceUnitId=" + sourceUnitId +
                ", targetUnitId=" + targetUnitId +
                '}';
    }
}
