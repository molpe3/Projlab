import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;

//Ha legyszi hasznalod a Game konstruktorat protectedbe, es ne legyen final
//TODO fuggvenyek
public class Main extends Game{
    protected static BufferedWriter out;
	
    protected static BufferedReader in;
	
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public enum State {StageBuild, Simulation, RandomSimulation, Cleanup }

    private State state;

    private String commandfile;

    private String answer;

    private  boolean random = false;

    private HashMap<String, Tile> Tiles;

    private HashMap<String, Animal> Animals;

    private HashMap<String, Thing> Things;

    public class Double<T1,T2>{
        public T1 first;
        public T2 second;
        public Double(T1 t1,T2 t2){
            first = t1;
            second = t2;
        }
    }

    public class Triple<T1,T2,T3>{
        public T1 first;
        public T2 second;
        public T3 third;
        public Triple(T1 t1, T2 t2, T3 t3){
            first=t1;
            second = t2;
            third=t3;
        }
    }

    public void StartTest(){

    }

    private HashMap<String, Double<Integer,State> > Commands;

    private ArrayList<Triple<String,Integer,Integer>> MovingAnimals;
    private int pandacount = 0;
    private int orancount = 0;
    private ArrayList<Triple<Orangutan,Tile,Boolean>> MovingOrangutans;

    private ArrayList<Triple<Panda,Tile,Boolean>> MovingPandas;

    private ArrayList<Thing> MovingThings;

    public void ExecuteState(){

    }
    public void ST_Ask(){

    }
    public void ST_Answer(){

    }
    public void SIAnimalAsk(){

    }
    public void SIAnimalAnswer(String type){

    }
    public void SIThingAsk(){

    }
    public void SIThingAnswer(String type){

    }
    public void SIRAnimalAnswer(String type){

    }
    public void SIRThingAnswer(String type){

    }
    public void IsLetGo(){

    }
    public void ReadState(String path){

    }
    public void ReadCommand(){

    }
    public void WriteState(){

    }
    public void ClearAll(){

    }
    public void CTile(String name, int sides){
        Tile tile = new Tile();
        tile.sides = sides;
        Tiles.put(name,tile);
    }
    public void CWardrobe(String name, int sides){
        Wardrobe war = new Wardrobe();
        war.sides = sides;
        Tiles.put(name,war);
    }
    public void CWeakTile(String name, int sides){
        WeakTile wt = new WeakTile();
        wt.sides=sides;
        Tiles.put(name,wt);
    }
    public void CChair(String name1, String name2){
        Chair chair = new Chair();
        Tile tile = Tiles.get(name2);
        tile.SetThing(chair);
        chair.SetTile(tile);
        Things.put(name1,chair);
        MovingThings.add(chair);
    }
    public void CChocolateMachine(String name1,String name2){
        ChocolateMachine cm = new ChocolateMachine();
        Tile tile = Tiles.get(name2);
        tile.SetThing(cm);
        cm.SetTile(tile);
        Things.put(name1,cm);
        MovingThings.add(cm);
    }
    public void CSlotMachine(String name1,String name2){
        SlotMachine sm = new SlotMachine();
        Tile tile = Tiles.get(name2);
        tile.SetThing(sm);
        sm.SetTile(tile);
        Things.put(name1,sm);
        MovingThings.add(sm);
    }
    public void CExit(String name, int sides){
        Exit exit = new Exit();
        exit.sides=sides;
        Tiles.put(name,exit);
    }
    public void CPanda(String name, String tile){
        Panda p = new Panda();
        Tile t = Tiles.get(tile);
        p.SetTile(t);
        t.SetAnimal(p);
        Animals.put(name,p);
        MovingAnimals.add(new Triple<String, Integer, Integer>(name,0,pandacount));
        pandacount++;
        MovingPandas.add(new Triple<>(p,t,true));
    }
    public void CJumpingPanda(String name, String tile){
        JumpingPanda p = new JumpingPanda();
        Tile t = Tiles.get(tile);
        p.SetTile(t);
        t.SetAnimal(p);
        Animals.put(name,p);
        MovingAnimals.add(new Triple<String, Integer, Integer>(name,0,pandacount));
        pandacount++;
        MovingPandas.add(new Triple<>(p,t,true));
    }
    public void CScaredPanda(String name, String tile){
        ScaredPanda p = new ScaredPanda();
        Tile t = Tiles.get(tile);
        p.SetTile(t);
        t.SetAnimal(p);
        Animals.put(name,p);
        MovingAnimals.add(new Triple<String, Integer, Integer>(name,0,pandacount));
        pandacount++;
        MovingPandas.add(new Triple<>(p,t,true));
    }
    public void CTiredPanda(String name, String tile){
        TiredPanda p = new TiredPanda();
        Tile t = Tiles.get(tile);
        p.SetTile(t);
        t.SetAnimal(p);
        Animals.put(name,p);
        MovingAnimals.add(new Triple<String, Integer, Integer>(name,0,pandacount));
        pandacount++;
        MovingPandas.add(new Triple<>(p,t,true));
    }
    public void COrangutan(String name, String tile){
        Orangutan o = new Orangutan();
        Tile t = Tiles.get(tile);
        o.SetTile(t);
        t.SetAnimal(o);
        Animals.put(name,o);
        MovingAnimals.add(new Triple<String, Integer, Integer>(name,1,orancount));
        orancount++;
        MovingOrangutans.add(new Triple<>(o,t,true));
    }
    public void SRandom(boolean r){
        random=r;
    }
    public void SConnect(Tile t1, int i1, Tile t2, int i2){
        t1.neighbors.put(i1,t2);
        t2.neighbors.put(i2,t1);
    }
    public void SWardrobeConnect(Wardrobe w1, Wardrobe w2){
        w1.neighbors.put(-1,w2);
        w2.neighbors.put(-1,w1);
    }
    public void SSubscribe(Panda p1, Thing t1){
        p1.observables.add(t1);
        t1.Subscribe(p1);
    }
    //TODO Atirni mert ez nem lesz jo
    public void SExitConnect(Exit e1, Tile t2){
        e1.neighbors.put(-1,t2);
        t2.neighbors.put(-1,e1);
    }
    public void SWeakTileSetLife(WeakTile w1, int i1){
        w1.steps=i1;
    }
    public void SWeakTileSetBroken(WeakTile w1, boolean b1){
        w1.broken=b1;
    }
    public void SOrangutanSetCooldown(Orangutan w1, int i1){
        w1.stepssincerobbed=i1;
    }
    public void SOrangutanPoint(Orangutan w1, int i1){
        w1.SetPoints(i1);
    }
    public void STiredPandaTired(TiredPanda t1, boolean b1){
        t1.SetTired(b1);
    }
    public void SSetPuller(Panda p1, Animal a1){
        p1.SetPuller(a1);
        a1.SetPulled(p1);
    }
    public void DeleteAnimal(Animal a){
        a.Destroy();
    }


}
