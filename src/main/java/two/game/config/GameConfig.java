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
    public static double fieldSize = 32;
    public static double gameSize = 3200;

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

}
