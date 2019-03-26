
public class WeakTile extends Tile {
	private int steps;
	private boolean broken;
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
			return false;
		}
		Damage(1);
		return true;
	}
	public boolean AcceptOrangutan(Orangutan o) {
		if (animal!=null){
			if (animal.CollideWithOrangutan(o)==true) {
				Damage(1);
			}
		return false;	
		}
		return true;
	}
	public void JumpedOn() {
		Damage(5);
	}
}
