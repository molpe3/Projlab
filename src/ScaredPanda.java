import java.util.Random;

/**
* Megijed, ha játékgép csilingel mellette. Ilyenkor elengedi a fogott állato(ka)t és felbomlik a sor.
*/
public class ScaredPanda extends Panda {
	public ScaredPanda(String name) {
		super(name);
	}
	
	/**
	* Lets go of its puller and pulled if applicable.
	*/
	public void GetScared(){
		if (puller!=null)
			Disband();	//Ha sorban volt, az felbomlik
	}
	
	/**
	* Checks whether the calling SlotMachine is on the same or on a neighboring tile.
	* If yes, it gets scared.
	* @param ob the observer that called the update
	*/
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
	
	/**
	* Prints all relevant information.
	* This includes name, tile, puller and pulled.
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
