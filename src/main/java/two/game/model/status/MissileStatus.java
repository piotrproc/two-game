package two.game.model.status;

import two.game.model.Point;

public class MissileStatus {
    private Long missileId;
    private Point currentPosition;
    private Point targetPosition;

    public MissileStatus() {
    }

    public MissileStatus(Long missileId, Point currentPosition, Point targetPosition) {
        this.missileId = missileId;
        this.currentPosition = currentPosition;
        this.targetPosition = targetPosition;
    }

    public Point getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Point targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Long getMissileId() {
        return missileId;
    }

    public void setMissileId(Long missileId) {
        this.missileId = missileId;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "MissileStatus{" +
                "targetPosition=" + targetPosition +
                ", currentPosition=" + currentPosition +
                ", missileId=" + missileId +
                '}';
    }
}
