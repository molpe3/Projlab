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
			tile.RemoveAnimal();
			t2.SetAnimal(this);
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
	public boolean CollideWithOrangutan(Orangutan o){ //itt kell megoldani a sorrabl�st
		return false;
	}
	public void LetGo()
	{
		stepssincerobbed=0;
		Disband();
	}
	public void Print()
	{
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName());
		System.out.println("\tH�zott panda:");
		if (pulled!=null)
			System.out.println("\t\t"+pulled.GetName());
		System.out.println("\tRabl�s �ta eltelt l�p�sek:");
		System.out.println("\t\t"+stepssincerobbed);
		System.out.println("\tPontsz�m:");
		System.out.println("\t\t"+points);
	}
}
