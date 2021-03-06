import java.io.*;
import java.util.*;


//Ha legyszi hasznalod a Game konstruktorat protectedbe, es ne legyen final
//TODO fuggvenyek
public class Main extends Game{


    /*public static void main(String[] args) {

        Main MainClass = new Main();
        MainClass.FillCommand();
        try {
            MainClass.ReadState("teszt.txt");
        }
        catch (Exception e){
            System.out.println("Gebasz");
        }
        MainClass.state=State.StageBuild;
        MainClass.SetTimer();
        while (MainClass.state!=State.Cleanup){
            MainClass.ExecuteState();
        }
        MainClass.ExecuteState();

    }*/
    public static void main(String[] args){
        Main2 main2 = new Main2();
        main2.Everything("teszt2.txt");

    }

    public enum State {StageBuild, Simulation, RandomSimulation, Cleanup }

    private State state;

    private ArrayList<String> commandfile = new ArrayList<>();

    private int commandindex=0;

    public boolean usefile=true;

    private String answer="";

    private  boolean random = false;

    private HashMap<String, Tile> Tiles = new HashMap<>();

    private HashMap<String, Animal> Animals = new HashMap<>();

    private HashMap<String, Thing> Things = new HashMap<>();

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

    public void FillCommand(){

        Commands.put("start",new Double<Integer,State>(0,State.Simulation));
        Commands.put("end",new Double<Integer, State>(0,State.Cleanup));
        Commands.put("Tile",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Wardrobe",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("WeakTile",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Chair",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("ChocolateMachine",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("SlotMachine",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Exit",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Panda",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("JumpingPanda",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("ScaredPanda",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("TiredPanda",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Orangutan",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Random",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Connect",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("WardrobeConnect",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("Subscribe",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("ExitConnect",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("WeakTileSetLife",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("WeakTileSetBroken",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("OrangutanSetCooldown",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("OrangutanPoint",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("TiredPandaTired",new Double<Integer, State>(0,State.StageBuild));
        Commands.put("SetPuller",new Double<Integer, State>(0,State.StageBuild));

    }

    private HashMap<String, Double<Integer,State> > Commands = new HashMap<>();

    private ArrayList<Triple<String,Integer,Integer>> MovingAnimals = new ArrayList<>();
    private int pandacount = 0;
    private int orancount = 0;
    private ArrayList<Triple<Orangutan,Tile,Boolean>> MovingOrangutans = new ArrayList<>();

    private ArrayList<Triple<Panda,Tile,Boolean>> MovingPandas = new ArrayList<>();

    private ArrayList<Thing> MovingThings = new ArrayList<>();


    public void SetTimer(){
        super.SetTimer(new Timer());
    }

    public void ExecuteState(){
        switch (state) {
            case StageBuild:
                System.out.println(commandfile.get(commandindex));

                ST_Ask();
                ST_Answer();
                break;
            case Simulation:
                SIAnimalAsk();
                SIAnimalAnswer();
                SIThingAsk();
                SIThingAnswer("valami");
                //LETGO-val mi lesz?
                break;
            case RandomSimulation:
                break;
            case Cleanup:
                WriteState();
                ClearAll();
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
            case("start"):
                if(random) state=State.RandomSimulation;
                else state=State.Simulation;
                break;

            case("end"):
                state=State.Cleanup;
                break;

            case("Tile"):
                CTile(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("Wardrobe"):
                CWardrobe(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("WeakTile"):
                CWeakTile(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("Chair"):
                CChair(parameters[1],parameters[2]);
                break;

            case("ChocolateMachine"):
                CChocolateMachine(parameters[1],parameters[2]);
                break;

            case("SlotMachine"):
                CSlotMachine(parameters[1],parameters[2]);
                break;

            case("Exit"):
                CExit(parameters[1],Integer.parseInt(parameters[2]));
                break;

            case("Panda"):
                CPanda(parameters[1],parameters[2]);
                break;

            case("JumpingPanda"):
                CJumpingPanda(parameters[1],parameters[2]);
                break;

            case("ScaredPanda"):
                CScaredPanda(parameters[1],parameters[2]);
                break;

            case("TiredPanda"):
                CTiredPanda(parameters[1],parameters[2]);
                break;

            case("Orangutan"):
                COrangutan(parameters[1],parameters[2]);
                break;

            case("Random"):
                SRandom(Boolean.parseBoolean(parameters[1]));
                break;

            case("Connect"):
                Tile t1 = Tiles.get(parameters[1]);
                int i1 = Integer.parseInt(parameters[2]);
                Tile t2 = Tiles.get(parameters[3]);
                int i2 = Integer.parseInt(parameters[4]);
                SConnect(t1,i1,t2,i2);
                break;

            case("WardrobeConnect"):
                Wardrobe w1= (Wardrobe) Tiles.get(parameters[1]);
                Wardrobe w2 = (Wardrobe) Tiles.get(parameters[2]);
                SWardrobeConnect(w1,w2);
                break;

            case("Subscribe"):
                Panda p = (Panda) Animals.get(parameters[1]);
                Thing t = Things.get(parameters[2]);
                SSubscribe(p,t);
                break;

            case("ExitConnect"):
                Exit e = (Exit) Tiles.get(parameters[1]);
                Tile t3 = Tiles.get(parameters[2]);
                SExitConnect(e,t3);
                break;

            case("WeakTileSetLife"):
                WeakTile wt = (WeakTile)Tiles.get(parameters[1]);
                int i3 = Integer.parseInt(parameters[2]);
                SWeakTileSetLife(wt,i3);
                break;

            case("WeakTileSetBroken"):
                WeakTile wt2 = (WeakTile)Tiles.get(parameters[1]);
                boolean b = Boolean.parseBoolean(parameters[2]);
                SWeakTileSetBroken(wt2,b);
                break;

            case("OrangutanSetCooldown"):
                Orangutan o = (Orangutan) Animals.get(parameters[1]);
                int cooldown = Integer.parseInt(parameters[2]);
                SOrangutanSetCooldown(o,cooldown);
                break;

            case("OrangutanPoint"):
                Orangutan o2 = (Orangutan) Animals.get(parameters[1]);
                int point = Integer.parseInt(parameters[2]);
                SOrangutanPoint(o2,point);
                break;

            case("TiredPandaTired"):
                boolean tired = Boolean.parseBoolean(parameters[2]);
                TiredPanda tp = (TiredPanda) Animals.get(parameters[1]);
                STiredPandaTired(tp,tired);
                break;

            case("SetPuller"):
                Panda pulled = (Panda) Animals.get(parameters[1]);
                Animal puller = Animals.get(parameters[2]);
                SSetPuller(pulled,puller);
                break;
        }

    }
    int MovingAnimalIndex=0;
    public void SIAnimalAsk(){
        //ONLY FOR USEFILE, TODO MANUAL

    }
    public void SIAnimalAnswer(){
        //ONLY FOR USEFILE, TODO MANUAL
        ReadCommand();

        if (answer.equals("end")){
            state=State.Cleanup;
            return;
        }

        int direction = Integer.parseInt(answer);
        int i1 = MovingAnimals.get(MovingAnimalIndex).second;
        int i2 = MovingAnimals.get(MovingAnimalIndex).third;
        if (i1==0){
            Panda p = MovingPandas.get(i2).first;
            Tile t = MovingPandas.get(i2).second;
            boolean b = MovingPandas.get(i2).third;
            if (!b)return;
            p.Move(direction);
            Tile newtile = MovingPandas.get(MovingAnimalIndex).first.GetTile();
            MovingPandas.get(MovingAnimalIndex).second= newtile;
            MovingPandas.get(MovingAnimalIndex).third=CheckIfCought(p);


        }
        else{
            Orangutan o = MovingOrangutans.get(i2).first;
            o.Move(direction);
            Tile newtile = MovingOrangutans.get(MovingAnimalIndex).first.GetTile();
            MovingOrangutans.get(i2).second=newtile;
            for (Triple<Panda,Tile,Boolean> triple : MovingPandas){
                if (newtile==triple.second){
                    triple.third=false;
                }
            }
            IsLetGo(o);
        }
        if (MovingAnimalIndex==MovingAnimals.size()-1)MovingAnimalIndex=0;
        else MovingAnimalIndex++;
    }
    public boolean CheckIfCought(Panda p){
        Tile tile = p.GetTile();
        for (Triple<Orangutan,Tile,Boolean> triple : MovingOrangutans){
            if (tile==triple.second) return false;
        }
        return true;
    }


    int MovingThingIndex=0;
    public void SIThingAsk(){
        //ONLY FOR USEFILE, TODO MANUAL
    }
    public void SIThingAnswer(String type){
        //ONLY FOR USEFILE, TODO MANUAL
        ReadCommand();
        if(answer.equals("end")){
            state=State.Cleanup;
            return;
        }
        boolean action = Boolean.parseBoolean(answer);

        if (action) MovingThings.get(MovingThingIndex).Notify();

        if (MovingThingIndex==MovingAnimals.size()-1)MovingThingIndex=0;
        else MovingThingIndex++;

    }
    public void SIRAnimalAnswer(String type){
        //ONLY FOR USEFILE, TODO MANUAL
    }
    public void SIRThingAnswer(String type){
        //ONLY FOR USEFILE, TODO MANUAL

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

        super.Print();

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
    boolean entreance_get=false;
    public void CTile(String name, int sides){
        Tile tile = new Tile();
        tile.sides = sides;
        Tiles.put(name,tile);
        super.AddTile(tile);
        if (!entreance_get){
            super.SetEntrance(tile);
            entreance_get=true;
        }
    }
    public void CWardrobe(String name, int sides){
        Wardrobe war = new Wardrobe();
        war.sides = sides;
        Tiles.put(name,war);
        super.AddTile(war);
    }
    public void CWeakTile(String name, int sides){
        WeakTile wt = new WeakTile();
        wt.sides=sides;
        Tiles.put(name,wt);
        super.AddTile(wt);
    }
    public void CChair(String name1, String name2){
        Chair chair = new Chair();
        Tile tile = Tiles.get(name2);
        tile.SetThing(chair);
        chair.SetTile(tile);
        Things.put(name1,chair);
        MovingThings.add(chair);
        super.AddThing(chair);
    }
    public void CChocolateMachine(String name1,String name2){
        ChocolateMachine cm = new ChocolateMachine();
        Tile tile = Tiles.get(name2);
        tile.SetThing(cm);
        cm.SetTile(tile);
        Things.put(name1,cm);
        MovingThings.add(cm);
        super.AddThing(cm);
    }
    public void CSlotMachine(String name1,String name2){
        SlotMachine sm = new SlotMachine();
        Tile tile = Tiles.get(name2);
        tile.SetThing(sm);
        sm.SetTile(tile);
        Things.put(name1,sm);
        MovingThings.add(sm);
        super.AddThing(sm);
    }
    public void CExit(String name, int sides){
        Exit exit = new Exit();
        exit.sides=sides;
        Tiles.put(name,exit);
        super.AddTile(exit);
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
        super.AddPanda(p);
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
        super.AddPanda(p);
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
        super.AddPanda(p);
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
        super.AddPanda(p);
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
        super.AddOrangutan(o);
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
        String name="";
        Set<String> keys = Animals.keySet();
        for(String k:keys){
                if (a == Animals.get(k)){
                    name=k;
                    break;
            }
        }

        int i1,i2;
        for (Triple<String,Integer,Integer> t:MovingAnimals){
            if (t.first.equals(name)){
                i1=t.second;i2=t.third;
                if (i1==0) MovingPandas.remove(i2);
                if (i2==1) MovingOrangutans.remove(i2);
                break;
            }
        }

        super.DeleteAnimal(a);
    }


}
