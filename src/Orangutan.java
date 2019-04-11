public class Orangutan extends Animal {
	private int points;
	private int stepssincerobbed;
	public Orangutan(String name)
	{
		points=0;
		stepssincerobbed=0;
		this.name=name;
		pulled=null;
		tile=null;
	}
	public void Addpoint() {
		points++;}
	public void Move(int side) {
		stepssincerobbed++;
		Tile start=tile;
		Tile t2=tile.GetNeighbor(side);
		if (t2.AcceptOrangutan(this)) {	
			if (pulled!=null) {
				Tile t3=pulled.GetTile();
				if (t3!=null) {
					int a=t3.CompareTile(start);
					pulled.Move(a);
					if (!Game.GetActiveorangutans().contains(this)) {
						Disband();
					}
				}
			}
		}
	}
	public boolean CollideWithPanda(Panda p){
		if (pulled==null) {
			p.SetPuller(this);
			pulled=p;
		}
		return true;
	}
	public boolean CollideWithOrangutan(Orangutan o){ //itt kell megoldani a sorrablást
		
		if (pulled!=null&&o.stepssincerobbed>3){
			pulled.SetPuller(o);
			o.SetPulled(pulled);
			Tile t=tile;
			Tile ot=o.GetTile();
			o.SetTile(t);
			t.SetAnimal(o);
			tile=ot;
			ot.SetAnimal(this);
			stepssincerobbed=0;
		}
		return false;
	}
	public void LetGo()
	{
		Disband();
	}
	public int GetPoints() {
		return points;
	}
	public void SetPoints(int points)
	{
		this.points=points;
	}
	public int GetStepssincerobbed() {
		return stepssincerobbed;
	}
	public void SetStepssincerobbed(int stepssincerobbed) {
		this.stepssincerobbed = stepssincerobbed;
	}
	public void Print()
	{
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName());
		System.out.println("\tHúzott panda:");
		if (pulled!=null)
			System.out.println("\t\t"+pulled.GetName());
		System.out.println("\tRablás óta eltelt lépések:");
		System.out.println("\t\t"+stepssincerobbed);
		System.out.println("\tPontszám:");
		System.out.println("\t\t"+points);
	}
}
