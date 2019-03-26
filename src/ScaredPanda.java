import java.util.Random;
public class ScaredPanda extends Panda {
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
}
