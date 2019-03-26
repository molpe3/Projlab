import java.util.ArrayList;
public final class Game {
	private static Game game;
	private Game() {}
	private Timer timer;
	private ArrayList<Orangutan> orangutans;
	private ArrayList<Panda> pandas;
	private ArrayList<Tile> tiles;
	private Tile entrance;
	public void EndGame() {}
	public void CheckEndGame() {
		if (orangutans.isEmpty()||pandas.isEmpty()){
			EndGame();
		}
	}
	public void DeleteAnimal(Animal a) {
		orangutans.remove(a);
		pandas.remove(a);
		CheckEndGame();
	}
	public Timer GetTimer() {
		return timer;
	}
	public static Game getInstance() {
        if(game == null) {
            game = new Game();
        }
        return game;
    }
}

