package two.game.model;

/**
 * Created by Piotr Proc on 14.12.15.
 */
public class ControlPoint {

    private Point location;

    public ControlPoint(Point location){
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

}
