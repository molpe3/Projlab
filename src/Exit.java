
public class Exit extends Tile{
	public Exit(String name) {
		super(name);
	}
	private Tile entrance;
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
	public Tile GetEntrance() {
		return entrance;
	}
	public void SetEntrance(Tile entrance) {
		this.entrance = entrance;
	}
}
