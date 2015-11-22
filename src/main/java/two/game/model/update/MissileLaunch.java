package two.game.model.update;

public class MissileLaunch {
    private Boolean targetsAir;
    private Double targetX;
    private Double targetY;

    public MissileLaunch() {
    }

    public MissileLaunch(Boolean targetsAir, Double targetX, Double targetY) {

        this.targetsAir = targetsAir;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public Boolean getTargetsAir() {

        return targetsAir;
    }

    public void setTargetsAir(Boolean targetsAir) {
        this.targetsAir = targetsAir;
    }

    public Double getTargetX() {
        return targetX;
    }

    public void setTargetX(Double targetX) {
        this.targetX = targetX;
    }

    public Double getTargetY() {
        return targetY;
    }

    public void setTargetY(Double targetY) {
        this.targetY = targetY;
    }

    @Override
    public String toString() {
        return String.format("MissileLaunch{targetsAir=%s, targetX=%s, targetY=%s}", targetsAir, targetX, targetY);
    }
}
