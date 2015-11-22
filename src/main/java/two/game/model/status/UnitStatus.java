package two.game.model.status;

public class UnitStatus {
    private Long unitId;
    private Double health;
    private Double positionX;
    private Double positionY;

    public UnitStatus() {
    }

    public UnitStatus(Long unitId, Double health, Double positionX, Double positionY) {

        this.unitId = unitId;
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
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

    public Double getPositionX() {
        return positionX;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return String.format("UnitStatus{unitId=%d, health=%s, positionX=%s, positionY=%s}", unitId, health, positionX, positionY);
    }
}
