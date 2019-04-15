import java.util.ArrayList;
import java.util.Random;
public class Panda extends Animal implements Observer, Steppable {
	protected Animal puller;
	private ArrayList<Observable> observables;
	public Panda(String name){
		this.name=name;
		observables=new ArrayList<Observable>();
	}
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
	public boolean CollideWithPanda(Panda p){		
		return false;
	}
	public boolean CollideWithOrangutan(Orangutan o){
		if (puller==null&&o.GetStepssincerobbed()>3){
			CaughtbyOrangutan(o);
			return true;
		}
		return false;
	}
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
	public Animal GetPuller(){
		return puller;
	}
	public void SetPuller(Animal a){
		puller=a;
	}
	public void Destroy(){
		for (Observable o:observables){
			o.Detach(this);
			Game.getInstance().GetTimer().RemoveSteppable(this);	
		}
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this);
	}
	
	public void Step() {
		//tiredpanda felülírja!!!!
		Random rand=new Random();
		if (puller==null) {	//randomizálás ki/be kapcsolható legyen
			int sides=tile.GetSides();
			Move(rand.nextInt(sides));
		}
	}
	public void GetScared(){}
	public void Jump(){}
	public void Sit(){}
	public void Update(Observable ob) {
	
	}
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
