package two.game.logic.scheduled;

import java.util.List;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import two.game.config.ControlPointConfig;
import two.game.logic.GameState;
import two.game.model.Point;
import two.game.model.constant.IGameMap;
import two.game.model.constant.MapElement;
import two.game.model.constant.UnitType;
import two.game.model.status.MissileStatus;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;

public class UpdateStateTask implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(UpdateStateTask.class);
	private static final double MOVE_DELTA_PER_MS = 0.1;
	private static final int MISSILE_RADIUS = 2;
	private static final int MISSILE_DAMAGE = 50;
	private final GameState gameState;
	private DateTime lastUpdate;
	private long sumOfMillisElapsed = 0;

	public UpdateStateTask(GameState gameState) {
		logger.debug(gameState.toString());
		this.gameState = gameState;
		this.lastUpdate = DateTime.now();
	}

	@Override
	public void run() {
		try {
			DateTime now = DateTime.now();
			long millisElapsed = new Duration(now, lastUpdate).getMillis();

			synchronized (gameState) {
				updateUnitPositions(millisElapsed);
				updateUnitHealth(millisElapsed);
				updateMissile(millisElapsed);
				updateResources(millisElapsed);
				lastUpdate = now;
				gameState.bumpUpdateSequenceId();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void updateResources(long millisElapsed) {
		sumOfMillisElapsed = sumOfMillisElapsed + (-millisElapsed); //millisElapsed is negative

		if (sumOfMillisElapsed > ControlPointConfig.resourcesIntervalInMillis) {
			sumOfMillisElapsed = sumOfMillisElapsed - ControlPointConfig.resourcesIntervalInMillis;
			gameState.getTeamStatuses().forEach(TeamStatus::addResourcesByControlPoints);
		}
	}

	private void updateUnitHealth(long millisElapsed) {
		gameState.getNewAttacks().forEach(attack -> {
			Long sourceId = attack.getSourceUnitId();
			Long targetId = attack.getTargetUnitId();

			UnitStatus attacker = getUnit(sourceId);
			UnitStatus attacked = getUnit(targetId);

			if(attacker != null && attacked != null) {
				int damage = (int) -(attacker.getDps() * millisElapsed * 0.0001);
				applyDamage(attacked, damage);
			}
		});
	}

	private void applyDamage(UnitStatus unit, int damage) {
        logger.debug("@@@@@@@");

        unit.setHealth(unit.getHealth() - damage);
		if (unit.getHealth() <= 0) {
            removeUnit(unit.getUnitId());
        }
	}

	private void updateMissile(long millisElapsed) {

        List<MissileStatus> missileStatuses = gameState.getMissileStatuses();
        for (int i = 0; i < missileStatuses.size(); i++) {
            MissileStatus missile = missileStatuses.get(i);

            Point position = missile.getCurrentPosition();
            Point target  = missile.getTargetPosition();

            if(position.equals(target)){
                gameState.getUnitStatuses().stream()
                        .filter(unit -> unit.getPosition().distanceTo(target) <= 32 * MISSILE_RADIUS)
                        .forEach(unit -> applyDamage(unit, MISSILE_DAMAGE));
                missileStatuses.remove(missile);

            }else{
                Point newPosition = moveMissile(position, target, millisElapsed);

                MissileStatus missileToUpdate = gameState.getMissileStatuses().stream()
                        .filter(m -> m.getMissileId().equals(missile.getMissileId()))
                        .findFirst().get();
                missileToUpdate.setCurrentPosition(newPosition);
            }
        }

	}

	private void removeUnit(Long unitId) {
		List<UnitStatus> units = gameState.getUnitStatuses();
		for (int i = 0; i < units.size(); i++) {
			if (units.get(i).getUnitId().equals(unitId)) {
				units.remove(i);
				return;
			}
		}
	}

	private UnitStatus getUnit(Long sourceId) {
		return gameState.getUnitStatuses().stream()
				.filter(u -> u.getUnitId().equals(sourceId))
				.findFirst()
				.orElse(null);
	}

	private void updateUnitPositions(long millisElapsed) {
		gameState.getUnitStatuses().stream()
				.filter(u -> u != null)
				.filter(u -> u.getPosition() != null)
				.filter(u -> u.getTargetPosition() != null)
				.forEach(unit -> {
					Point position = unit.getPosition();
					Point target = unit.getTargetPosition();
                    Point newPosition = moveUnit(gameState, unit,position, target, millisElapsed);
					unit.setPosition(newPosition);
				});
	}

	private Point moveMissile(Point position, Point target, long time) {
		double dx = target.getX() - position.getX();
		double dy = target.getY() - position.getY();

		double distanceAllowed = Math.abs(MOVE_DELTA_PER_MS * time);
		double scaler = (Math.abs(dx) + Math.abs(dy)) / distanceAllowed;
		scaler = Math.abs(Math.max(1., scaler));

		return new Point(position.getX() + dx / scaler, position.getY() + dy / scaler);
	}

	private Point moveUnit(GameState gameState, UnitStatus unit, Point position, Point target, long time) {
		double dx = target.getX() - position.getX();
		double dy = target.getY() - position.getY();

		double distanceAllowed = Math.abs(MOVE_DELTA_PER_MS * time);
		double scaler = (Math.abs(dx) + Math.abs(dy)) / distanceAllowed;
		scaler = Math.abs(Math.max(1., scaler));

		Point result = new Point(position.getX() + dx / scaler, position.getY() + dy / scaler);
		if (getOverlapping(gameState.getMap(), result).stream().allMatch(element -> isAllowed(unit.getType(), element))) {
			return result;
		}

		int roundedX = 32 * (int) (Math.round(position.getX() / 32));
		int roundedY = 32 * (int) (Math.round(position.getY() / 32));
		result = new Point((double) roundedX, position.getY() + dy / scaler);
		if (getOverlapping(gameState.getMap(), result).stream().allMatch(element -> isAllowed(unit.getType(), element))) {
			return result;
		}
		result = new Point(position.getX() + dx / scaler, (double) roundedY);
		if (getOverlapping(gameState.getMap(), result).stream().allMatch(element -> isAllowed(unit.getType(), element))) {
			return result;
		}
		result = new Point((double) roundedX, (double) roundedY);
		return result;
	}

    private boolean isAllowed(UnitType type, MapElement element) {
        // todo: implement
        return element.equals(MapElement.GROUND);
    }

    private List<MapElement> getOverlapping(IGameMap map, Point position) {
        int left = (int) (position.getX() / 32);
        int upper = (int) (position.getY() / 32);
        int right = left + 1;
        int lower = upper + 1;

        return Lists.newArrayList(
                map.get(left, upper),
                map.get(left, lower),
                map.get(right, upper),
                map.get(right, lower)
        );
    }
}
