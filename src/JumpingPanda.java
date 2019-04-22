import java.util.Random;

/**
* Olyan panda, amelyik ugrik egyet, ha csokiautomata sípolását hallja.
*/

public class JumpingPanda extends Panda{
	public JumpingPanda(String name) {
		super(name);
	}
	
	/**
	* Calls occupied tile's JumpedOn method and prints out that the jump has happened.
	*/
	public void Jump(){
		System.out.println("jump");
		tile.JumpedOn();
	}
	
	/**
	* Randomly chooses a direction to step towards on every tick.
	*/
	public void Step() {
		Random rand=new Random();	//randomizálás ki-be kapcsolható legyen
		if (puller==null) {
			int sides=tile.GetSides();
			Move(rand.nextInt(sides));
		}
	}
	
	/**
	* When receiving a notification from a ChocolateMachine, checks adjacency.
	* If it's on the same or a neighboring tile, jumps.
	* @param ob the Observable that called its Update method
	*/
	public void Update(Observable ob) {
		System.out.println("asd");
		if (ob.getClass()==ChocolateMachine.class)
		{
			ChocolateMachine cm=(ChocolateMachine) ob;
			int side=tile.CompareTile(cm.GetTile());
			if (side==-1)	//ugyanazon a csempén van a panda és a csokiautomata
				Jump();
			else if (side>0)	//szomszéd csempén vannak
			{
				Jump();
			}
		}
	}
	
	/**
	* Prints out all relevant information
	* This includes name, occupied tile, puller and pulled.
	*/
	public void Print()
	{
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName());
		System.out.println("\tHúzó állat:");
		if (puller!=null)
			System.out.println("\t\t"+puller.GetName());
		System.out.println("\tHúzott panda:");
		if (pulled!=null)
			System.out.println("\t\t"+pulled.GetName());
	}
}
