import java.util.ArrayList;
import java.util.Comparator;

/**
* A játék inditásáért, befejezéséért és tartalmazásáért felelős singleton.
*/

public final class Game implements Printable{
	private static Game game;
	private Game() {}
	protected Timer timer;
	protected static ArrayList<Orangutan> activeorangutans;
	protected static ArrayList<Orangutan> orangutans;
	protected static ArrayList<Panda> pandas;
	protected static ArrayList<Tile> tiles;
	protected static ArrayList<Thing> things;
	protected Tile entrance;
	
	/**
	* Prints out that the game has ended, then sorts orangutans by score and prints out their name and score.
	*/
	public void EndGame() {
		System.out.println("Játék vége!");
		orangutans.sort(Comparator.comparing(Orangutan::GetPoints));
		int i=1;
		for (Orangutan o:orangutans) {
			System.out.println("\n\t"+i+". "+o.GetName()+":"+o.GetPoints());
			//itt nem kéne i-t inkrementálni
		}
	}
	
	/**
	* Checks whether the game has ended
	* The game is over when there are no more active orangutans or pandas in the game.
	*/
	public void CheckEndGame() {
		if (activeorangutans.isEmpty()||pandas.isEmpty()){
			EndGame();
		}
	}
	
	/**
	* Deletes an animal, then checks whether the game should end
	* @param a the animal to delete
	*/
	public void DeleteAnimal(Animal a) {
		activeorangutans.remove(a);
		pandas.remove(a);
		CheckEndGame();
	}
	
	/**
	* returns with the current instance of game
	* if there's no active game instance, it creates a new one
	* @return game the game instance
	*/
	public static Game getInstance() {
        if(game == null) {
            game = new Game();
            activeorangutans=new ArrayList<Orangutan>();
            orangutans=new ArrayList<Orangutan>();
            pandas=new ArrayList<Panda>();
            tiles=new ArrayList<Tile>();
            things=new ArrayList<Thing>();
        }
        return game;
    }
	/**
	* Adds an orangutan to the list of active orangutans.
	* @param o the orangutan to be added
	*/
	public void AddOrangutan(Orangutan o) {
		activeorangutans.add(o);
		orangutans.add(o);
	}
	
	/**
	* Adds a panda to the list of pandas.
	* @param p the panda to be added
	*/
	public void AddPanda(Panda p) {
		pandas.add(p);
	}
	
	/**
	* Adds a tile to the list of tiles.
	* @param t the tile to be added
	*/
	public void AddTile(Tile t) {
		tiles.add(t);
	}
	
	/**
	* returns with the entrance
	* @return entrance the entrance orangutans return to after exiting
	*/
	public Tile GetEntrance() {
		return entrance;
	}
	
	/**
	* Adds a thing (chocolate machine, slot machine or chair) to the list of things.
	* @param t the thing to be added
	*/
	public void AddThing(Thing t) {
		things.add(t);
	}
	
	/**
	* sets the entrance
	* @param entrance the tile we want to turn into the new entrance
	*/
	public void SetEntrance(Tile entrance) {
		this.entrance=entrance;
	}
	
	/**
	* sets the timer
	* @param timer the timer that governs the behavior of steppables
	*/
	public void SetTimer(Timer timer) {
		this.timer = timer;
	}
	
	/**
	* returns with the timer
	* @return timer the timer that governs the behavior of steppables
	*/
	public Timer GetTimer() {
		return timer;
	}
	
	/**
	* returns with a list of active orangutans
	* orangutans are active if they haven't died yet
	* @return activeorangutans returns with an ArrayList containing active orangutans
	*/
	public static ArrayList<Orangutan> GetActiveorangutans() {
		return activeorangutans;
	}
	
	/**
	* returns with a list of pandas
	* @return pandas returns with an ArrayList containing all pandas currently in the game
	*/
	public static ArrayList<Panda> GetPandas() {
		return pandas;
	}
	
	/**
	* Prints out all relevant information.
	* This includes randomization status, tiles listed by type, animals listed by type and things listed by type.
	*/
	public void Print() {
		System.out.println("Randomizálás:");
		System.out.println("\tBe/ki");
		System.out.println("Csempék");
		System.out.println("Sima Csempék");
		for (Tile t:tiles) {
			if (t.getClass()==Tile.class) {
				t.Print();
			}
		}
		System.out.println("Gyenge Csempék");
		for (Tile t:tiles) {
			if (t.getClass()==WeakTile.class) {
				t.Print();
			}
		}
		System.out.println("Kijáratok:");
		for (Tile t:tiles) {
			if (t.getClass()==Exit.class) {
				t.Print();
			}
		}
		System.out.println("Szekrények:");
		for (Tile t:tiles) {
			if (t.getClass()==Wardrobe.class) {
				t.Print();
			}
		}
		System.out.println("Állatok:");
		System.out.println("Pandák:");
		System.out.println("Ijedos pandák:");
		for (Panda p:pandas) {
			if (p.getClass()==ScaredPanda.class) {
				p.Print();
			}
		}
		System.out.println("Ugró pandák:");
		for (Panda p:pandas) {
			if (p.getClass()==JumpingPanda.class) {
				p.Print();
			}
		}
		System.out.println("Fáradékony pandák:");
		for (Panda p:pandas) {
			if (p.getClass()==TiredPanda.class) {
				p.Print();
			}
		}
		System.out.println("Orángutánok:");
		for (Orangutan o:activeorangutans) {
			if (o.getClass()==Orangutan.class) {
				o.Print();
			}
		}
		System.out.println("Tárgyak:");
		System.out.println("Fotelek:");
		for (Thing t:things) {
			if (t.getClass()==Chair.class) {
				t.Print();
			}
		}
		System.out.println("Játékgépek:");
		for (Thing t:things) {
			if (t.getClass()==SlotMachine.class) {
				t.Print();
			}
		}
		System.out.println("Csokiautomaták:");
		for (Thing t:things) {
			if (t.getClass()==ChocolateMachine.class) {
				t.Print();
			}
		}
		CheckEndGame();	
	}
}

