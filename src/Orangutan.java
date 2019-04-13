import java.io.IOException;

public class Orangutan extends Animal {
	private int points = 0;
	private int stepssincerobbed = 0;

	public void Move(int side) {
		if (side != 0) {
			Tile t2 = tile.GetNeighbor(side);
			if (t2.AcceptOrangutan(this)) {
				tile.RemoveAnimal();
				t2.SetAnimal(this);
				tile = t2;
				if (pulled != null) {
					Tile t3 = pulled.GetTile();
					int a = tile.CompareTile(t3);
					pulled.Move(a);
				}
				stepssincerobbed++; // Only add to the counter if the step is actually taken
			}
		}
	}

	public boolean CollideWithPanda(Panda p) {
		if (stepssincerobbed >= 3) {
			p.CaughtbyOrangutan(this);
		}
		return true;
	}

	public boolean CollideWithOrangutan(Orangutan attacker) {
		if (attacker.GetStepsSinceRobbed() >= 3 && attacker.GetPulled() == null) {
			tile = attacker.GetTile();
			tile.SetAnimal(this);
			pulled.SetPuller(attacker);
			attacker.SetPulled(pulled);
			// the Move() of the other panda puts it on the former tile of this
			stepssincerobbed = 0;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int AskForRandomNumber() throws IOException {
		Main.out.write("Where should the Orangutan " + this.name + " move?");
		Main.out.newLine();
		Main.out.write("(0: stay");
		Tile neighbor;
		int nOfNeighbors = tile.GetSides();
		for (int i = 1; i <= nOfNeighbors; ++i) {
			neighbor = tile.GetNeighbor(i);
			Main.out.write(", " + String.valueOf(i) + ": " + neighbor.name);
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

	public void Print() {
		System.out.println("\t" + this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t" + tile.GetName());
		System.out.println("\tHúzott panda:");
		if (pulled != null)
			System.out.println("\t\t" + pulled.GetName());
		System.out.println("\tRablás óta eltelt lépések:");
		System.out.println("\t\t" + stepssincerobbed);
		System.out.println("\tPontszám:");
		System.out.println("\t\t" + points);
	}

	public void Addpoint() {
		++points;
	}

	public void LetGo() {
		Disband();
	}

	public int GetPoints() {
		return points;
	}

	public void SetPoints(int points) {
		this.points = points;
	}

	public int GetStepsSinceRobbed() {
		return stepssincerobbed;
	}

	public void SetStepssincerobbed(int stepssincerobbed) {
		this.stepssincerobbed = stepssincerobbed;
	}
}
