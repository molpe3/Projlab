
public class Chair extends Thing {
	private boolean occupied;
	public void Step() {	//randomizálást megcsinálni, ki-be kapcsolható legyen
		if (!occupied) {
			Notify();
		}
	}
	public void Notify() {
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	public boolean GetOccupied() {
		return occupied;
	}
	public void SetOccupied(boolean occupied){
		this.occupied=occupied;
	}
	public void Detach(Observer o) {
		observers.remove(o);
	}
}
