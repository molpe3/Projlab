import java.util.ArrayList;
import java.util.Random;

/**
* Különböző tulajdonságokkal bíró pandák közös tulajdonságait összefoglaló absztrakt ősosztály.
* A panda véletlenszerűen kószál a játéktéren, amíg egy orángután meg nem fogja. (A protóban determinisztikusan is mozgatható.)
* Miután megfogták, követi az őt fogó állatot, amíg ki nem vezetik a játéktérről, fel nem bomlik a sor, vagy meg nem hal.
*/
public class Panda extends Animal implements Observer, Steppable {
	protected Animal puller;
	private ArrayList<Observable> observables;
	public Panda(String name){
		this.name=name;
		observables=new ArrayList<Observable>();
	}
	
	/**
	* Moves to an adjacent tile, collides with the occupying animal if it's occupied.
	* @param side the side of the current tile which the target tile is adjacent to
	*/
	public void Move(int side) {
		Tile start=tile;
		Tile t2=tile.GetNeighbor(side);
		if (t2.AcceptPanda(this)) {	
			if (pulled!=null) {
				Tile t3=pulled.GetTile();
				int a=t3.CompareTile(start);
				pulled.Move(a);
				if (!Game.GetPandas().contains(this)) {
					Disband();
				}
			}
		}
	}
	
	/**
	* Collides with a panda, always preventing movement.
	* @param p the panda it's colliding with
	*/
	public boolean CollideWithPanda(Panda p){		
		return false;
	}
	
	/**
	* Collides with an orangutan, getting caught by it if it's eligible to catch pandas
	* (the orangutan made 3+ steps since it was robbed)
	* @param o the orangutan it's colliding with
	*/
	public boolean CollideWithOrangutan(Orangutan o){
		if (puller==null&&o.GetStepssincerobbed()>3){
			CaughtbyOrangutan(o);
			return true;
		}
		return false;
	}
	
	/**
	* Gets caught by an orangutan, becoming the first panda in its pulled queue.
	* Becomes the puller of the previous queue leader if one existed.
	* Also swaps tiles with the orangutan.
	* @param o the orangutan catching it
	*/
	public  void CaughtbyOrangutan(Orangutan o){	
		Tile oran_tile = o.GetTile();
        Panda lead_panda = o.GetPulled();
        o.SetPulled(this);
        SetPuller(o);
        if (lead_panda != null) {	
        	lead_panda.SetPuller(this);
        	this.SetPulled(lead_panda);
        }
        o.SetTile(tile);
        tile.SetAnimal(o);
        this.SetTile(oran_tile);
        oran_tile.SetAnimal(this);
	}
	
	/**
	* returns with the animal that pulls it
	* @return puller the pulling animal
	*/
	public Animal GetPuller(){
		return puller;
	}
	
	/**
	* sets the animal that pulls it
	* @param a the animal we're setting as puller
	*/
	public void SetPuller(Animal a){
		puller=a;
	}
	
	/**
	* unsubscribes from the observables before getting deleted
	*/
	public void Destroy(){
		for (Observable o:observables){
			o.Detach(this);
			Game.getInstance().GetTimer().RemoveSteppable(this);	
		}
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this);
	}
	
	/**
	* moves randomly on timer ticks
	*/
	public void Step() {
		//tiredpanda felülírja!!!!
		Random rand=new Random();
		if (puller==null) {	//randomizálás ki/be kapcsolható legyen
			int sides=tile.GetSides();
			Move(rand.nextInt(sides));
		}
	}
	
	/**
	* a subclass implements this with expanded functionality
	*/
	public void GetScared(){}
	
	/**
	* a subclass implements this with expanded functionality
	*/
	public void Jump(){}
	
	/**
	* a subclass implements this with expanded functionality
	*/
	public void Sit(){}
	
	/**
	* multiple subclasses implement this with expanded functionality
	*/
	public void Update(Observable ob) {
	
	}
	
	/**
	* disbands the queue; puller no longer pulls, pulled disbands as well
	*/
	public void Disband(){
		if (puller!=null)
			puller.SetPulled(null);
		puller=null;
		if (pulled!=null) {
			pulled.Disband();
		}
	}
	
	public void Print()
	{
		
	}
}
