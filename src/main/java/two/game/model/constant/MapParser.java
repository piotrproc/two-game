package two.game.model.constant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {

    public static GameMap parse() {

        String csvFile = "D:/Dropbox/Studia/Semestr IX/Technologie Wytwarzania Oprogramowania/two-game/src/main/webapp/map/my_map.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int rowCounter = 0;
        GameMap gameMap = new GameMap();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] row = line.split(cvsSplitBy);
                for(int i=0;i<row.length;i++)
                {
                    gameMap.updateMap(new MapStructure(rowCounter, i), parseMapElement(row[i]));
                }
                rowCounter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    private static MapElement parseMapElement(String element)
    {
        if(element == "8") {
            return MapElement.TREE;
        }
        if(element == "4") {
            return MapElement.WATER;
        }
        if(element == "0") {
            return MapElement.GROUND;
        }
        return null;
    }

}