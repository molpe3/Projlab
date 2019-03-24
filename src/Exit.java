import java.util.ArrayList;

public class Exit extends Tile{
	
	public boolean AcceptPanda(Panda p, Tile tile, Game game, Animal puller, Panda pulled, ArrayList<Orangutan> orangutans, ArrayList<Panda> pandas, ArrayList<Observable> observables, Timer timer) {
		System.out.println("AcceptPanda(p)");
		tile.RemoveAnimal();
		p.Destroy(tile,game,puller,pulled,orangutans,pandas,observables,timer);
		return false;
	}
	public boolean AcceptOrangutan(Orangutan o, Tile entrance, Tile tile, Panda p1, Panda p2,Animal entranceAnimal, Tile panda1Tile, Tile panda2Tile, Game game,ArrayList<Orangutan> orangutans, ArrayList<Panda> pandas,ArrayList<Observable> observables, Timer timer) {
		System.out.println("AcceptOrangutan o)");
		if(entrance.GetAnimal(entranceAnimal)!=null)return false;
		tile.RemoveAnimal();
		entrance.SetAnimal(o);
		o.SetTile(entrance);
		Panda temp1 = o.GetPulled(p1);
		Panda temp2;

		//while cikklus helyett
		if(temp1!=null) {
			o.Addpoint();
			temp2 = temp1.GetPulled(p2);
			if (p2 != null){
				o.Addpoint();
			}
			temp1.Destroy(panda1Tile,game,o,temp2,orangutans,pandas,observables,timer);
			if (temp2!=null)temp2.Destroy(panda2Tile,game,null,null,orangutans,pandas,observables,timer);

		}
		return false;
	}
}
