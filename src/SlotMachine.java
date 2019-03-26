import java.util.Random;
public class SlotMachine extends Thing {
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
}
