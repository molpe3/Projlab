import java.util.ArrayList;

public interface Observable {
	public void Detach(Observer o);
	public void Notify(ArrayList<Observer> observers);
}
