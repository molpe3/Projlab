import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
public class Tile implements Printable {
	private int sides=0;
	protected Hashtable<Integer,Tile> neighbors;
	protected Animal animal=null;
	protected Thing thing=null;
	protected String name;
	public Tile (String name)
	{
		this.name=name;
		neighbors=new Hashtable<Integer,Tile>();
	}
	public boolean AcceptPanda(Panda p) {
		if (animal!=null) {
			animal.CollideWithPanda(p);
			return false;
		}
		return true;
	}
	public boolean AcceptOrangutan(Orangutan o) {
		if (animal!=null){
			animal.CollideWithOrangutan(o);
			return false;
		}
		return true;
	}
	public void RemoveAnimal() {
		animal=null;
	}
	public void JumpedOn() {}
	public Tile GetNeighbor(int side) {
		return neighbors.get(side);
	}
	public int GetSides() {
		return sides;
	}
	public int CompareTile(Tile t) {
        if (this.equals(t)) 
        	return -1;
        for(Map.Entry<Integer,Tile> it : neighbors.entrySet()) {
            if(t.equals( it.getValue() )) {
                return it.getKey();
            }
        }
        return 0;
     }  
	public void SetAnimal (Animal a) {
		animal=a;
	}
	public Animal GetAnimal()
	{
		return animal;
	}
	public String GetName()
	{
		return name;
	}
	public void AddNeighbor(int side, Tile neighbor)
	{
		neighbors.put(side, neighbor);
		sides++;
	}
	public void SetThing(Thing thing){
		this.thing=thing;
	}
	public void Print() {
		System.out.println("\t"+this.name);
		System.out.println("\tSzomszédok:");
		if (!neighbors.isEmpty()) {
			for (int key:neighbors.keySet()){
				System.out.println("\t\t"+key+":"+neighbors.get(key).GetName());
			}
		}
		System.out.println("\tRajta lévo állat:");
		if (animal!=null) {
			System.out.println("\t\t"+animal.GetName()+":"+animal.getClass());
		}
		System.out.println("\tRajta lévo tárgy:");
		if (thing!=null)
			System.out.println("\t\t"+thing.GetName()+":"+thing.getClass());
	}
}
