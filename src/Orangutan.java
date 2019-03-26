public class Orangutan extends Animal {
	private int points;
	public void Addpoint() {
		points++;}
	public void Move(int side) {
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
	public boolean CollideWithOrangutan(Orangutan o){
		return false;
	}
}
