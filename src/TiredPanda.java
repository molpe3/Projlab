import java.util.Random;

public class TiredPanda extends Panda {
	public TiredPanda(String name) {
		super(name);
	}
	private boolean tired=false;
	private int sittingsince=0;
	private Chair chair=null;
	public void Sit() {
		
		sittingsince=1;
		tile.RemoveAnimal();
		tile=chair.tile;
		chair.SetOccupied(true);
		chair.GetTile().SetAnimal(this);
		
		if (puller!=null||pulled!=null)
			Disband();	//ha volt sora, az felbomlik
		
	}
	public void Step() { 
		Random rand=new Random();
		if (puller==null&&sittingsince==0) {	//randomizálás ki/be kapcsolható legyen
			//int sides=tile.GetSides();
			//Move(rand.nextInt(sides));
		}
		if (sittingsince>0) {
			sittingsince++;
			if (sittingsince>3) {
				sittingsince=0;
				chair.SetOccupied(false);
				chair=null;
				tired=false;
			}
		}
		if (!tired&&sittingsince==0) {
			if (rand.nextInt(10)<2) {
				tired=true;
			}
		}
	}
	public void Update(Observable o) {
		Chair ob=(Chair)o;
		if (tired)	//ha fáradt a panda
		{
			int side=tile.CompareTile(ob.GetTile());
			if (side==-1)	//ha ugyanazon a csempén van a panda és a fotel
			{
				chair=ob;
				Sit();
			}
			else if (side>0)	//ha szomszédos csempén vannak
			{
				chair=ob;
				Sit();
				
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
}
