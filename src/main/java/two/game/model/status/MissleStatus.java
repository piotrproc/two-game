package two.game.model.status;

import two.game.model.Point;

public class MissleStatus {
    private Long missleId;
    private Point currentPosition;

    public MissleStatus() {

    }

    public MissleStatus(Long missleId, Point currentPosition) {

        this.missleId = missleId;
        this.currentPosition = currentPosition;
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
                "missleId=" + missleId +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
