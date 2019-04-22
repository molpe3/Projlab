/**
* Absztrakt ősosztály az állatoknak.
*/

public abstract class Animal implements Printable{
	protected Panda pulled;
	protected Tile tile;
	protected String name;
	
	/**
	* Animals die when they fall, calling their Destroy() method
	*/
	public void Fall() {
		Destroy();
	}
	
	/**
	* removes animal from the tile it occupies
	* animal is then deleted from the game
	*/
	public void Destroy(){
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this);
	}
	
	/**
	* subclasses implement this functionality
	*/
	public void Move(int side){
		
	}
	
	/**
	* the default behavior is returning false, thus preventing movement
	*/
	public boolean CollideWithPanda(Panda p){
		return false;
	}
	
	/**
	* the default behavior is returning false, thus preventing movement
	*/
	public boolean CollideWithOrangutan(Orangutan o){
		return false;
	}
	
	/**
	* disband cascades through pulled pandas
	*/
	public void Disband(){
		if (pulled!=null){
			pulled.Disband();
		}
	}
	
	/**
	* returns with the tile the animal is occupying
	* @ret tile the tile the animal is standing on
	*/
	public Tile GetTile(){
		return tile;
	}
	
	/**
	* returns with pulled panda
	* @ret pulled the panda this animal is pulling
	*/
	public Panda GetPulled(){
		return pulled;
	}
	
	/**
	* sets pulled panda 
	* @param p the panda it's starting to pull
	*/
	public void SetPulled(Panda p){
		pulled=p;
	}
	
	/**
	* sets occupied tile
	* @param t the tile the animal's moving to
	*/
	public void SetTile(Tile t) {
		tile=t;
	}
	
	/**
	* returns with the animal's name
	* @ret name the name
	*/
	public String GetName()
	{
		return name;
	}
}
