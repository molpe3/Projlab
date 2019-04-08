
public class Wardrobe extends Tile {	//nincs k�sz
	public Wardrobe(String name) {
		super(name);
	}
	private Wardrobe pair;
	public boolean AcceptPanda(Panda p) {return true;}
	public boolean AcceptOrangutan(Orangutan o) {return true;}
	public void Print() {
		System.out.println("\t"+this.name);
		System.out.println("\tSzomsz�dok:");
		if (!neighbors.isEmpty()) {
			for (int key:neighbors.keySet()){
				System.out.println("\t\t"+key+":"+neighbors.get(key).GetName());
			}
		}
		System.out.println("\tRajta l�vo �llat:");
		if (animal!=null) {
			System.out.println("\t\t"+animal.GetName()+":"+animal.getClass());
		}
		System.out.println("\tRajta l�vo t�rgy:");
		if (thing!=null)
			System.out.println("\t\t"+thing.GetName()+":"+thing.getClass());
		System.out.println("\tP�r:");
		System.out.println("\t\t"+pair.GetName());
	}
	public Wardrobe GetPair() {
		return pair;
	}
	public void SetPair(Wardrobe pair) {
		this.pair = pair;
	}
}
