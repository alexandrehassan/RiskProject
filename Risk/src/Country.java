import java.util.ArrayList;

/**
 * This class is used to hold information about the different Countries in the game of risk.
 * Every country is made of its name, troops and its neighbors
 *
 * @version 17-10-2020
 * @author Team Group - Alexandre Hassan
 */
public class Country {
    private final ArrayList<Country> neighbor;
    private final String name;
    private int troops;

    /**
     * Constructor for the country Class
     *
     * @param name the name of the country
     */
    public Country(String name) {
        this.name = name;
        troops = 1;
        neighbor = new ArrayList<>();
    }

    /**
     * Adds a neighbor to the country.
     * This means that the countries can move between each other (either for attacking or troop movement)
     *
     * @param neighbor the country to be added as a neighbor.
     */
    public void addNeighbor(Country neighbor){
        this.neighbor.add(neighbor);
    }

    /**
     * Gives an arraylist containing all of the neighbors of the country.
     *
     * @return all the countries that are neighbors with this country.
     */
    public ArrayList<Country> getNeighbors() {
        return neighbor;
    }

    /**
     * Adds troops to the country.
     * @param num the number of troops to be added. (ignores negative values.)
     */
    public void addTroop(int num){
        if(num<1) return;
        troops += num;

        if (num == 1) System.out.println(num + " Troop added to " + name);
        else System.out.println(num + " Troops added to " + name);
    }

    /**
     * Gives the number of troops posted on Country.
     *
     * @return the number of troops posted on this country.
     */
    public int getTroops() {

        return troops;
    }

    /**
     * Subtracts troops to the country.
     *
     * @param num the number of troops to be added. (ignores positive values.)
     */
    public void removeTroops(int num) {
        if(num<0) return;
        troops -= num;
    }


    /**
     * Gives the name of the country.
     *
     * @return name of this country.
     */
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name + "  Troops: " + troops;
    }
}
