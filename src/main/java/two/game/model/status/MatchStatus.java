package two.game.model.status;

import java.util.List;

public class MatchStatus {
    private Long sequenceId;
    private List<MissleStatus> missleStatuses;
    private List<AttackEvent> attackEvents;
    private List<TeamStatus> teamStatuses;
    private List<UnitStatus> unitStatuses;

    public MatchStatus(Long sequenceId, List<MissleStatus> missleStatuses, List<AttackEvent> attackEvents, List<TeamStatus> teamStatuses, List<UnitStatus> unitStatuses) {
        this.sequenceId = sequenceId;
        this.missleStatuses = missleStatuses;
        this.attackEvents = attackEvents;
        this.teamStatuses = teamStatuses;
        this.unitStatuses = unitStatuses;
    }

    public MatchStatus() {
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

    public List<AttackEvent> getAttackEvents() {
        return attackEvents;
    }

    public void setAttackEvents(List<AttackEvent> attackEvents) {
        this.attackEvents = attackEvents;
    }

    public List<TeamStatus> getTeamStatuses() {
        return teamStatuses;
    }

    public void setTeamStatuses(List<TeamStatus> teamStatuses) {
        this.teamStatuses = teamStatuses;
    }

    public List<UnitStatus> getUnitStatuses() {
        return unitStatuses;
    }

    public void setUnitStatuses(List<UnitStatus> unitStatuses) {
        this.unitStatuses = unitStatuses;
    }

    @Override
    public String toString() {
        return "MatchStatus{" +
                "sequenceId=" + sequenceId +
                ", missleStatuses=" + missleStatuses +
                ", attackEvents=" + attackEvents +
                ", teamStatuses=" + teamStatuses +
                ", unitStatuses=" + unitStatuses +
                '}';
    }
}
