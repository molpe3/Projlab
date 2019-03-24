import java.util.ArrayList;
import java.util.Random;
public class ChocolateMachine extends Thing{
	public void Whistle(ArrayList<Observer> observers) {
		System.out.println("Whistle()");
		Notify(observers);
	}
	public void Step(int rand, ArrayList<Observer> observers) { //tesztelés alatt nem randomozunk
		System.out.println("Step()");
		if (rand<2) { //most még mindig csinálja
			Whistle(observers);
		}
	}
	
	public void Notify(ArrayList<Observer> observers) {
		System.out.println("Notify()");
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	public void Detach(Observer o) {
		System.out.println("Detach(Observer o)");

	}
	
}
