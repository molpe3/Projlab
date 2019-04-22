import java.util.ArrayList;

/**
* Ősosztály a pályán lévő tárgyaknak.
*/
public abstract class Thing implements Steppable, Observable, Printable{
	protected Tile tile;
	protected String name;
	protected ArrayList<Observer> observers;
	public Thing(String name){
		this.name=name;
		observers=new ArrayList<Observer>();
	}
	
	/**
	* returns with the tile it occupied
	* @return tile the tile in question
	*/
	public Tile GetTile() {
		return tile;
	}
	
	/**
	* sets occupied tile
	* @param tile the tile we want the thing to occupy
	*/
	protected void SetTile(Tile tile) {
		this.tile=tile;
	}
	
	/**
	* Adds a new observer to its list of subscribers.
	* @param o the observer to be added
	*/
	public void Subscribe(Observer o) {
		observers.add(o);
	}
	
	/**
	* Removes a new observer from its list of subscribers.
	* @param o the observer to be removed
	*/
	public void Detach(Observer o) {
		observers.remove(o);
	}
	
	/**
	* returns with its own name
	* @return name the thing's name
	*/
	public String GetName()
	{
		return name;
	}
}
