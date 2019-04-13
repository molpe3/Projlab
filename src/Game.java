import java.util.ArrayList;
import java.util.Comparator;

public final class Game implements Printable {
	private static Game game;
	private Timer timer;
	private static ArrayList<Orangutan> activeorangutans = new ArrayList<Orangutan>();
	private static ArrayList<Orangutan> orangutans = new ArrayList<Orangutan>();
	private static ArrayList<Panda> pandas = new ArrayList<Panda>();
	private static ArrayList<Tile> tiles = new ArrayList<Tile>();
	private static ArrayList<Thing> things = new ArrayList<Thing>();
	private Tile entrance;

	public void EndGame() {
		System.out.println("J�t�k v�ge!");
		orangutans.sort(Comparator.comparing(Orangutan::GetPoints));
		int i = 1;
		for (Orangutan o : orangutans) {
			System.out.println("\n\t" + i + ". " + o.name + ":" + o.GetPoints());
		}
	}

	public void CheckEndGame() {
		if (activeorangutans.isEmpty() || pandas.isEmpty()) {
			EndGame();
		}
	}

	public void DeleteAnimal(Animal a) {
		activeorangutans.remove(a);
		pandas.remove(a);
		CheckEndGame();
	}

	public void AddOrangutan(Orangutan o) {
		activeorangutans.add(o);
		orangutans.add(o);
	}

	public void AddPanda(Panda p) {
		pandas.add(p);
	}

	public void AddTile(Tile t) {
		tiles.add(t);
	}

	public Tile GetEntrance() {
		return entrance;
	}

	public void AddThing(Thing t) {
		things.add(t);
	}

	public void SetEntrance(Tile entrance) {
		this.entrance = entrance;
	}

	public void SetTimer(Timer timer) {
		this.timer = timer;
	}

	public Timer GetTimer() {
		return timer;
	}

	public static ArrayList<Orangutan> GetActiveorangutans() {
		return activeorangutans;
	}

	public static ArrayList<Panda> GetPandas() {
		return pandas;
	}

	public void Print() {
		System.out.println("Randomiz�l�s:");
		System.out.println("\tBe/ki");
		System.out.println("Csemp�k");
		System.out.println("Sima Csemp�k");
		for (Tile t : tiles) {
			if (t.getClass() == Tile.class) {
				t.Print();
			}
		}
		System.out.println("Gyenge Csemp�k");
		for (Tile t : tiles) {
			if (t.getClass() == WeakTile.class) {
				t.Print();
			}
		}
		System.out.println("Kij�ratok:");
		for (Tile t : tiles) {
			if (t.getClass() == Exit.class) {
				t.Print();
			}
		}
		System.out.println("Szekr�nyek:");
		for (Tile t : tiles) {
			if (t.getClass() == Wardrobe.class) {
				t.Print();
			}
		}
		System.out.println("�llatok:");
		System.out.println("Pand�k:");
		System.out.println("Ijedos pand�k:");
		for (Panda p : pandas) {
			if (p.getClass() == ScaredPanda.class) {
				p.Print();
			}
		}
		System.out.println("Ugr� pand�k:");
		for (Panda p : pandas) {
			if (p.getClass() == JumpingPanda.class) {
				p.Print();
			}
		}
		System.out.println("F�rad�kony pand�k:");
		for (Panda p : pandas) {
			if (p.getClass() == TiredPanda.class) {
				p.Print();
			}
		}
		System.out.println("Or�ngut�nok:");
		for (Orangutan o : activeorangutans) {
			if (o.getClass() == Orangutan.class) {
				o.Print();
			}
		}
		System.out.println("T�rgyak:");
		System.out.println("Fotelek:");
		for (Thing t : things) {
			if (t.getClass() == Chair.class) {
				t.Print();
			}
		}
		System.out.println("J�t�kg�pek:");
		for (Thing t : things) {
			if (t.getClass() == SlotMachine.class) {
				t.Print();
			}
		}
		System.out.println("Csokiautomat�k:");
		for (Thing t : things) {
			if (t.getClass() == ChocolateMachine.class) {
				t.Print();
			}
		}
		CheckEndGame();
	}

	private Game() {
	}

	public static Game getInstance() {
		return game;
	}
}
