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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttackEvent that = (AttackEvent) o;

        if (sourceUnitId != null ? !sourceUnitId.equals(that.sourceUnitId) : that.sourceUnitId != null) return false;
        return !(targetUnitId != null ? !targetUnitId.equals(that.targetUnitId) : that.targetUnitId != null);

    }

    @Override
    public int hashCode() {
        int result = sourceUnitId != null ? sourceUnitId.hashCode() : 0;
        result = 31 * result + (targetUnitId != null ? targetUnitId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttackEvent{" +
                "sourceUnitId=" + sourceUnitId +
                ", targetUnitId=" + targetUnitId +
                '}';
    }
}
