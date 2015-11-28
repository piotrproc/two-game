package two.game.model.status;

import two.game.model.Point;

public class MissleStatus {
    private Long missleId;
    private Point currentPosition;
    private Point targetPosition;

    public MissleStatus() {
    }

    public MissleStatus(Long missleId, Point currentPosition, Point targetPosition) {
        this.missleId = missleId;
        this.currentPosition = currentPosition;
        this.targetPosition = targetPosition;
    }

    public Point getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Point targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Long getMissleId() {
        return missleId;
    }

    public void setMissleId(Long missleId) {
        this.missleId = missleId;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "MissleStatus{" +
                "targetPosition=" + targetPosition +
                ", currentPosition=" + currentPosition +
                ", missleId=" + missleId +
                '}';
    }
}
