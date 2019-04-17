import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


//Ha legyszi hasznalod a Game konstruktorat protectedbe, es ne legyen final
//TODO fuggvenyek
public class Main extends Game{

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public enum State {StageBuild, Simulation, RandomSimulation, Cleanup }

    private State state;

    private ArrayList<String> commandfile;

    private int commandindex=0;

    public boolean usefile=true;

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
        switch (state) {
            case StageBuild:

                break;
            case Simulation:
                break;
            case RandomSimulation:
                break;
            case Cleanup:
                break;
        }
    }
    public void ST_Ask(){
        //Todo usefile nélkül
        if(!usefile)return;
    }
    public void ST_Answer(){
        ReadCommand();
        String[] parameters = answer.split(" ");
        Double<Integer,State> d = Commands.get(parameters[0]);
        if(d==null)return;
        if (parameters.length!= d.first+1)return;

        switch (parameters[0]){
            case("CTile"):
                CTile(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("CWardrobe"):
                CWardrobe(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("CWeakTile"):
                CWeakTile(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("CChair"):
                CChair(parameters[1],parameters[2]);
                break;

            case("CChocolateMachine"):
                CChocolateMachine(parameters[1],parameters[2]);
                break;

            case("CSlotMachine"):
                CSlotMachine(parameters[1],parameters[2]);
                break;

            case("CExit"):
                CExit(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("CPanda"):
                CPanda(parameters[1],parameters[2]);
                break;

            case("CJumpingPanda"):
                CJumpingPanda(parameters[1],parameters[2]);
                break;

            case("CScaredPanda"):
                CScaredPanda(parameters[1],parameters[2]);
                break;

            case("CTiredPanda"):
                CTiredPanda(parameters[1],parameters[2]);
                break;

            case("COrangutan"):
                COrangutan(parameters[1],parameters[2]);
                break;

            case("SRandom"):
                SRandom(Boolean.parseBoolean(parameters[1]));
                break;

            case("SConnect"):
                Tile t1 = Tiles.get(parameters[1]);
                int i1 = Integer.parseInt(parameters[2]);
                Tile t2 = Tiles.get(parameters[3]);
                int i2 = Integer.parseInt(parameters[4]);
                SConnect(t1,i1,t2,i2);
                break;

            case("SWardrobeConnect"):
                Wardrobe w1= (Wardrobe) Tiles.get(parameters[1]);
                Wardrobe w2 = (Wardrobe) Tiles.get(parameters[2]);
                SWardrobeConnect(w1,w2);
                break;

            case("SSubscribe"):
                Panda p = (Panda) Animals.get(parameters[1]);
                Thing t = Things.get(parameters[2]);
                SSubscribe(p,t);
                break;

            case("SExitConnect"):
                Exit e = (Exit) Tiles.get(parameters[1]);
                Tile t3 = Tiles.get(parameters[2]);
                SExitConnect(e,t3);
                break;

            case("SWeakTileSetLife"):
                WeakTile wt = (WeakTile)Tiles.get(parameters[1]);
                int i3 = Integer.parseInt(parameters[2]);
                SWeakTileSetLife(wt,i3);
                break;

            case("SWeakTileSetBroken"):
                WeakTile wt2 = (WeakTile)Tiles.get(parameters[1]);
                boolean b = Boolean.parseBoolean(parameters[2]);
                SWeakTileSetBroken(wt2,b);
                break;

            case("SOrangutanSetCooldown"):
                Orangutan o = (Orangutan) Animals.get(parameters[1]);
                int cooldown = Integer.parseInt(parameters[2]);
                SOrangutanSetCooldown(o,cooldown);
                break;

            case("SOrangutanPoint"):
                Orangutan o2 = (Orangutan) Animals.get(parameters[1]);
                int point = Integer.parseInt(parameters[2]);
                SOrangutanPoint(o2,point);
                break;

            case("STiredPandaTired"):
                boolean tired = Boolean.parseBoolean(parameters[2]);
                TiredPanda tp = (TiredPanda) Animals.get(parameters[1]);
                STiredPandaTired(tp,tired);
                break;

            case("SSetPuller"):
                Panda pulled = (Panda) Animals.get(parameters[1]);
                Animal puller = Animals.get(parameters[2]);
                SSetPuller(pulled,puller);
                break;
        }

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
    public void IsLetGo(Orangutan o){
        if (!usefile) {
            String name = "";
            for (int i = 0; i < MovingOrangutans.size(); i++) {
                if (MovingOrangutans.get(i).first == o) {
                    for (Triple<String, Integer, Integer> t : MovingAnimals) {
                        if (t.second == 1 || t.third == i) name = t.first;
                    }
                }
            }
            System.out.println("Let go of " + name + "?");
            //TODO olvassd be a valaszt az answerbe
        }
        ReadCommand();
        if (answer.equals("true")){
            Panda a = o.pulled;
            ArrayList<Panda> freepandas=new ArrayList<>();
            while (a!=null){
                freepandas.add(a);
                a=a.pulled;
            }
            for(Panda p:freepandas){
                for(Triple<Panda, Tile, Boolean> t:MovingPandas){
                    if (p==t.first)t.third=true;
                }
            }
            o.LetGo();
        }

    }
    public void ReadState(String path) throws IOException {
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               commandfile.add(line);
            }
        }
    }
    public void ReadCommand(){
        if(commandindex!=commandfile.size()) {
            answer=commandfile.get(commandindex);
            commandindex++;
        }
    }
    public void WriteState(){
        //?
    }
    public void ClearAll(){
        //state?
        commandfile=new ArrayList<String>();
        commandindex=0;
        answer="";

        random = false;

        Tiles = new HashMap<String, Tile> ();

        Animals=new HashMap<String, Animal>();

        Things=new HashMap<String, Thing>();
        MovingAnimals = new ArrayList<Triple<String,Integer,Integer>>();
        pandacount = 0;
        orancount = 0;
        MovingOrangutans=new ArrayList<Triple<Orangutan,Tile,Boolean>>() ;

        MovingPandas=new ArrayList<Triple<Panda,Tile,Boolean>> ();

        MovingThings= new ArrayList<Thing>();
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
