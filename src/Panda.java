import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
public class Panda extends Animal implements Observer, Steppable {


	public void Move(int side, Tile tile, Panda pulled, HashMap<Integer,Tile> neighbors, Animal onTile, Tile pulledTile) {
		System.out.println("Move(int side)");
		Tile nextTile=tile.GetNeighbor(side, neighbors);
		if (nextTile.AcceptPanda(this,onTile)) {
			tile.RemoveAnimal();
			nextTile.SetAnimal(this);
			if (pulled!=null) {
				Tile next_Tile2=pulled.GetTile(pulledTile);
				int a=tile.CompareTile(next_Tile2, neighbors);
				pulled.Move(a);
			}
		}
	}
	
	public boolean CollideWithPanda(Panda p){
		System.out.println("CollideWithPanda(Panda p)");
		return false;
	}
	public boolean CollideWithOrangutan(Orangutan o, Animal puller,Tile tile, Tile oran_tile, Panda lead_panda){
		System.out.println("CollideWithOrangutan(Orangutan o)");
		if (puller==null){
			CaughtbyOrangutan(o,tile,oran_tile,lead_panda);
			return true;
			}
		return false;
	}
	public  void CaughtbyOrangutan(Orangutan o, Tile tile, Tile oran_tile, Panda lead_panda){
		System.out.println("CaughtbyOrangutan(Orangutan o)");
		oran_tile = o.GetTile(oran_tile);
                Panda lead = o.GetPulled(lead_panda);
                o.SetPulled(this);
                if (lead != null)
                        lead.SetPuller(this);
                o.SetTile(tile);
                this.SetTile(oran_tile);
                tile.SetAnimal(o);
                oran_tile.SetAnimal(this);

	}
	public Animal GetPuller(Animal puller){
		System.out.println("GetPuller()");
		return puller;
	}
	public void SetPuller(Animal a){
		System.out.println("SetPuller(Animal a)");
		
	}

	public void Destroy(Tile tile, Game game,Animal puller, Panda pulled,  ArrayList<Orangutan> orangutans, ArrayList<Panda> pandas,ArrayList<Observable> observables,Timer timer){
		System.out.println("Destroy()");
		for (Observable o:observables){
			o.Detach(this);
			Game.getInstance().GetTimer(timer).RemoveSteppable(this);
			Game.getInstance().DeleteAnimal(this,orangutans,pandas);
		}
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this, orangutans,pandas);
		if (pulled!=null) {
			Disband(puller, pulled);
		}
	}


	public void Step(int random, ArrayList<Observer> observers) {
		System.out.println("Step()");
		this.Move(random);

	}
	public void GetScared(){}
	public void Jump(){}
	public void Sit(){}
	public void Update(Observable ob) {}

	public void Disband(Animal puller, Panda pulled, Panda pulled2){
		System.out.println("Disband()");
		if(puller!=null)puller.SetPulled(null);
		puller=null;
		if (pulled!=null) {
			pulled.Disband(this, pulled2);
		}
	}
	public void Disband(Animal puller, Panda pulled){
		System.out.println("Disband()");
		puller.SetPulled(null);
		puller=null;
		if (pulled!=null) {
			pulled.Disband(this, null);
		}
	}

}
