public class Orangutan extends Animal {
	private int points;
	private int stepssincerobbed;
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
	public boolean CollideWithOrangutan(Orangutan o){ //itt kell megoldani a sorrablást
		return false;
	}
	public void LetGo()
	{
		stepssincerobbed=0;
		Disband();
	}
}
