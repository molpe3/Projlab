
public class Chair extends Thing {
	private boolean occupied;
	public void Step() {	//randomiz�l�st megcsin�lni, ki-be kapcsolhat� legyen
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
	public void Detach(Observer o) {
		observers.remove(o);
	}
}
