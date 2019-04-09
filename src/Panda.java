import java.util.ArrayList;
import java.util.Random;
public class Panda extends Animal implements Observer, Steppable {
	protected Animal puller;
	private ArrayList<Observable> observables;
	public Panda(String name)
	{
		this.name=name;
		observables=new ArrayList<Observable>();
	}
	public void Move(int side) {
		Tile t2=tile.GetNeighbor(side);
		if (t2.AcceptPanda(this)) {	
			tile.RemoveAnimal();
			if (pulled!=null) {
				Tile t3=pulled.GetTile();
				int a=tile.CompareTile(t3);
				pulled.Move(a);
			}
		}
	}
	public boolean CollideWithPanda(Panda p){		
		return false;
	}
	public boolean CollideWithOrangutan(Orangutan o){	//csak akkor kap�djon el, ha az o.stepssinceletgo>3
		if (puller==null){
			CaughtbyOrangutan(o);
			return true;
			}
		return false;
		}
	public  void CaughtbyOrangutan(Orangutan o){	
		Tile oran_tile = o.GetTile();
                Panda lead_panda = o.GetPulled();
                o.SetPulled(this);
                if (lead_panda != null)
                        lead_panda.SetPuller(this);
                o.SetTile(tile);
                super.SetTile(oran_tile);
                tile.SetAnimal(o);
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
		if (pulled!=null) {
			Disband();
		}
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this);
	}
	
	public void Step() {
		Random rand=new Random();
		if (puller==null) {	//randomiz�l�s ki/be kapcsolhat� legyen
			int sides=tile.GetSides();
			Move(rand.nextInt(sides));
		}
	}
	public void GetScared(){}
	public void Jump(){}
	public void Sit(){}
	public void Update(Observable ob) {}
	public void Disband(){
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
