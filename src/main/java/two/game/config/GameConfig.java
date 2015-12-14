package two.game.config;

import two.game.model.Point;

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


}
