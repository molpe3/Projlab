import java.util.Random;
public class ScaredPanda extends Panda {
	public void GetScared(){
		if (puller!=null)
			Disband();	//Ha sorban volt, az felbomlik
	}
	public void Update(SlotMachine sm) {
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
}
