
public class TiredPanda extends Panda {
	private boolean tired;
	public void Sit() {
		if (puller!=null||pulled!=null)
			Disband();	//ha volt sora, az felbomlik
	}
	public void Update(Chair ob) {
		if (tired)	//ha f�radt a panda
		{
			int side=tile.CompareTile(ob.GetTile());
			if (side==-1)	//ha ugyanazon a csemp�n van a panda �s a fotel
			{
				Sit();
			}
			else if (side>0)	//ha szomsz�dos csemp�n vannak
			{
				Sit();
			}
		}
	}
}
