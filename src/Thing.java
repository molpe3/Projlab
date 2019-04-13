import java.io.IOException;
import java.util.ArrayList;

public abstract class Thing implements Steppable, Observable, Printable{
	protected Tile tile;
	protected String name = "UNNAMED";
	protected ArrayList<Observer> observers = new ArrayList<Observer>();

	public abstract int AskForRandomNumber() throws IOException;

	public Tile GetTile() {
		return tile;
	}

	protected void SetTile(Tile tile) {
		this.tile = tile;
	}
	
	public void Subscribe(Observer o) {
		observers.add(o);
	}
	
	public void Detach(Observer o) {
		observers.remove(o);
	}
}