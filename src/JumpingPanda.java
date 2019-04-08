import java.util.Random;

public class JumpingPanda extends Panda{
	public JumpingPanda(String name) {
		super(name);
	}
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
