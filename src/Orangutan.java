/**
* A játékos utasításainak megfelelően mozog. Ha szabad pandával azonos mezőre lép, megfogja azt.
* A pandák kijárathoz vitelével pontokat szerez. A pandákat bármikor elengedheti.
* Ha más orángutánnal ütközik, elrabolja a pandáit. A másik orángután ilyenkor 3 lépés megtételéig nem szerezhet pandákat
*/

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
	
	/**
	* Increases the orangutan's points by 1.
	*/
	public void Addpoint() {
		points++;}
	
	/**
	* Moves the orangutan to an adjacent tile.
	* @param side the current tile's side that is adjacent to the target tile
	*/
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
	
	/**
	* Collides with a panda, potentially adding it to the pulled queue
	* @param p the panda it's colliding with
	*/
	public boolean CollideWithPanda(Panda p){
		p.CaughtbyOrangutan(this);
		return true;
	}
	
	/**
	* Collides with an orangutan.
	* The other orangutan may steal its pandas if it made at least 3 steps since it was robbed last time.
	* @param o the orangutan it's running into
	*/
	public boolean CollideWithOrangutan(Orangutan o){ 
		
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
	
	/**
	* Lets go of all pulled pandas
	*/
	public void LetGo()
	{
		Disband();
	}
	
	/**
	* returns with the orangutan's score
	* @return points the number of points the orangutan has gathered
	*/
	public int GetPoints() {
		return points;
	}
	
	/**
	* sets the orangutan's number of points
	* @param points the value to be set
	*/
	public void SetPoints(int points)
	{
		this.points=points;
	}
	
	/**
	* returns with the number of steps the orangutan made since another stole its pandas
	* @return stepssincerobbed the number of steps it has made since the theft
	*/
	public int GetStepssincerobbed() {
		return stepssincerobbed;
	}
	
	/**
	* sets the number of steps the orangutan made since its pandas were stolen
	* @param stepssincerobbed the new number of steps made since it was robbed
	*/
	public void SetStepssincerobbed(int stepssincerobbed) {
		this.stepssincerobbed = stepssincerobbed;
	}
	
	/**
	* prints all relevant information
	* this includes name, tile, pulled, number of steps since it was robbed, and score
	*/
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
