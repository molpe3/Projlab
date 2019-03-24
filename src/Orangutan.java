import java.util.HashMap;
import java.util.Hashtable;

public class Orangutan extends Animal {
	
	public void Addpoint() {
		System.out.println("Addpoint()");
		}
	public void Move(int side, Tile tile, Panda pulled, HashMap<Integer,Tile> neighbors, Animal onTile, Tile pulledTile) {
		System.out.println("Move(int side)");
		Tile nextTile=tile.GetNeighbor(side, neighbors);
		if (nextTile.AcceptOrangutan(this,onTile)) {
			tile.RemoveAnimal();
			nextTile.SetAnimal(this);
			if (pulled!=null) {
				Tile next_Tile2=pulled.GetTile(pulledTile);
				int a=tile.CompareTile(next_Tile2,neighbors);
				pulled.Move(a);
			}
		}
	}
	public boolean CollideWithPanda(Panda p, Panda pulled,Tile tile, Tile oran_tile, Panda lead_panda){
		System.out.println("CollideWithPanda(Panda p)");
		if(p.GetPulled(pulled) != null)		
			p.CaughtbyOrangutan(this,tile,oran_tile,lead_panda);
		return false;
	}
	public boolean CollideWithOrangutan(Orangutan o){
		System.out.println("CollideWithOrangutan(Orangutan o)");
		return false;
	}
}
