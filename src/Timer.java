import java.util.ArrayList;
public class Timer {
	private ArrayList<Steppable> steppables;
	public void Tick() {
		for (Steppable s:steppables){
			s.Step();
		}
	}
	public void RemoveSteppable(Steppable s) {
		steppables.remove(s);
	}
}
