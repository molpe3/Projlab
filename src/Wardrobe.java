public class Wardrobe extends Tile {// nevlegesen ennek mukodnie kell
	private Wardrobe pair;

	public boolean AcceptPanda(Panda p) {
		if (animal != null && pair.GetAnimal() == null) {
			return true;
		} else if (animal != null) {
			return animal.CollideWithPanda(p);
		} else {
			return false;
		}
	}

	public boolean AcceptOrangutan(Orangutan o) {
		if (animal != null && pair.GetAnimal() == null) {
			return true;
		} else if (animal != null) {
			return animal.CollideWithOrangutan(o);
		} else {
			return false;
		}
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
		System.out.println("\tPár:");
		System.out.println("\t\t" + pair.GetName());
	}

	public Wardrobe GetPair() {
		return pair;
	}

	public void SetPair(Wardrobe pair) {
		this.pair = pair;
	}
	
	@Override
	public Tile GetNeighbor(int side) {
		return pair;
	}
}
