import java.io.IOException;

public class SlotMachine extends Thing {

	public void Step() {
		DeRandom rand = new DeRandom();
		if (rand.next(5, this) == 0) { // Jingles on average every other turn
			Jingle();
		}
	}

	public void Print() {
		System.out.println("\t" + this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t" + tile.GetName() + ":" + tile.getClass());
		System.out.println("\tObserverek:");
		for (Observer o : observers) {
			System.out.println("\t\t" + o.GetName());
		}
	}

	@Override
	public int AskForRandomNumber() throws IOException {
		Main.out.write("Should the SlotMachine " + this.name + " jingle?");
		Main.out.newLine();
		Main.out.write("(0: yes, 1-4: no)");
		Main.out.newLine();
		String temp;
		Integer number = -1;
		while (number.equals(-1)) {
			temp = Main.in.readLine();
			try {
				number = Integer.parseInt(temp);
				if (number.compareTo(0) <= 0 && number.compareTo(4) >= 0) {
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

	public void Jingle() {
		Notify();
	}

	public void Notify() {
		for (Observer o : observers) {
			o.Update(this);
		}
	}

	public void Detach(Observer o) {
		observers.remove(o);
	}
}
