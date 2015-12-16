package two.game.config;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Piotr Proc on 14.12.15.
 */
public class UnitConfig {
    private static final String[] unitNames = {"airforce", "cannon", "tank", "soldier"};
    private static final Double[] unitPrices = {300.0, 300.0, 300.0 , 300.0};

    public static String getUnitName(Integer key){
        return unitNames[key-1];
    }

    public static Double getUnitPrice(Integer key){
        return unitPrices[key-1];
    }
}
