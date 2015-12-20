package two.game.model.status;

import two.game.model.ControlPoint;
import two.game.model.Event;

import java.util.List;

public class MatchStatus implements Event {
    private Long sequenceId;
    private List<MissileStatus> missileStatuses;
    private List<AttackEvent> attackEvents;
    private List<TeamStatus> teamStatuses;
    private List<UnitStatus> unitStatuses;
    private List<ControlPoint> controlPoints;

    public MatchStatus(Long sequenceId, List<MissileStatus> missileStatuses, List<AttackEvent> attackEvents,
                       List<TeamStatus> teamStatuses, List<UnitStatus> unitStatuses, List<ControlPoint> controlPoints) {
        this.sequenceId = sequenceId;
        this.missileStatuses = missileStatuses;
        this.attackEvents = attackEvents;
        this.teamStatuses = teamStatuses;
        this.unitStatuses = unitStatuses;
        this.controlPoints = controlPoints;
    }

    public MatchStatus() {
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public List<MissileStatus> getMissileStatuses() {
        return missileStatuses;
    }

    public void setMissileStatuses(List<MissileStatus> missileStatuses) {
        this.missileStatuses = missileStatuses;
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

    public List<ControlPoint> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(List<ControlPoint> controlPoints) {
        this.controlPoints = controlPoints;
    }

    @Override
    public String toString() {
        return "MatchStatus{" +
                "sequenceId=" + sequenceId +
                ", missileStatuses=" + missileStatuses +
                ", attackEvents=" + attackEvents +
                ", teamStatuses=" + teamStatuses +
                ", unitStatuses=" + unitStatuses +
                '}';
    }
}
