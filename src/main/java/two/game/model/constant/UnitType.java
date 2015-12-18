package two.game.model.constant;

import static two.game.model.constant.MapElement.*;

import java.util.Arrays;
import java.util.List;

public enum UnitType {
	AIRFORCE ("airforce", 300, MapElement.values()),
	CANNON ("cannon", 300, GROUND),
	TANK ("tank", 300, GROUND),
	SOLDIER ("soldier", 300, GROUND, TREE)
	
	;
	
	private final String name;
	private final double prices;
	private final List<MapElement> canMoveOn;
	
	private UnitType(String name, double prices, MapElement ... canMoveOn){
		this.name = name;
		this.prices = prices;
		this.canMoveOn = Arrays.asList(canMoveOn);
	}

	

	public static UnitType fromIntegerType(Integer unitType){
		return values()[unitType];
	}
	
	public String getName() {
		return name;
	}
	public double getPrices() {
		return prices;
	}
	public List<MapElement> getCanMoveOn() {
		return canMoveOn;
	}	
}
