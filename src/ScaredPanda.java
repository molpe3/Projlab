import java.io.IOException;

public class ScaredPanda extends Panda {

	public void GetScared() {
		if (puller != null)
			Disband(); // Ha sorban volt, az felbomlik
	}

	public void Update(Observable o) {
		SlotMachine sm = (SlotMachine) o;
		int side = tile.CompareTile(sm.GetTile());
		if (side == -1) { // ha azonos csempén van a panda és a játékgép
			GetScared();
		} else if (side > 0) { // ha szomszédosak
			GetScared();
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

	}
	
	@Override
	public int AskForRandomNumber() throws IOException {
		Main.out.write("Where should the ScaredPanda " + this.name + " move?");
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
}
