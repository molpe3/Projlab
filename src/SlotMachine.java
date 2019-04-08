import java.util.Random;
public class SlotMachine extends Thing {
	public SlotMachine(String name) {
		super(name);
	}
	public void Jingle() {
		Notify();
	}
	public void Step() {
		Random rand=new Random();
		if (rand.nextInt(1)<2) {	//most még mindig csinálja, randomizálás megoldani, hogy ki/be kapcsolható legyen
			Jingle();
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
