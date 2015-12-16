package two.game.config;

import two.game.model.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Piotr Proc on 16.12.15.
 */
public class ControlPointConfig {

    public static Integer resourcesPerInterval = 20;
    public static Integer resourcesIntervalInMillis = 10 * 1000;

    private static Set<Point> controlPointLocations = new HashSet<>(Arrays.asList(
            new Point(7 * GameConfig.fieldSize, 7 * GameConfig.fieldSize),
            new Point(9 * GameConfig.fieldSize, 9 * GameConfig.fieldSize)));

    public static boolean controlPointIsOnTheField(Point point){
        return controlPointLocations.stream().anyMatch(p -> p.isEqual(point));
    }

}
