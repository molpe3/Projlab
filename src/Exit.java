/**
* A pálya kijárata.
* A rajta átmenő orángutánokat atrakja a bejáratra, és pontokat ad neki minden húzott panda után, amiket ezután eltávolít.
*/
public class Exit extends Tile{
	public Exit(String name) {
		super(name);
	}
	private Tile entrance;
	
	/**
	* Arbitrates orangutan movement to the exit.
	* Orangutans that enter collide with occupying animal first.
	* Then, if the orangutan entered successfully, we attempt to move it to the entrance.
	* If movement to the entrance is successful, we remove the pandas pulled by the orangutan and award 1 point for each.
	*/
	public boolean AcceptOrangutan(Orangutan o) {	
		if (animal!=null){
			animal.CollideWithOrangutan(o);
			return false;
		}
		o.tile.RemoveAnimal();
		if (entrance.AcceptOrangutan(o)){
			if (o.pulled!=null) {
				Panda p=o.pulled;
				while (p!=null) {
					o.Addpoint();
					p.Destroy();
					p.GetTile().SetAnimal(null);
					p.SetTile(null);
					if (p.pulled!=null) {
						p=p.pulled;
					}
					else {
						p=null;
					}
				}
			}
			return true;
		}
		o.tile=this;
		animal=o;
		return true;
	}
	
	/**
	* Prints out relevant information.
	* This covers name, neighbors, occupying animal and thing, and the entrance.
	*/
	public void Print() {
		System.out.println("\t"+this.name);
		System.out.println("\tSzomszédok:");
		if (!neighbors.isEmpty()) {
			for (int key:neighbors.keySet()){
				System.out.println("\t\t"+key+":"+neighbors.get(key).GetName());
			}
		}
		System.out.println("\tRajta lévo állat:");
		if (animal!=null) {
			System.out.println("\t\t"+animal.GetName()+":"+animal.getClass());
		}
		System.out.println("\tRajta lévo tárgy:");
		if (thing!=null)
			System.out.println("\t\t"+thing.GetName()+":"+thing.getClass());
		System.out.println("\tBejárat:");
		System.out.println("\t\t"+entrance.GetName());
	}
	
	/**
	* returns with the entrance
	* @return entrance the entrance orangutans move back to after they exit
	*/
	public Tile GetEntrance() {
		return entrance;
	}
	
	/**
	* sets the entrance
	* @param entrance the tile to be set as entrance
	*/
	public void SetEntrance(Tile entrance) {
		this.entrance = entrance;
	}
}
