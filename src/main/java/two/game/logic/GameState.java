package two.game.logic;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.config.ControlPointConfig;
import two.game.config.GameConfig;
import two.game.model.ControlPoint;
import two.game.model.Point;
import two.game.model.constant.IGameMap;
import two.game.model.constant.MapParser;
import two.game.model.constant.UnitType;
import two.game.model.init.MatchInfo;
import two.game.model.init.StartGame;
import two.game.model.status.AttackEvent;
import two.game.model.status.MissileStatus;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;

import java.util.*;

/**
 * remember that object is shared and all actions should
 */
public class GameState extends AbstractVerticle {


    private static final Logger logger = LoggerFactory.getLogger(GameState.class);
	private final IGameMap map;
	private final Map<String, Long> userIdToSequenceId;
	private Boolean gameStarted;
	private List<MissileStatus> missileStatuses;
	private List<AttackEvent> attackEvents;
	private List<TeamStatus> teamStatuses;
	private List<UnitStatus> unitStatuses;
	private List<ControlPoint> controlPoints;
	private Long updateSequenceId;
    private MatchInfo matchInfo;


	private int lastProcessedAttack = 0;

	public GameState() {
		this(MapParser.parse(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), ControlPointConfig.controlPointLocations, MatchInfo.getDefault());
		this.matchInfo.setState(this);
		//ControlPoint cp = new ControlPoint(new Point(224.0, 224.0));
        /*this.getTeamStatuses().add(new TeamStatus("Team A", 1000., new HashSet<>(Arrays.asList("user1")), new HashSet<>()));
		this.getTeamStatuses().add(new TeamStatus("Team B", 1000., new HashSet<>(Arrays.asList("user2")), new HashSet<>(Arrays.asList(cp))));
		this.getUnitStatuses().add(new UnitStatus(1L, UnitType.CANNON, "user1", 80, 200, 2, 4, 2, new Point(256, 96), new Point(256, 96)));
		this.getUnitStatuses().add(new UnitStatus(2L, UnitType.SOLDIER, "user1", 80, 200, 2, 4, 2, new Point(256, 32), new Point(256, 32)));
        this.getUnitStatuses().add(new UnitStatus(3L, UnitType.AIRFORCE, "user1", 80, 200, 2, 4, 2, new Point(256, 64), new Point(256, 64)));
		this.getUnitStatuses().add(new UnitStatus(11L, UnitType.TANK, "user2", 80, 200, 2, 4, 2, new Point(192, 96), new Point(192, 32)));*/
	}

	public GameState(IGameMap map, List<MissileStatus> missileStatuses, List<AttackEvent> attackEvents,
			List<TeamStatus> teamStatuses, List<UnitStatus> unitStatuses, List<ControlPoint> controlPoints, MatchInfo info) {
		this.matchInfo = info;
        this.map = map;
		this.missileStatuses = missileStatuses;
		this.attackEvents = attackEvents;
		this.teamStatuses = teamStatuses;
		this.unitStatuses = unitStatuses;
		this.controlPoints = controlPoints;
		this.gameStarted = false;
		this.userIdToSequenceId = new HashMap<>();
		this.updateSequenceId = 0L;
	}

	public synchronized Collection<MissileStatus> getMissiles() {
//		List<MissileStatus> missiles = missileStatuses.subList(lastProcessedMissile, missileStatuses.size());
//		lastProcessedMissile = missileStatuses.size();
		return missileStatuses;
	}


	public synchronized Collection<AttackEvent> getNewAttacks() {
		List<AttackEvent> missiles = attackEvents.subList(lastProcessedAttack, attackEvents.size());
		lastProcessedAttack = attackEvents.size() ;
		return missiles;
	}

	public Map<String, Long> getUserIdToSequenceId() {
		return userIdToSequenceId;
	}

	public IGameMap getMap() {
		return map;
	}

	public synchronized List<MissileStatus> getMissileStatuses() {
		return missileStatuses;
	}

	public void setMissileStatuses(List<MissileStatus> missileStatuses) {
		this.missileStatuses = missileStatuses;
	}

	public synchronized List<AttackEvent> getAttackEvents() {
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

	public Boolean isStarted() {
		return gameStarted;
	}

    public synchronized void start() {
        this.setStarted(true);

        Map<String, List<String>> teamToPlayers = this.getMatchInfo().getTeamToPlayers();
        for (String team : teamToPlayers.keySet()) {
            this.getTeamStatuses().add(new TeamStatus(team, 1000., new HashSet<>(teamToPlayers.get(team)), new HashSet<>()));
        }

        this.vertx.eventBus().publish("StartGame", new Gson().toJson(new StartGame()));
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

	public void addUnit(UnitType unitType, String user, Integer teamNumber) {
		Point startPoint = GameConfig.getStartPoint(teamNumber);
		long unitId = getUniqueUnitId();

		// todo: so many magic constants...
		this.getUnitStatuses().add(new UnitStatus(unitId, unitType, user, 100, 200, 2, 4, 2, startPoint, startPoint));
	}

	public void addUnit(UnitStatus status) {
		this.unitStatuses.add(status);
	}

	public synchronized void addAttack(AttackEvent attackEvent) {
		this.attackEvents.add(attackEvent);
		logger.info("Add Attacks: " + attackEvents.size());
	}

    public MatchInfo getMatchInfo() {
        return this.matchInfo;
    }

	public synchronized void addMissile(MissileStatus missileStatus) {
		this.missileStatuses.add(missileStatus);
	}

	private long getUniqueUnitId() {
		long LOWER_RANGE = 0;
		long UPPER_RANGE = 1000000;
		Random random = new Random();

		return LOWER_RANGE +
				(long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
	}

}
