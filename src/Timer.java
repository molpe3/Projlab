import java.util.ArrayList;

/**
* A Steppable interfészt implementáló objektumok működésének ütemezését végző osztály, lépteti azokat.
*/
public class Timer {
	private ArrayList<Steppable> steppables;
	
	/**
	* Meghívja minden léptethető elemre a Step() metódust.
	*/
	public void Tick() {
		if( steppables != null )
			for (Steppable s:steppables)
				s.Step();
	}
	
	/**
	* Hozzáadja a paraméterként kapott Steppable-t a léptethető objektumokhoz.
	* @param hozzáadni kívánt Steppable.
	*/
	public void AddSteppable(Steppable s) {
		if( steppables == null )
			steppables = new ArrayList<Steppable>();
		steppables.Add(s);
	}
	
	/**
	* Eltávolítja a paraméterként kapott Steppable-t a léptethető objektumok közül.
	* @param eltávolítandó Steppable.
	*/
	public void RemoveSteppable(Steppable s) {
		if( steppables != null && !steppables.isEmpty() )
			steppables.remove(s);
	}
}
