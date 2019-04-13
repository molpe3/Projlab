public class WeakTile extends Tile {
	private int steps = 0;
	private boolean broken = false;

	public boolean AcceptPanda(Panda p) {
		if (!broken) {
			if (super.AcceptPanda(p)) {
				Damage(1);
				return true;
			} else {
				return false;
			}
		} else {
			p.Fall();
			return true;
		}
	}

	public boolean AcceptOrangutan(Orangutan o) {
		if (!broken) {
			if (super.AcceptOrangutan(o)) {
				Damage(1);
				return true;
			} else {
				return false;
			}
		} else {
			o.Fall();
			return true;
		}
	}

	public void Print() {
		System.out.println("\t" + this.name);
		System.out.println("\tSzomsz�dok:");
		if (!neighbors.isEmpty()) {
			for (int key : neighbors.keySet()) {
				System.out.println("\t\t" + key + ":" + neighbors.get(key).GetName());
			}
		}
		System.out.println("\tRajta l�vo �llat:");
		if (animal != null) {
			System.out.println("\t\t" + animal.name + ":" + animal.getClass());
		}
		System.out.println("\tRajta l�vo t�rgy:");
		if (thing != null)
			System.out.println("\t\t" + thing.name + ":" + thing.getClass());
		System.out.println("\t�llapot:");
		if (broken) {
			System.out.println("\t\tt�r�tt");
			System.out.println("\t\t" + steps);
		} else {
			System.out.println("\t\t�p");
			System.out.println("\t\t" + steps);
		}
	}

	public void Damage(int n) {
		steps += n;
		if (steps >= 20 || broken) {
			Break();
		}
	}

	public void Break() {
		broken = true;
		if (animal != null) {
			animal.Fall();
		}
	}

	public void JumpedOn() {
		Damage(5);
		System.out.println("jumpedon");
	}

	public int GetSteps() {
		return steps;
	}

	public void SetSteps(int steps) {
		this.steps = steps;
	}

	public boolean GetBroken() {
		return broken;
	}

	public void SetBroken(boolean broken) {
		this.broken = broken;
	}
}
