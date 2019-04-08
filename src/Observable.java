
public interface Observable {
	public void Detach(Observer o);
	public void Subscribe(Observer o);
	public void Notify();
}
