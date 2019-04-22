
/**
* Szék, amiben a fáradékony pandák kipihenhetik magukat.
* Értesíti a fáradékony pandákat erről a lehetőségről.
*/
public class Chair extends Thing {
	public Chair(String name) {
		super(name);
	}
	private boolean occupied;
	
	/**
	* notifies observers on timer ticks, but only when unoccupied
	*/
	public void Step() {	
		if (!occupied) {
			Notify();
		}
	}
	
	/**
	* calls subscribed TiredPandas' Update methods
	*/
	public void Notify() {
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	
	/**
	* returns whether it's occupied right now
	*/
	public boolean GetOccupied() {
		return occupied;
	}
	
	/**
	* sets occupied status
	*/
	public void SetOccupied(boolean occupied) {
		this.occupied=occupied;
	}
	
	/**
	* prints all relevant information
	*/
	public void Print() {
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName()+":"+tile.getClass());
		System.out.println("\tÁllapot:");
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
