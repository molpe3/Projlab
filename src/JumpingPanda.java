import java.util.Random;

public class JumpingPanda extends Panda{
	public JumpingPanda(String name) {
		super(name);
	}
	public void Jump(){
		tile.JumpedOn();
	}
	public void Step() {
		Random rand=new Random();	//randomiz�l�s ki-be kapcsolhat� legyen
		if (puller==null) {
			int sides=tile.GetSides();
			Move(rand.nextInt(sides));
		}
	}
	public void Update(ChocolateMachine ob) {
		int side=tile.CompareTile(ob.GetTile());
		if (side==-1)	//ugyanazon a csemp�n van a panda �s a csokiautomata
			Jump();
		else if (side>0)	//szomsz�d csemp�n vannak
		{
			Jump();
		}
	}
	public void Print()
	{
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName());
		System.out.println("\tH�z� �llat:");
		if (puller!=null)
			System.out.println("\t\t"+puller.GetName());
		System.out.println("\tH�zott panda:");
		if (pulled!=null)
			System.out.println("\t\t"+pulled.GetName());
	}
}
