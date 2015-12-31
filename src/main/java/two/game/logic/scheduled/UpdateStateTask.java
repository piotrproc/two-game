package two.game.logic.scheduled;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import two.game.config.ControlPointConfig;
import two.game.logic.GameState;
import two.game.model.Point;
import two.game.model.status.MissileStatus;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;

public class UpdateStateTask implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(UpdateStateTask.class);
	private static final double MOVE_DELTA_PER_MS = 0.1;
	private static final int MISSILE_RADIUS = 4;
	private static final int MISSILE_DAMAGE = 0;
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
		unit.setHealth(unit.getHealth() - damage);
		if (unit.getHealth() <= 0) {
            removeUnit(unit.getUnitId());
        }
	}

	private void updateMissile(long millisElapsed) {
		gameState.getMissiles().forEach(missile -> {

			Point position = missile.getCurrentPosition();
			Point target  = missile.getTargetPosition();

			if(position.equals(target)){
				Collection<UnitStatus> units = gameState.getUnitsInRadius(target, MISSILE_RADIUS);
				units.forEach(u -> applyDamage(u, MISSILE_DAMAGE));
			}else{
				Point newPosition = move(position, target, millisElapsed);

				MissileStatus missileToUpdate = gameState.getMissileStatuses().stream()
						.filter(m -> m.getMissileId().equals(missile.getMissileId()))
						.findFirst().get();
				missileToUpdate.setCurrentPosition(newPosition);
			}
		});

        List<MissileStatus> missileStatuses = gameState.getMissileStatuses();
        for (int i = 0; i < missileStatuses.size(); i++) {
            MissileStatus missile = missileStatuses.get(i);

            if (missile.getCurrentPosition().equals(missile.getTargetPosition())) {
                missileStatuses.remove(missile);
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
					Point newPosition = move(position, target, millisElapsed);
					unit.setPosition(newPosition);
				});
	}

	private Point move(Point position, Point target, long time) {
		double dx = target.getX() - position.getX();
		double dy = target.getY() - position.getY();

		double distanceAllowed = Math.abs(MOVE_DELTA_PER_MS * time);
		double scaler = (Math.abs(dx) + Math.abs(dy)) / distanceAllowed;
		scaler = Math.abs(Math.max(1., scaler));

		return new Point(position.getX() + dx / scaler, position.getY() + dy / scaler);
	}
}
