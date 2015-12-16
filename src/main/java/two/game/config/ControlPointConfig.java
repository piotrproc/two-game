package two.game.config;

import two.game.model.ControlPoint;
import two.game.model.Point;

import java.util.*;

/**
 * Created by Piotr Proc on 16.12.15.
 */
public class ControlPointConfig {

    public static final Integer resourcesPerInterval = 20;
    public static final Integer resourcesIntervalInMillis = 10 * 1000;

    public static final List<ControlPoint> controlPointLocations = new ArrayList<>(Arrays.asList(
            new ControlPoint(new Point(7 * GameConfig.fieldSize, 7 * GameConfig.fieldSize)),
            new ControlPoint(new Point(9 * GameConfig.fieldSize, 9 * GameConfig.fieldSize))
    ));

    public static boolean controlPointIsOnTheField(Point point){
        return controlPointLocations.stream().anyMatch(p -> p.isLocated(point));
    }

}
