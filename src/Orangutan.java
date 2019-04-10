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
		Tile t2=tile.GetNeighbor(side);
		if (t2.AcceptOrangutan(this)) {	
			if (pulled!=null) {
				Tile t3=pulled.GetTile();
				int a=tile.CompareTile(t3);
				pulled.Move(a);
			}
		}
	}
	public boolean CollideWithPanda(Panda p){
			p.CaughtbyOrangutan(this);
			return false;
		}
	public boolean CollideWithOrangutan(Orangutan o){ //itt kell megoldani a sorrablást
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
