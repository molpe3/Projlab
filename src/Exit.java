
public class Exit extends Tile{
	private Tile entrance;
	public boolean AcceptPanda(Panda p) {	//meg kell írni, (ha panda egyedül lép ide, nem történik semmi)
		return true;}
	public boolean AcceptOrangutan(Orangutan o) {	//szkeleton kódból át kell írni
		/*if(entrance.GetAnimal(entranceAnimal)!=null)return false;
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
		}*/
		return false;
}
}
