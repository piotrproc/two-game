package two.game.logic;

import two.game.model.Point;
import two.game.model.constant.IGameMap;
import two.game.model.constant.MapElement;
import two.game.model.constant.MapParser;
import two.game.model.status.AttackEvent;
import two.game.model.status.MissleStatus;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;

import java.util.*;

public class GameState {
    private final IGameMap map;
    private final Map<String, Long> userIdToSequenceId;
    private Boolean gameStarted;
    private List<MissleStatus> missileStatuses;
    private List<AttackEvent> attackEvents;
    private List<TeamStatus> teamStatuses;
    private List<UnitStatus> unitStatuses;
    private Long updateSequenceId;

    public GameState() {
        this(MapParser.parse(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        this.getTeamStatuses().add(new TeamStatus("Team A", 1000., new HashSet<>(Arrays.asList("user1"))));
        this.getTeamStatuses().add(new TeamStatus("Team B", 1000., new HashSet<>(Arrays.asList("user2"))));
        this.getUnitStatuses().add(new UnitStatus(1L, "user1", 10, 2, 2, 4, 2, new Point(50.0, 50.0), new Point(50.0, 50.0)));
        this.getUnitStatuses().add(new UnitStatus(2L, "user2", 10, 2, 2, 4, 2, new Point(55.0, 55.0), new Point(55.0, 55.0)));

    }

    public GameState(IGameMap map, List<MissleStatus> missileStatuses, List<AttackEvent> attackEvents, List<TeamStatus> teamStatuses, List<UnitStatus> unitStatuses) {
        this.map = map;
        this.missileStatuses = missileStatuses;
        this.attackEvents = attackEvents;
        this.teamStatuses = teamStatuses;
        this.unitStatuses = unitStatuses;
        this.gameStarted = false;
        this.userIdToSequenceId = new HashMap<>();
        this.updateSequenceId = 0l;
    }

    public Map<String, Long> getUserIdToSequenceId() {
        return userIdToSequenceId;
    }

    public IGameMap getMap() {
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

    public Boolean isStarted() {
        return gameStarted;
    }

    public void setStarted(Boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Long getUpdateSequenceId() {
        return updateSequenceId;
    }

    public void bumpUpdateSequenceId() {
        this.updateSequenceId += 1;
    }
}
