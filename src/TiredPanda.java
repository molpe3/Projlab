import java.io.IOException;

public class TiredPanda extends Panda {
	private boolean tired = false;
	private int sittingsince = 0;
	private Chair chair;

	public void Sit() {
		sittingsince = 1;
		tile.RemoveAnimal();
		tile = chair.tile;
		chair.SetOccupied(true);
		chair.GetTile().SetAnimal(this);

		if (puller != null || pulled != null) {
			Disband(); // ha volt sora, az felbomlik
		}
	}

	public void Step() {
		DeRandom rand = new DeRandom();
		if (puller == null && sittingsince == 0) { // randomizálás ki/be kapcsolható legyen
			// int sides=tile.GetSides();
			// Move(rand.nextInt(sides));
		}
		if (sittingsince > 0) {
			sittingsince++;
			if (sittingsince > 3) {
				sittingsince = 0;
				chair.SetOccupied(false);
				chair = null;
				tired = false;
			}
		}
		if (!tired && sittingsince == 0) {
			if (rand.next(5, this) == 0) {
				tired = true;
			}
		}
	}

	public void Update(Observable o) {
		Chair ob = (Chair) o;
		if (tired) {// ha fáradt a panda
			int side = tile.CompareTile(ob.GetTile());
			if (side == -1) { // ha ugyanazon a csempén van a panda és a fotel
				chair = ob;
				Sit();
			} else if (side > 0) {// ha szomszédos csempén vannak
				chair = ob;
				Sit();
			}
		}
	}

	public void Print() {
		System.out.println("\t" + this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t" + tile.GetName());
		System.out.println("\tHúzó állat:");
		if (puller != null)
			System.out.println("\t\t" + puller.name);
		System.out.println("\tHúzott panda:");
		if (pulled != null)
			System.out.println("\t\t" + pulled.GetName());
		System.out.println("\tÁllapot:");
		if (tired) {
			System.out.println("\t\tFáradt");
			if (sittingsince > 0) {
				System.out.println("\t\tÜl:" + sittingsince + ":" + chair.name);
			} else {
				System.out.println("\t\tnem ül");
			}
		} else {
			System.out.println("\t\tNem fáradt");
			if (sittingsince > 0) {
				System.out.println("\t\tÜl:" + sittingsince + ":" + chair.name);
			} else {
				System.out.println("\t\tnem ül");
			}
		}
	}

	@Override
	public int AskForRandomNumber() throws IOException {
		Main.out.write("Where should the TiredPanda " + this.name + " move?");
		Main.out.newLine();
		Main.out.write("(0: nowhere");
		int nOfNeighbors = tile.GetSides();
		for (int i = 1; i <= nOfNeighbors; ++i) {
			Main.out.write(", " + String.valueOf(i) + ": " + tile.GetNeighbor(i).name);
		}
		Main.out.write(")");
		Main.out.newLine();

		String temp;
		Integer number = -1;
		while (number.equals(-1)) {
			temp = Main.in.readLine();
			try {
				number = Integer.parseInt(temp);
				if (number.compareTo(0) <= 0 && number.compareTo(nOfNeighbors) >= 0) {
					return number;
				} else {
					number = -1;
				}
			} catch (NumberFormatException expectedUserThings) {
				// user wrote not a number, that's okay, continue
			}
		}
		return 0; // should never reach
	}

	public int GetSittingsince() {
		return sittingsince;
	}

	public void SetSittingsince(int sittingsince) {
		this.sittingsince = sittingsince;
	}

	public Chair GetChair() {
		return chair;
	}

	public void SetChair(Chair chair) {
		this.chair = chair;
	}

	public boolean GetTired() {
		return tired;
	}

	public void SetTired(boolean tired) {
		this.tired = tired;
	}
}
