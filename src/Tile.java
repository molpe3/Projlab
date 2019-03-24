import java.util.HashMap;
import java.util.Map;

public class Tile {

    public boolean AcceptPanda(Panda p,Animal animal) {
        System.out.println("AcceptPanda(Panda p)");
        if (animal!=null) {
            animal.CollideWithPanda(p);
            return false;
        }
        return true;
    }
    public boolean AcceptOrangutan(Orangutan o, Animal animal) {
        System.out.println("AcceptOrangutan(Orangutan o)");
        if (animal!=null){
            animal.CollideWithOrangutan(o);
            return false;
        }
        return true;
    }
    public void RemoveAnimal() {
        System.out.println("RemoveAnimal()");

    }
    public void JumpedOn() {
        System.out.println("JumpedOn()");
    }
    public Tile GetNeighbor(int side, HashMap<Integer,Tile> neighbors) {
        System.out.println("GetNeighbor(int side)");
        return neighbors.get(side);
    }
    public int GetSides(int sides) {
        System.out.println("GetSides()");
        return sides;
    }
    public int CompareTile(Tile tile,HashMap<Integer,Tile> neighbors) {
        System.out.println("CompareTile(Tile t)");
        if (this.equals(tile)) return -1;

        //Find tile:
        for(Map.Entry<Integer,Tile> it : neighbors.entrySet()) {
            if(tile.equals( it.getValue() )) {
                return it.getKey();
            }
        }

        return 0;
    }
    public void SetAnimal (Animal a) {
        System.out.println("SetAnimal(Animal a)");
    }
    public Animal GetAnimal(Animal animal){
        System.out.println("GetAnimal()");
        return animal;
    }
}