
public class TiredPanda extends Panda {
	public TiredPanda(String name) {
		super(name);
	}
	private boolean tired=false;
	private int sittingsince=0;
	private Chair chair=null;
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
		System.out.println("\tÁllapot:");
		if (tired){
			System.out.println("\t\tFáradt");
			if (sittingsince>0){
				System.out.println("\t\tÜl:"+sittingsince+":"+chair.GetName());
			}
			else{
				System.out.println("\t\tnem ül");
			}
		}
		else{
			System.out.println("\t\tNem fáradt");
			if (sittingsince>0){
				System.out.println("\t\tÜl:"+sittingsince+":"+chair.GetName());
			}
			else{
				System.out.println("\t\tnem ül");
			}
		}
	}
	public int GetSittingsince() {
		return sittingsince;
	}
	public void SetSittingsince(int sittingsince) {
		this.sittingsince = sittingsince;
	}
	public Chair GetChair() {
		return chair;
	}
	public void SetChair(Chair chair) {
		this.chair = chair;
	}
	public boolean GetTired()
	{
		return tired;
	}
	public void SetTired(boolean tired)
	{
		this.tired=tired;
	}
}
