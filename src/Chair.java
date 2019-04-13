public class Chair extends Thing {
	private boolean occupied = false;

	public void Step() {
		if (!occupied && GetTile().GetAnimal() == null) {
			Notify();
		}
	}

	public void Notify() {
		for (Observer o : observers) {
			o.Update(this);
		}
	}

	public void Print() {
		System.out.println("\t" + this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t" + tile.name + ":" + tile.getClass());
		System.out.println("\t¡llapot:");
		if (occupied == true) {
			System.out.println("\t\tfoglalt");
		} else {
			System.out.println("\t\tszabad");
		}
		System.out.println("\tObserverek:");
		for (Observer o : observers) {
			System.out.println("\t\t" + o.GetName());
		}
	}

	@Override
	public int AskForRandomNumber() {
		return 0; // doesn't need a random number
	}

	public boolean GetOccupied() {
		return occupied;
	}

	public void SetOccupied(boolean b) {
		occupied = b;
	}

	public void Detach(Observer o) {
		observers.remove(o);
	}
}