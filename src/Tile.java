import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
* A játékteret felépítő mezők.
* Helyet adhatnak pontosan egy állatnak és tárgynak, aktuális státuszuktól függően befogadják vagy elutasítják
* a rájuk lépni kívánó állatokat, továbbá információt szolgáltatnak a róluk továbblépni kívánó állatok számára a szomszédaikról.
*/
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
	
	/**
	* Manages the movement of an inbound panda.
	* If occupied, causes the two animals to collide.
	* If the movement is okay, manages the switch between tiles.
	*/
	public boolean AcceptPanda(Panda p) {
		if (animal!=null) {
			animal.CollideWithPanda(p);
			return false;
		}
		p.tile.RemoveAnimal();
		p.tile=this;
		animal=p;
		return true;
	}
	
	/**
	* Manages the movement of an inbound orangutan.
	* If occupied, causes the two animals to collide.
	* If the movement is okay, manages the switch between tiles.
	*/
	public boolean AcceptOrangutan(Orangutan o) {
		if (animal!=null){
			animal.CollideWithOrangutan(o);
			return false;
		}
		o.tile.RemoveAnimal();
		o.tile=this;
		animal=o;
		return true;
	}
	
	/**
	* removes the current animal from itself
	*/
	public void RemoveAnimal() {
		animal=null;
	}
	
	/**
	* WeakTile subclass implements this with expanded functionality
	*/
	public void JumpedOn() {}
	
	/**
	* returns its neighbor on a given side
	* @param side the side we're curious about
	*/
	public Tile GetNeighbor(int side) {
		return neighbors.get(side);
	}
	
	/**
	* returns the tile's number of sides
	* @return sides the number of sides the tile has
	*/
	public int GetSides() {
		return sides;
	}
	
	/**
	* compares a parameter tile to itself and its neighbors
	* returns with -1 if it's the same as the parameter tile, 0 if the two are not neighbors,
	* or the number of the neighbor if they are.
	* @param t the tile we're comparing.
	*/
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
	
	/**
	* Sets occupying animal.
	* @param a the animal we want to set as the tile's occupant
	*/
	public void SetAnimal (Animal a) {
		animal=a;
	}
	
	/**
	* returns with occupying animal
	* @return animal the animal that's currently on the tile
	*/
	public Animal GetAnimal()
	{
		return animal;
	}
	
	/**
	* Returns with the name of the tile.
	* @param name the tile's name.
	*/
	public String GetName()
	{
		return name;
	}
	
	/**
	* Adds a neighbor to the tile.
	* @param neighbor the tile we want to add
	* @param side the index we want to put it at
	*/
	public void AddNeighbor(int side, Tile neighbor)
	{
		neighbors.put(side, neighbor);
		sides++;
	}
	
	/**
	* Sets the tile's occupying thing.
	* @param thing the thing we want to occupy the tile.
	*/
	public void SetThing(Thing thing){
		this.thing=thing;
	}
	
	/**
	* Prints out all relevant information.
	* This includes name, neighbors, and the occupying animal and thing.
	*/
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
