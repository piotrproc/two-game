package two.game.model.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import two.game.model.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {

    private static final Logger logger = LoggerFactory.getLogger(MapParser.class);

    public static GameMap parse() {

        String csvFile = "src/main/webapp/map/my_map.csv";


        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int rowCounter = 0;
        GameMap gameMap = new GameMap();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(cvsSplitBy);

                for (int i = 0; i < row.length; i++) {
                    MapElement mapElement = parseMapElement(row[i]);
                    Point point = new Point((double)i, (double)rowCounter);
                    gameMap.updateMap(point, mapElement);
                }
                rowCounter++; 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return gameMap;
    }

    private static MapElement parseMapElement(String element) {

        if (element.equals("8")) {
            return MapElement.TREE;
        }
        if (element.equals("4")) {
            return MapElement.WATER;
        }
        if (element.equals("0")) {
            return MapElement.GROUND;
        }
        return null;
    }

}