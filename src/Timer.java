import java.util.ArrayList;
public class Timer {
	
	public void Tick(ArrayList<Steppable> steppables, int random, ArrayList<Observer> observers) {
		System.out.println("Tick()");
		for (Steppable s:steppables){
			s.Step(random, observers);
		}
	}
	public void RemoveSteppable(Steppable s) {
		System.out.println("RemoveSteppable(Steppable s)");

	}
}
