import java.util.ArrayList;

/**
 *
 * @version 17-10-2020
 * @author Team Group
 */
public class Country {
    private final ArrayList<Country> neighbor;
    private final String name;
    private int troops;


    public Country(String name) {
        this.name = name;
        troops = 1;
        neighbor = new ArrayList<>();
    }

    public void addNeighbor(Country neighbor){
        this.neighbor.add(neighbor);
    }

    public ArrayList<Country> getNeighbors() {
        return neighbor;
    }

    public void addTroop(int num){
        if (num == 1) System.out.println(num + " Troop added to " + name);
        else System.out.println(num + " Troops added to " + name);

        troops += num;
    }

    public int getTroops() {
        return troops;
    }

    public void removeTroops(int num) {
        troops -= num;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name + "  Troops: " + troops;
    }
}
