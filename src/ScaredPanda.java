import java.util.Random;
public class ScaredPanda extends Panda {
	public ScaredPanda(String name) {
		super(name);
	}
	public void GetScared(){
		if (puller!=null)
			Disband();	//Ha sorban volt, az felbomlik
	}
	public void Update(SlotMachine sm) {
		int side=tile.CompareTile(sm.GetTile());
		if (side==-1) //ha azonos csemp�n van a panda �s a j�t�kg�p
		{
			GetScared();
		}
		else if (side>0)	//ha szomsz�dosak
		{
			GetScared();
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
