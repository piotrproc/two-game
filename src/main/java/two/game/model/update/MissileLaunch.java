package two.game.model.update;

import two.game.model.Point;

public class MissileLaunch {
    private Long missileId;
    private Point target;

    public MissileLaunch() {
    }

    public MissileLaunch(Point target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "MissileLaunch{" +
                "missileId=" + missileId.toString() +
                "target=" + target +
                '}';
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public Long getMissileId() {
        return missileId;
    }

    public void setMissileId(Long missileId) {
        this.missileId = missileId;
    }

}
