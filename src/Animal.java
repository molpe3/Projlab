import java.util.ArrayList;

public abstract class Animal {
	
	public void Fall(Tile tile, Game game,Animal puller, Panda pulled, ArrayList<Orangutan> orangutans,ArrayList<Panda> pandas) {
		System.out.println("Fall()");
		this.Destroy(tile,game,puller, pulled, orangutans,pandas);
	}
	public void Destroy(Tile tile, Game game,Animal puller, Panda pulled,  ArrayList<Orangutan> orangutans, ArrayList<Panda> pandas){
		System.out.println("Destroy()");
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this, orangutans,pandas);
		if (pulled!=null) {
			Disband(puller, pulled);
		}
	}
	public void Move(int side){
		//Implemented in subclasses

	}
	public boolean CollideWithPanda(Panda p){
		System.out.println("CollideWithPanda(Panda p)");
		return false;
		}
	public boolean CollideWithOrangutan(Orangutan o){
		System.out.println("CollideWithOrangutan(Orangutan o)");
		return false;
		}
	public void Disband(Animal puller, Panda pulled){
		System.out.println("Disband()");
		if(puller !=null)puller.SetPulled(null);
		if (pulled==null)return;
		pulled.Disband(this,null);
	}
	public Tile GetTile(Tile tile){
		System.out.println("GetTile()");
		return tile;
		}

	public void SetTile(Tile tile){
		System.out.println("SetTile(Tile tile)");
	}
	public Panda GetPulled(Panda pulled){
		System.out.println("GetPulled()");
		return pulled;
		}
	public void SetPulled(Panda p){
		System.out.println("SetPulled(Panda p)");
		}
}
