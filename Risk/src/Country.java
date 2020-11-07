import java.util.ArrayList;

/**
 * This class is used to hold information about the different Countries in the game of risk.
 * Every country is made of its name, troops and its neighbors
 *
 * @author Team Group - Alexandre Hassan, Jonah Gaudet
 * @version 17-10-2020
 */
public class Country {
    private final String name;
    private final ArrayList<Country> neighbors;
    private int troops;

    /**
     * Constructor for the country Class
     *
     * @param name the name of the country
     */
    public Country(String name) {
        this.name = name;
        troops = 1;
        neighbors = new ArrayList<>();
    }

    /**
     * Gives the name of the country.
     *
     * @return name of this country.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a neighbor to the country.
     * This means that the countries can move between each other (either for attacking or troop movement)
     *
     * @param neighbor the country to be added as a neighbor.
     */
    public void addNeighbor(Country neighbor) {
        this.neighbors.add(neighbor);
        if (!(neighbor.hasNeighbor(this))) {
            neighbor.addNeighbor(this);
        }
    }

    public boolean hasNeighbor(Country c) {
        return neighbors.contains(c);
    }

    /**
     * Gets the neighbors of Country
     *
     * @return an ArrayList of neighbors.
     */
    public ArrayList<Country> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds troops to the country.
     *
     * @param num the number of troops to be added. (ignores negative values.)
     */
    public void addTroop(int num) {
        if (num < 1) return;
        troops += num;
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
        if (num < 0) return;
        troops -= num;
        if (troops < 0) troops = 0;
    }

    @Override
    public String toString() {
        return name + "  Troops: " + troops;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Country)) return false;
        Country c = (Country) o;

        if (!c.getName().equals(name)) return false;
        if(!(c.getTroops()==troops)) return false;
        return neighbors.containsAll(c.getNeighbors()) && c.getNeighbors().containsAll(neighbors);
    }
}
