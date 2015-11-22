package two.game.model.status;

import java.util.List;

public class MatchStatus {
    private Long sequenceId;
    private List<MissleStatus> missleStatuses;
    private List<UnitStatus> unitStatuses;

    public MatchStatus() {
    }

    public MatchStatus(Long sequenceId, List<MissleStatus> missleStatuses, List<UnitStatus> unitStatuses) {

        this.sequenceId = sequenceId;
        this.missleStatuses = missleStatuses;
        this.unitStatuses = unitStatuses;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public List<MissleStatus> getMissleStatuses() {
        return missleStatuses;
    }

    public void setMissleStatuses(List<MissleStatus> missleStatuses) {
        this.missleStatuses = missleStatuses;
    }

    public List<UnitStatus> getUnitStatuses() {
        return unitStatuses;
    }

    public void setUnitStatuses(List<UnitStatus> unitStatuses) {
        this.unitStatuses = unitStatuses;
    }

    @Override
    public String toString() {
        return String.format("MatchStatus{sequenceId=%d, missleStatuses=%s, unitStatuses=%s}", sequenceId, missleStatuses, unitStatuses);
    }
}
