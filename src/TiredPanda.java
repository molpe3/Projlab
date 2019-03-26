
public class TiredPanda extends Panda {
	private boolean tired;
	public void Sit() {
		if (puller!=null||pulled!=null)
			Disband();	//ha volt sora, az felbomlik
	}
	public void Update(Chair ob) {
		if (tired)	//ha fáradt a panda
		{
			int side=tile.CompareTile(ob.GetTile());
			if (side==-1)	//ha ugyanazon a csempén van a panda és a fotel
			{
				Sit();
			}
			else if (side>0)	//ha szomszédos csempén vannak
			{
				Sit();
			}
		}
	}
}
