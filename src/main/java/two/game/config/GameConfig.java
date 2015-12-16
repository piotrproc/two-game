package two.game.config;

import two.game.model.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Piotr Proc on 14.12.15.
 */
public class GameConfig {

    private static int startPointXShift = 1;
    private static int startPointYShift = 1;
    private static double fieldSize = 32;
    private static double gameSize = 3200;

    public static Point getStartPoint(Integer team) {

        if (team == 0)
            return new Point(
                    startPointXShift * fieldSize,
                    startPointXShift * fieldSize);
        else
            return new Point(
                    gameSize - startPointXShift * fieldSize,
                    gameSize - startPointYShift * fieldSize);
    }

    private static Set<Point> controlPointLocations = new HashSet<>(Arrays.asList(
            new Point(7 * fieldSize, 7 * fieldSize),
            new Point(9 * fieldSize, 9 * fieldSize)));

    public static boolean controlPointIsOnTheField(Point point){
        return controlPointLocations.stream().anyMatch(p -> p.isEqual(point));
    }
}
