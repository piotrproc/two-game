package two.game.model.status;

import two.game.model.Point;

public class UnitStatus {
    private Long unitId;
    private Double health;
    private Point position;

    public UnitStatus() {
    }

    public UnitStatus(Long unitId, Double health, Point position) {
        this.unitId = unitId;
        this.health = health;
        this.position = position;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UnitStatus{" +
                "unitId=" + unitId +
                ", health=" + health +
                ", position=" + position +
                '}';
    }
}
