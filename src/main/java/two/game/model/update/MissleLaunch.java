package two.game.model.update;

public class MissleLaunch {
    private Double targetsAir;
    private Double targetX;
    private Double targetY;

    public MissleLaunch() {
    }

    public MissleLaunch(Double targetsAir, Double targetX, Double targetY) {

        this.targetsAir = targetsAir;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public Double getTargetsAir() {

        return targetsAir;
    }

    public void setTargetsAir(Double targetsAir) {
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
        return String.format("MissleLaunch{targetsAir=%s, targetX=%s, targetY=%s}", targetsAir, targetX, targetY);
    }
}
