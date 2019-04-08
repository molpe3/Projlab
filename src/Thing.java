import java.util.ArrayList;
public abstract class Thing implements Steppable, Observable, Printable{
	protected Tile tile;
	protected String name;
	protected ArrayList<Observer> observers;
	public Thing(String name){
		this.name=name;
		observers=new ArrayList<Observer>();
	}
	public Tile GetTile() {
		return tile;
	}
	protected void SetTile(Tile tile) {
		this.tile=tile;
	}
	public void Subscribe(Observer o) {
		observers.add(o);
	}
	public void Detach(Observer o) {
		observers.remove(o);
	}
	public String GetName()
	{
		return name;
	}
}
