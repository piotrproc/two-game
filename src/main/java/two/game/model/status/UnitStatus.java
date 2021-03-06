package two.game.model.status;

import two.game.model.Point;
import two.game.model.constant.UnitType;

public class UnitStatus {
	private Long unitId;
	private UnitType type;
	private String user;
	private Integer health;
	private Integer dps;
	private Integer speed;
	private Integer sightRange;
	private Integer attackRange;
	private Point position;

	private Point targetPosition;

	public UnitStatus() {
	}

	//todo: why dps, health etc. are int? even speed...
	public UnitStatus(Long unitId, UnitType type, String user, Integer health,
			Integer dps, Integer speed, Integer sightRange,
			Integer attackRange, Point position, Point targetPosition) {
		this.unitId = unitId;
		this.type = type;
		this.user = user;

		this.health = health;
		this.dps = dps;
		this.speed = speed;
		this.sightRange = sightRange;
		this.attackRange = attackRange;
		this.position = position;
		this.targetPosition = targetPosition;
	}

	public Point getTargetPosition() {
		return targetPosition;
	}

	public void setTargetPosition(Point targetPosition) {
		this.targetPosition = targetPosition;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Integer getDps() {
		return dps;
	}

	public void setDps(Integer dps) {
		this.dps = dps;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getSightRange() {
		return sightRange;
	}

	public void setSightRange(Integer sightRange) {
		this.sightRange = sightRange;
	}

	public Integer getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(Integer attackRange) {
		this.attackRange = attackRange;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UnitStatus{" +
				"unitId=" + unitId +
				", health=" + health +
				", dps=" + dps +
				", speed=" + speed +
				", sightRange=" + sightRange +
				", attackRange=" + attackRange +
				", targetPosition=" + targetPosition +
				'}';
	}
}
