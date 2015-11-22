package two.game.model.status;

public class MissleStatus {
    private Long missleId;
    private Double positionX;
    private Double positionY;
    private Boolean isAerial;

    public MissleStatus() {
    }

    public MissleStatus(Long missleId, Double positionX, Double positionY, Boolean isAerial) {

        this.missleId = missleId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isAerial = isAerial;
    }

    public Long getMissleId() {

        return missleId;
    }

    public void setMissleId(Long missleId) {
        this.missleId = missleId;
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

    public Boolean getIsAerial() {
        return isAerial;
    }

    public void setIsAerial(Boolean isAerial) {
        this.isAerial = isAerial;
    }

    @Override
    public String toString() {
        return String.format("MissleStatus{missleId=%d, positionX=%s, positionY=%s, isAerial=%s}", missleId, positionX, positionY, isAerial);
    }
}
