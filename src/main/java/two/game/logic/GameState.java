package two.game.logic;

import two.game.model.constant.GameMap;
import two.game.model.status.AttackEvent;
import two.game.model.status.MissleStatus;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;

import java.util.List;

public class GameState {
    private final GameMap map;
    private List<MissleStatus> missileStatuses;
    private List<AttackEvent> attackEvents;
    private List<TeamStatus> teamStatuses;
    private List<UnitStatus> unitStatuses;

    public GameState(GameMap map, List<MissleStatus> missileStatuses, List<AttackEvent> attackEvents, List<TeamStatus> teamStatuses, List<UnitStatus> unitStatuses) {
        this.map = map;
        this.missileStatuses = missileStatuses;
        this.attackEvents = attackEvents;
        this.teamStatuses = teamStatuses;
        this.unitStatuses = unitStatuses;
    }

    public GameMap getMap() {
        return map;
    }

    public List<MissleStatus> getMissileStatuses() {
        return missileStatuses;
    }

    public void setMissileStatuses(List<MissleStatus> missileStatuses) {
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
}
