
public class Wardrobe extends Tile {	//nincs kész
	public Wardrobe(String name) {
		super(name);
	}
	private Wardrobe pair;
	public boolean AcceptPanda(Panda p) {
		if (animal!=null) {
			animal.CollideWithPanda(p);
			return false;
		}
		if (pair.GetAnimal()==null) {
			
			p.tile.RemoveAnimal();
			p.SetTile(pair);
			pair.SetAnimal(p);
			return false;
		}
		
		p.tile.RemoveAnimal();
		p.tile=this;
		animal=p;
		return true;
		
	}
	public boolean AcceptOrangutan(Orangutan o) {
		Tile start=o.GetTile();
		if (animal!=null){
			animal.CollideWithOrangutan(o);
			return false;
		}
		if (pair.GetAnimal()==null) {
			
			o.tile.RemoveAnimal();
			o.SetTile(pair);
			pair.SetAnimal(o);
			if (o.pulled!=null) {
				int a=o.pulled.GetTile().CompareTile(start);
				o.pulled.Move(a);
				 a=o.pulled.GetTile().CompareTile(this);
				o.pulled.Move(a);
			}
			return false;
		}
		o.tile.RemoveAnimal();
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
		System.out.println("\tPár:");
		System.out.println("\t\t"+pair.GetName());
	}
	public Wardrobe GetPair() {
		return pair;
	}
	public void SetPair(Wardrobe pair) {
		this.pair = pair;
	}
}
