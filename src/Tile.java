import java.util.Hashtable;
import java.util.Map;

public class Tile implements Printable {
	private int sides;
	protected Hashtable<Integer, Tile> neighbors = new Hashtable<Integer, Tile>();
	protected Animal animal;
	protected Thing thing;
	protected String name = "UNNAMED";

	public boolean AcceptPanda(Panda p) {
		if (animal != null) {
			return animal.CollideWithPanda(p);
		} else {
			return true;
		}
	}

	public boolean AcceptOrangutan(Orangutan o) {
		if (animal != null) {
			return animal.CollideWithOrangutan(o);
		} else {
			return true;
		}
	}

	public int CompareTile(Tile t) {
		if (this.equals(t)) {
			return -1;
		}
		for (Map.Entry<Integer, Tile> it : neighbors.entrySet()) {
			if (t.equals(it.getValue())) {
				return it.getKey();
			}
		}
		return 0;
	}

	public void Print() {
		System.out.println("\t" + this.name);
		System.out.println("\tSzomszédok:");
		if (!neighbors.isEmpty()) {
			for (int key : neighbors.keySet()) {
				System.out.println("\t\t" + key + ":" + neighbors.get(key).GetName());
			}
		}
		System.out.println("\tRajta lévo állat:");
		if (animal != null) {
			System.out.println("\t\t" + animal.name + ":" + animal.getClass());
		}
		System.out.println("\tRajta lévo tárgy:");
		if (thing != null)
			System.out.println("\t\t" + thing.name + ":" + thing.getClass());
	}

	public void AddNeighbor(int side, Tile neighbor) {
		neighbors.put(Integer.valueOf(side), neighbor);
		sides++;
	}

	public void SetThing(Thing thing) {
		this.thing = thing;
	}

	public String GetName() {
		return name;
	}

	public Animal GetAnimal() {
		return animal;
	}

	public void SetAnimal(Animal a) {
		animal = a;
	}

	public int GetSides() {
		return sides;
	}

	public Tile GetNeighbor(int side) {
		return neighbors.get(Integer.valueOf(side));
	}

	public void RemoveAnimal() {
		animal = null;
	}

	public void JumpedOn() {
	}
}
