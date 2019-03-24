import java.util.ArrayList;
public final class Game {
	private static Game game;
	private Game() {}
	
	public void EndGame() {
	System.out.println("EndGame()");
	}
	public void CheckEndGame(ArrayList<Orangutan> orangutans,ArrayList<Panda> pandas) {
		System.out.println("CheckEndGame()");
		if (orangutans.isEmpty()||pandas.isEmpty()){
			EndGame();
		}
	}
	public void DeleteAnimal(Animal a,ArrayList<Orangutan> orangutans,ArrayList<Panda> pandas) {
		System.out.println("DeleteAnimal(Animal a)");
		orangutans.remove(a);
		pandas.remove(a);
		CheckEndGame(orangutans,pandas);
	}
	public Timer GetTimer(Timer timer) {
		System.out.println("GetTimer(Timer timer)");
		return timer;
	}
	public static Game getInstance() {
	System.out.println("getInstance()");
        if(game == null) {
            game = new Game();
        }
         
        return game;
    }
}
