import java.util.ArrayList;
public abstract class Thing implements Steppable, Observable{
	private Tile tile;
	protected ArrayList<Observer> observers;
	public Tile GetTile() {
		return tile;
	}
}
