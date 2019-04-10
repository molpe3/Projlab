public abstract class Animal implements Printable{
	protected Panda pulled;
	protected Tile tile;
	protected String name;
	
	public void Fall() {
		Destroy();
	}
	public void Destroy(){
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this);
		if (pulled!=null) {
			Disband();
		}
	}
	public void Move(int side){
		
	}
	public boolean CollideWithPanda(Panda p){
		return false;
	}
	public boolean CollideWithOrangutan(Orangutan o){
		return false;
	}
	public void Disband(){
		if (pulled!=null){
			pulled.Disband();
		}
	}
	public Tile GetTile(){
		return tile;
	}
	public Panda GetPulled(){
		return pulled;
	}
	public void SetPulled(Panda p){
		pulled=p;
	}
	
	public void SetTile(Tile t) {
		tile=t;
	}
	public String GetName()
	{
		return name;
	}
}
