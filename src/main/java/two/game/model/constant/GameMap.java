package two.game.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taz on 2015-11-30.
 */
public class GameMap implements IGameMap {

    private Map<MapStructure, MapElement> gameMap;

    public GameMap() {
        gameMap = new HashMap<>();
    }

    public void updateMap(MapStructure mapStructure, MapElement mapElement) {
        gameMap.put(mapStructure,mapElement);
    }

    @Override
    public MapElement get(int x, int y) {
        for(MapStructure mapStructure : gameMap.keySet()) {
            if(mapStructure.x == x && mapStructure.y == y) {
                return gameMap.get(mapStructure);
            }
        }
        return null;
    }
}
