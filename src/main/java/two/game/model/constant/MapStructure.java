package two.game.model.constant;

/**
 * Created by Taz on 2015-11-30.
 */
public class MapStructure {
    int x;
    int y;

    public MapStructure(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

	@Override
	public String toString() {
		return "MapStructure [x=" + x + ", y=" + y + "]";
	}
}