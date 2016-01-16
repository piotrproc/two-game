package two.game.config;

import two.game.model.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Piotr Proc
 */
public class GameConfig {

    private static final int startPointXShift = 14;
    private static final int startPointYShift = 5;
    public static final double fieldSize = 32;
    public static final double gameSize = 3200;

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
