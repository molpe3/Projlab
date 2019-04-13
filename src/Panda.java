import java.io.IOException;
import java.util.ArrayList;

public class Panda extends Animal implements Observer, Steppable {
	protected Animal puller;
	private ArrayList<Observable> observables = new ArrayList<Observable>();

	public void Move(int side) {
		Tile start = tile;
		Tile t2 = tile.GetNeighbor(side);
		if (t2.AcceptPanda(this)) {
			tile.RemoveAnimal();
			tile = t2;
			t2.animal = this;
			if (pulled != null) {
				Tile t3 = pulled.GetTile();
				int a = t3.CompareTile(start);
				pulled.Move(a);
				if (!Game.GetPandas().contains(this)) {
					Disband();
				}
			}
		}
	}

	public boolean CollideWithOrangutan(Orangutan o) {
		if (puller == null && o.GetStepsSinceRobbed() > 3) {
			CaughtbyOrangutan(o);
			return true;
		} else {
			return false;
		}
	}

	public void CaughtbyOrangutan(Orangutan o) {
		Panda lead_panda = o.GetPulled();
		o.SetPulled(this);
		SetPuller(o);
		if (lead_panda != null) {
			lead_panda.SetPuller(this);
			this.SetPulled(lead_panda);
		}
		tile = o.GetTile();
		tile.SetAnimal(this);
	}

	public void Destroy() {
		for (Observable o : observables) {
			o.Detach(this);
			Game.getInstance().GetTimer().RemoveSteppable(this);
		}
		tile.RemoveAnimal();
		if (pulled != null) {
			Disband();
		}
		Game.getInstance().DeleteAnimal(this);
	}

	public void Step() {
		DeRandom rand = new DeRandom();
		if (puller == null) {
			Move(rand.next(tile.GetSides(), this));
		}
	}

	public void Disband() {
		if (puller != null)
			puller.SetPulled(null);
		puller = null;
		if (pulled != null) {
			pulled.Disband();
		}
	}

	public void Print() {
		// TODO
	}

	@Override
	public int AskForRandomNumber() throws IOException {
		Main.out.write("Where should the Panda " + this.name + " move?");
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

	@Override
	public String GetName() {
		return name;
	}

	public boolean CollideWithPanda(Panda p) {
		return false;
	}
	
	public Animal GetPuller() {
		return puller;
	}

	public void SetPuller(Animal a) {
		puller = a;
	}
	
	public void GetScared() {}

	public void Jump() {}

	public void Sit() {}

	public void Update(Observable ob) {}
}
