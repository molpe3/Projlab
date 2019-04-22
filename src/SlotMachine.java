import java.util.Random;

/**
* Csilingel, és értesíti a regisztrált pandákat erről.
*/
public class SlotMachine extends Thing {
	public SlotMachine(String name) {
		super(name);
	}
	
	/**
	* Jingles and notifies subscribers of this fact.
	*/
	public void Jingle() {
		Notify();
	}
	
	/**
	* Jingles on every timer tick.
	*/
	public void Step() {
		Random rand=new Random();
		if (rand.nextInt(1)<2) {	//most még mindig csinálja, randomizálás megoldani, hogy ki/be kapcsolható legyen
			Jingle();
		}
	}
	
	/**
	* Notifies every subscribing observer of the jingling.
	*/
	public void Notify() {
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	
	/**
	* Removes an observer from its list of subscribers.
	* @param o the observer to be removed
	*/
	public void Detach(Observer o) {
		observers.remove(o);
	}
	
	/**
	* Prints all relevant information.
	* This includes name, tile and observers.
	*/
	public void Print() {
		System.out.println("\t"+this.name);
		System.out.println("\tCsempe:");
		System.out.println("\t\t"+tile.GetName()+":"+tile.getClass());
		System.out.println("\tObserverek:");
		for (Observer o:observers) {
			System.out.println("\t\t"+o.GetName());
		}
	}
}
