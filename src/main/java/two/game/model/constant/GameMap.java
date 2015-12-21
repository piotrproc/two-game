package two.game.model.constant;

import two.game.model.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taz on 2015-11-30.
 */
public class GameMap implements IGameMap {

    private Map<Point, MapElement> gameMap;

    public GameMap() {
        gameMap = new HashMap<>();
    }

    public void updateMap(Point mapStructure, MapElement mapElement) {
        gameMap.put(mapStructure,mapElement);
    }

    @Override
    public MapElement get(int x, int y) {
        for(Point mapStructure : gameMap.keySet()) {
            if(mapStructure.getX() == x && mapStructure.getY() == y) {
                return gameMap.get(mapStructure);
            }
        }
        return null;
    }
}
