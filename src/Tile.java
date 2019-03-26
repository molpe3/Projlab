import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
public class Tile {
	private int sides;
	private Hashtable<Integer,Tile> neighbors;
	protected Animal animal;
	private Thing thing;
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
		this.animal=null;
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
        //Find tile:
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
}
