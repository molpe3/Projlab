
public class Exit extends Tile {
	private Tile entrance;

	@Override
	public boolean AcceptOrangutan(Orangutan o) {
		if (entrance.AcceptOrangutan(o)) {
			if (this.animal == null) {
				return true;
			} else {
				return false; // there's a zero tick length time where this could happen
			}
		} else {
			return false; // you can't step here if the entrance is blocked, though this aéso only happens
							// for a zero tick length time
		}
	}

	@Override
	public void RemoveAnimal() {
		Orangutan oran = (Orangutan) this.animal; // Only orangutans should ever exist on this tile
		Panda panda = oran.GetPulled();
		Panda temp;
		while (panda != null) {
			oran.Addpoint();
			temp = panda.GetPulled();
			panda.Destroy();
			panda = temp;
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
		System.out.println("\tBejárat:");
		System.out.println("\t\t" + entrance.GetName());
	}

	/*
	 * Should only ever be called by an Orangutan. All inputs from the exit lead to
	 * the entrance.
	 */
	@Override
	public Tile GetNeighbor(int side) {
		return entrance;
	}

	public Tile GetEntrance() {
		return entrance;
	}

	public void SetEntrance(Tile entrance) {
		this.entrance = entrance;
	}
}
