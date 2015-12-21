package two.game.logic.predicates.unitupdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import two.game.logic.GameState;
import two.game.logic.predicates.ChangePredicate;
import two.game.model.Point;
import two.game.model.status.TeamStatus;
import two.game.model.status.UnitStatus;
import two.game.model.update.UnitAttack;
import two.game.model.update.UnitUpdate;
import static two.game.model.constant.UnitType.*;

public class AttackPredicate implements ChangePredicate<UnitUpdate> {
    private static final Logger logger = LoggerFactory.getLogger(AttackPredicate.class);

	@Override
	public boolean applicable(UnitUpdate object, GameState state) {		
		if (object.getAttacks() == null || object.getAttacks().isEmpty())
			return true;
		
		UnitStatus unitStatus = getUnitStatus(object.getUnitId(), state);		
		TeamStatus userTeam = getTeamStatus(unitStatus, state);
		
		
		for (UnitAttack unitAttack : object.getAttacks()){
			UnitStatus targetUnitStatus = getUnitStatus(unitAttack.getTargetUnitId(), state);
			TeamStatus targetUnitTeam = getTeamStatus(targetUnitStatus, state);
			
			if (userTeam.equals(targetUnitTeam)){
				logger.debug("Friendly fire is forbidden");
				return false;
			}
			
			double distance = getDistance(unitStatus.getPosition(), targetUnitStatus.getPosition());		
			if (CANNON.equals(unitStatus.getType()) 
					|| distance > unitStatus.getSightRange() * 32){
				logger.debug("Only cannon can fire to target which is not in sight");
				return false;
			}
			
			if (TANK.equals(unitStatus.getType()) 
					&& AIRFORCE.equals(targetUnitStatus.getType())){
				logger.debug("Cannon cannot attack airforce");
				return false;
			}
		}

		return true;
	}
	
	public static TeamStatus getTeamStatus(UnitStatus unitStatus, GameState state){
		Long userId = state.getUserIdToSequenceId()
				.get(unitStatus.getUser());
		return state.getTeamStatuses().stream()
			.filter(t -> t.getUserIds().contains(userId)).findAny().get();
	}
	
	public static UnitStatus getUnitStatus(Long unitId, GameState state){
		return state.getUnitStatuses().stream()
			.filter(s -> s.getUnitId().equals(unitId))
			.findAny().get();
	}
	
	private double getDistance(Point x, Point y){	
		return Math.sqrt((x.getX() - y.getX()) * (x.getX() - y.getX()) 
				+ (x.getY() - y.getY()) * (x.getY() - y.getY()));
	}
}
