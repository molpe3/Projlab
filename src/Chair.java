import java.util.ArrayList;

public class Chair extends Thing {

	public void Step(int random, ArrayList<Observer> observers){
		if (random==1) this.Step(true,observers);
		if (random==0) this.Step(false,observers);
	}

	public void Step(boolean occupied,ArrayList<Observer> observers) {
		System.out.println("Step()");
		if (!occupied) {
			Notify(observers);
		}
	}
	public void Notify(ArrayList<Observer> observers) {
		System.out.println("Notify()");
		for (Observer o:observers) {
			o.Update(this);
		}
	}
	public boolean GetOccupied(boolean occupied) {
		System.out.println("GetOccupied()");
		return occupied;
		}
	public void Detach(Observer o) {
		System.out.println("Detach(Observer o)");

	}
}
