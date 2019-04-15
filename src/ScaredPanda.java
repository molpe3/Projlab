import java.util.Random;
public class ScaredPanda extends Panda {
	public ScaredPanda(String name) {
		super(name);
	}
	public void GetScared(){
		if (puller!=null)
			Disband();	//Ha sorban volt, az felbomlik
	}
	public void Update(Observable o) {
		SlotMachine sm=(SlotMachine) o;
		int side=tile.CompareTile(sm.GetTile());
		if (side==-1) //ha azonos csempén van a panda és a játékgép
		{
			GetScared();
		}
		else if (side>0)	//ha szomszédosak
		{
			GetScared();
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
