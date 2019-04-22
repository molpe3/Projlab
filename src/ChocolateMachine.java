import java.util.Random;

/**
* Sípol, amitől a közelében lévő ScaredPandák megijednek.
*/
public class ChocolateMachine extends Thing{
	public ChocolateMachine(String name) {
		super(name);
	}
	
	/**
	* Whistles, notifying its subscribers of this.
	*/
	public void Whistle() {
		Notify();
	}
	
	/** 
	* On every timer tick, it has a 20% chance to whistle
	*/
	public void Step() {
		Random rand=new Random();
		if (rand.nextInt(10)<2) { //20%
			Whistle();
		}
	}
	
	/**
	* Notifies subscribed ScaredPandas of the whistling so they can react accordingly
	*/
	public void Notify() {
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	
	/**
	* removes an observer from its subscriber list
	* @param o the observer to be removed
	*/
	public void Detach(Observer o) {
		observers.remove(o);
	}
	
	/**
	* prints out all relevant information
	* this includes name, tile and observers
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
