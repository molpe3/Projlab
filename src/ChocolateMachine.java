import java.util.Random;
public class ChocolateMachine extends Thing{
	public ChocolateMachine(String name) {
		super(name);
	}
	public void Whistle() {
		Notify();
	}
	public void Step() {
		Random rand=new Random();
		if (rand.nextInt(10)<2) { //20%
			Whistle();
		}
	}
	public void Notify() {
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	public void Detach(Observer o) {
		observers.remove(o);
	}
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
