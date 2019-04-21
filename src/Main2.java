import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main2 extends Game {

    //Variables for processing commands
    private ArrayList<String> commandfile = new ArrayList<>();
    private int commandindex=0;
    private String answer="";
    private ArrayList<String> createcommands = new ArrayList<>();
    private ArrayList<String> interactivecommands = new ArrayList<>();

    //Items and names
    private HashMap<Tile, String> TileNames = new HashMap<>();
    private HashMap<Orangutan, String> OrangutanNames = new HashMap<>();
    private HashMap<Panda, String> PandaNames = new HashMap<>();
    private HashMap<Thing, String> ThingNames = new HashMap<>();

    private HashMap<String,Tile> NameTiles = new HashMap<>();
    private HashMap<String,Orangutan> NameOrangutans = new HashMap<>();
    private HashMap<String,Panda> NamePandas= new HashMap<>();
    private HashMap<String,Thing> NamesThings = new HashMap<>();

    private ArrayList<String> AnimalOrder=new ArrayList<>();

    //variables for testflow
    boolean create = true;
    boolean start = false;
    boolean end = false;

    //misc variables
    boolean firsttile=true;
    int animalindex=0;
    int interactionindex=0;
    int thingindex=0;

    //Main Methods

    public void Everything(String path){
        try {
            ReadFile(path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Game game= new Game();
        OrganizeFile();
        Create();
        printarrays(interactivecommands);
        InteractionControll();
        super.Print();
        //printarrays();

    }

    public void printarrays(){
        for(Tile t:super.getTiles()){
            t.Print();
        }
    }
    public void printarrays(ArrayList<String> list){
        for(String s:list){
            System.out.println(s);
        }
    }

    //Methods

    public void ReadFile(String path) throws IOException {

        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                commandfile.add(line);
            }
        }
    }

    public void OrganizeFile(){
        boolean interactive=false;
        for(String s:commandfile){
            if(!interactive){
                if(!s.equals("start")){
                    createcommands.add(s);
                }
                else interactive=true;
            }
            else{
                if(!s.equals("end")){
                    interactivecommands.add(s);
                }
            }
        }
    }

    public void Create(){
        for(String s:createcommands){
            CreateItem(s);
        }
    }

    public Animal NextAnimal_Helper(){
        String name = AnimalOrder.get(animalindex);
        if(animalindex>=AnimalOrder.size()-1){
            animalindex=0;

        }
        else animalindex++;

        Panda p;
        Orangutan o;
        p = NamePandas.get(name);
        if(p==null){
            o=NameOrangutans.get(name);
            return o;
        }
        else{
            //if(p.GetPuller()!=null) return null;
            return p;
        }
    }

    public Animal NextAnimal(){
        Animal animal = null;
        animal=NextAnimal_Helper();
        return animal;
    }

    public String NextInteraction(){
        if(interactionindex==interactivecommands.size())return null;
        String line = interactivecommands.get(interactionindex);
        /*if(interactionindex==0){
            interactionindex++;
            return line;
        }*/

        interactionindex++;
        return line;
    }

    public int InteractClassifier(String interact){
        if(interact.equals("Whistle")) return 0;
        else if (interact.equals("nowhistle")) return 3;
        else if (interact.equals("letgo")) return 5;
        else if (interact.equals("noletgo")) return 4;
        else if (interact.equals("Jingle")) return 1;
        else if (interact.equals("nojingle")) return 2;
        else return 6;
    }

    public Thing NextThing(){
        Thing th=super.getThings().get(thingindex);
        thingindex++;
        return th;
    }

    public void InteractionControll(){
        String interact = NextInteraction();
        while(interact!=null) {
            int interclass = InteractClassifier(interact);
            switch (interclass) {
                case 0:
                case 1:
                    Thing thing = NextThing();
                    if (thing==null)continue;
                    thing.Notify();
                    break;
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    Orangutan orangutan = NameOrangutans.get(AnimalOrder.get(0));
                    orangutan.LetGo();
                    break;
                case 6:
                    int direction = Integer.parseInt(interact);
                    if (direction==0)continue;
                    Animal animal = NextAnimal();
                    if (animal ==null)continue;
                    animal.Move(direction);

            }
            interact=NextInteraction();
        }

    }





    public void CreateItem(String string){
        String[] fullcommand = string.split(" ");
        String command=fullcommand[0];
        //System.out.println(command);
        //System.out.println(fullcommand[1]);
        //System.out.println(fullcommand[2]);
        //Vars for creating
        Tile tile;
        Orangutan orangutan;
        Panda panda;
        int sides;
        int sides2;
        String name1;
        String name2;
        Tile tile2;
        Thing thing;
        int number;
        boolean condition;
        WeakTile weaktile;
        TiredPanda tiredpanda;
        Animal animal;
        Panda panda2;
        Wardrobe wardrobe;
        Wardrobe wardrobe2;

        //2 fele name tiles
        //super: activeorangutans, orangutans, pandas, things, tiles
        //firsttile

        switch (command) {
            case ("Tile"):
                //name and sides
                name1=fullcommand[1];
                sides=Integer.parseInt(fullcommand[2]);

                Tile newTile = new Tile();
                newTile.SetName(name1);
                NameTiles.put(name1,newTile);
                TileNames.put(newTile,name1);
                super.AddTile(newTile);
                if(firsttile){
                    firsttile=false;
                    super.SetEntrance(newTile);
                }
                break;

            case ("Wardrobe"):
                //name and sides
                name1=fullcommand[1];
                sides=Integer.parseInt(fullcommand[2]);

                Wardrobe newwardrobe=new Wardrobe();
                newwardrobe.SetName(name1);
                NameTiles.put(name1,newwardrobe);
                TileNames.put(newwardrobe,name1);
                super.AddTile(newwardrobe);
                break;

            case ("WeakTile"):
                //name and sides
                name1=fullcommand[1];
                sides=Integer.parseInt(fullcommand[2]);

                WeakTile newweak = new WeakTile();
                newweak.SetName(name1);
                NameTiles.put(name1,newweak);
                TileNames.put(newweak,name1);
                super.AddTile(newweak);
                break;

            case ("Chair"):
                //name and tilename
                name1=fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                Chair newchair = new Chair();
                newchair.SetName(name1);
                NamesThings.put(name1,newchair);
                ThingNames.put(newchair,name1);
                super.AddThing(newchair);
                newchair.SetTile(tile);
                tile.SetThing(newchair);
                break;

            case ("ChocolateMachine"):
                //name and tilename
                name1=fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                ChocolateMachine cm = new ChocolateMachine();
                cm.SetName(name1);
                NamesThings.put(name1,cm);
                ThingNames.put(cm,name1);
                super.AddThing(cm);
                tile.SetThing(cm);
                cm.SetTile(tile);
                break;

            case ("SlotMachine"):
                //name and tilename
                name1=fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                SlotMachine sm = new SlotMachine();
                sm.SetName(name1);
                NamesThings.put(name1,sm);
                ThingNames.put(sm,name1);
                super.AddThing(sm);
                tile.SetThing(sm);
                sm.SetTile(tile);
                break;

            case ("Exit"):
                name1=fullcommand[1];
                sides=Integer.parseInt(fullcommand[2]);

                Exit exit = new Exit();
                exit.SetName(name1);
                NameTiles.put(name1,exit);
                TileNames.put(exit,name1);
                super.AddTile(exit);
                break;

                //2 fele name tiles
            //super: activeorangutans, orangutans, pandas, things, tiles
            //firsttile
            case ("Panda"):
                //name tile
                panda = new Panda();
                name1 = fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                panda.SetName(name1);
                panda.SetTile(tile);
                tile.SetAnimal(panda);
                NamePandas.put(name1,panda);
                PandaNames.put(panda,name1);
                super.AddPanda(panda);
                AnimalOrder.add(name1);
                break;

            case ("JumpingPanda"):
                //name tile
                panda = new JumpingPanda();
                name1 = fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                panda.SetName(name1);
                panda.SetTile(tile);
                tile.SetAnimal(panda);
                NamePandas.put(name1,panda);
                PandaNames.put(panda,name1);
                super.AddPanda(panda);
                AnimalOrder.add(name1);
                break;

            case ("ScaredPanda"):
                //name tile
                panda = new ScaredPanda();
                name1 = fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                panda.SetName(name1);
                panda.SetTile(tile);
                tile.SetAnimal(panda);
                NamePandas.put(name1,panda);
                PandaNames.put(panda,name1);
                super.AddPanda(panda);
                AnimalOrder.add(name1);
                break;

            case ("TiredPanda"):
                //name tile
                panda = new TiredPanda();
                name1 = fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                panda.SetName(name1);
                panda.SetTile(tile);
                tile.SetAnimal(panda);
                NamePandas.put(name1,panda);
                PandaNames.put(panda,name1);
                super.AddPanda(panda);
                AnimalOrder.add(name1);
                break;

            case ("Orangutan"):
                //name tile
                orangutan = new Orangutan();
                name1 = fullcommand[1];
                name2=fullcommand[2];
                tile=NameTiles.get(name2);

                orangutan.SetName(name1);
                orangutan.SetTile(tile);
                tile.SetAnimal(orangutan);
                NameOrangutans.put(name1,orangutan);
                OrangutanNames.put(orangutan,name1);
                super.AddOrangutan(orangutan);
                AnimalOrder.add(name1);
                break;

            case ("Random"):

                break;

            case ("Connect"):
                //name side name side
                name1=fullcommand[1];
                sides=Integer.parseInt(fullcommand[2]);
                name2=fullcommand[3];
                sides2=Integer.parseInt(fullcommand[4]);
                tile=NameTiles.get(name1);
                tile2=NameTiles.get(name2);

                tile.AddNeighbor(sides,tile2);
                tile2.AddNeighbor(sides2,tile);
                break;

            case ("WardrobeConnect"):
                //name name
                name1=fullcommand[1];
                name2=fullcommand[2];
                wardrobe=(Wardrobe) NameTiles.get(name1);
                wardrobe2=(Wardrobe) NameTiles.get(name2);

                wardrobe.SetPair(wardrobe2);
                wardrobe2.SetPair(wardrobe);
                break;

            case ("Subscribe"):
                //name name
                name1=fullcommand[1];
                name2=fullcommand[2];
                panda = NamePandas.get(name1);
                thing = NamesThings.get(name2);

                thing.Subscribe(panda);
                panda.observables.add(thing);
                break;

            case ("ExitConnect"):
                //name name
                name1=fullcommand[1];
                name2=fullcommand[2];
                exit =(Exit) NameTiles.get(name1);
                tile2=NameTiles.get(name2);

                exit.SetEntrance(tile2);
                super.SetEntrance(tile2);
                break;

            case ("WeakTileSetLife"):
                //name int
                name1=fullcommand[1];
                number=Integer.parseInt(fullcommand[2]);
                weaktile=(WeakTile) NameTiles.get(name1);

                weaktile.SetSteps(number);
                break;

            case ("WeakTileSetBroken"):
                //name bool
                name1=fullcommand[1];
                condition=Boolean.parseBoolean(fullcommand[2]);
                weaktile=(WeakTile) NameTiles.get(name1);

                weaktile.SetBroken(condition);
                break;

            case ("OrangutanSetCooldown"):
                //name int

                break;

            case ("OrangutanSetPoint"):
                //name int
                name1=fullcommand[1];
                orangutan=NameOrangutans.get(name1);
                number=Integer.parseInt(fullcommand[2]);

                orangutan.SetPoints(number);
                break;

            case ("SetTiredPandaTired"):
                //name bool
                name1=fullcommand[1];
                condition=Boolean.parseBoolean(fullcommand[2]);
                tiredpanda = (TiredPanda) NamePandas.get(name1);

                tiredpanda.SetTired(condition);
                break;

            case ("SetPuller"):
                //name name
                name1=fullcommand[1];
                name2=fullcommand[2];
                panda=NamePandas.get(name1);

                panda2=NamePandas.get(name2);
                if(panda2==null){
                    orangutan=NameOrangutans.get(name2);
                    panda.SetPuller(orangutan);
                    orangutan.SetPulled(panda);
                }
                else{
                    panda.SetPuller(panda2);
                    panda2.SetPulled(panda);
                }

                break;
        }
    }



}
