import java.util.Random;

public class JumpingPanda extends Panda{
	public void Jump(){
		tile.JumpedOn();
	}
	public void Step() {
		Random rand=new Random();	//randomizálás ki-be kapcsolható legyen
		if (puller==null) {
			int sides=tile.GetSides();
			Move(rand.nextInt(sides));
		}
	}
	public void Update(ChocolateMachine ob) {
		int side=tile.CompareTile(ob.GetTile());
		if (side==-1)	//ugyanazon a csempén van a panda és a csokiautomata
			Jump();
		else if (side>0)	//szomszéd csempén vannak
		{
			Jump();
		}
	}
}
