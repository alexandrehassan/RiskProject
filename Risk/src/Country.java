import java.util.ArrayList;

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

    public ArrayList<Country> getNeighbor() {
        return neighbor;
    }

    public void addTroop(int num){
        troops =  troops + num;
    }



    public int getTroops() {
        return troops;
    }

    @Override
    public String toString() {
        return name;
    }
}
