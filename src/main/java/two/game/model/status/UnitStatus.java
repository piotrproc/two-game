package two.game.model.status;

import two.game.model.Point;

public abstract class UnitStatus {
    private Long unitId;
    private Integer health;
    private Integer dps;
    private Integer speed;
    private Integer sightRange;
    private Integer attackRange;
    private Point position;


    private Point targetPosition;

    public UnitStatus() {
    }

    public UnitStatus(Long unitId, Integer health, Integer dps, Integer speed, Integer sightRange, Integer attackRange,
                      Point position, Point targetPosition) {
        this.unitId = unitId;
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


    public void setDps(Integer dps) {
        this.dps = dps;
    }

    public Integer getDps() {
        return dps;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSightRange(Integer sightRange) {
        this.sightRange = sightRange;
    }

    public Integer getSightRange() {
        return sightRange;
    }

    public void setAttackRange(Integer attackRange) {
        this.attackRange = attackRange;
    }

    public Integer getAttackRange() {
        return attackRange;
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
