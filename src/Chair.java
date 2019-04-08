
public class Chair extends Thing {
	public Chair(String name) {
		super(name);
	}
	private boolean occupied;
	public void Step() {	
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
	public void SetOccupied(boolean occupied) {
		this.occupied=occupied;
	}
	
	
	public void Print() {
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName()+":"+tile.getClass());
		System.out.println("\t¡llapot:");
		if (occupied==true) {
			System.out.println("\t\tfoglalt");
		}
		else {
			System.out.println("\t\tszabad");
		}
		System.out.println("\tObserverek:");
		for (Observer o:observers) {
			System.out.println("\t\t"+o.GetName());
		}
	}
}
