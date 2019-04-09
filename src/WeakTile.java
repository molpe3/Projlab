
public class WeakTile extends Tile {
	public WeakTile(String name) {
		super(name);
	}
	private int steps=0;
	private boolean broken=false;
	public void Damage (int n) {
		steps+=n;
		if (steps>=20) {
			Break();
		}
	}
	public void Break() {
		broken=true;
		if (animal!=null)
			animal.Fall();
	}
	public boolean AcceptPanda(Panda p) {
		if (animal!=null) {
			animal.CollideWithPanda(p);
			p.tile=this;
			animal=p;
			Damage(1);
			return false;
		}
		p.tile=this;
		animal=p;
		Damage(1);
		return true;
	}
	public boolean AcceptOrangutan(Orangutan o) {
		if (animal!=null){
			if (animal.CollideWithOrangutan(o)==true) {
				o.tile=this;
				animal=o;
				Damage(1);
			}
		return false;	
		}
		o.tile=this;
		animal=o;
		Damage(1);
		return true;
	}
	public void JumpedOn() {
		Damage(5);
	}
	public int GetSteps() {
		return steps;
	}
	public void SetSteps(int steps) {
		this.steps = steps;
	}
	public boolean GetBroken() {
		return broken;
	}
	public void SetBroken(boolean broken) {
		this.broken = broken;
	}
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
		System.out.println("\t�llapot:");
		if (broken) {
			System.out.println("\t\tt�r�tt");
			System.out.println("\t\t"+steps);
		}
		else {
			System.out.println("\t\t�p");
			System.out.println("\t\t"+steps);
		}
	}
}
