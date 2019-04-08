
public class Exit extends Tile{
	public Exit(String name) {
		super(name);
	}
	private Tile entrance;
	public boolean AcceptPanda(Panda p) {
		if (animal!=null) {
			animal.CollideWithPanda(p);
			return false;
		}
		return true;
		}
	public boolean AcceptOrangutan(Orangutan o) {	//szkeleton kódból át kell írni
		/*if(entrance.GetAnimal(entranceAnimal)!=null)return false;
		tile.RemoveAnimal();
		entrance.SetAnimal(o);
		o.SetTile(entrance);
		Panda temp1 = o.GetPulled(p1);
		Panda temp2;
		//while cikklus helyett
		if(temp1!=null) {
			o.Addpoint();
			temp2 = temp1.GetPulled(p2);
			if (p2 != null){
				o.Addpoint();
			}
			temp1.Destroy(panda1Tile,game,o,temp2,orangutans,pandas,observables,timer);
			if (temp2!=null)temp2.Destroy(panda2Tile,game,null,null,orangutans,pandas,observables,timer);
		}*/
		return false;
		
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
