import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * The Player class represents the individual players in the Risk game.
 * Each player has a name, a list of countries they own and whether or not they have been eliminated.
 *
 * The state of the player evolves as they acquire or lose countries.
 *
 * @version 17-10-2020
 * @author Team Group - Alexandre Hassan, Jonah Gaudet
 */

public class Player {
    private final LinkedList<Country> countries;
    private final String name;
    private boolean eliminated;

    /**
     * Default constructor for the class Player.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        this.name = name;
        countries = new LinkedList<>();
        eliminated = false;
    }

    /**
     * Gets the list of countries Player owns.
     *
     * @return ArrayList of countries owned by the player
     */
    public LinkedList<Country> getCountries() {
        return countries;
    }

    /**
     * Adds a country to the ones owned by the Player
     *
     * @param country country to be added to the player's countries.
     */
    public void addCountry(Country country){
        countries.add(country);
    }

    /**
     * Gives the number of countries owned by Player.
     *
     * @return the size of countries.
     */
    public int getCountrySize(){
        return countries.size();
    }

    /**
     * Gives the name of Player
     *
     * @return the name of the player.
     */
    public String getName () {
        return name;
    }

    /**
     * Whether or not the player is eliminated
     *
     * @return True if Eliminated, False otherwise
     */
    public boolean isEliminated () {
        return eliminated;
    }

    /**
     * Triggers the check to see whether of not player is eliminated
     */
    public void checkEliminated () {
        setEliminated(!(countries.size() > 0));
    }

    /**
     * Method used to set the value of eliminated.
     * @param isEliminated the value to set
     */
    private void setEliminated (boolean isEliminated) {
        this.eliminated = isEliminated;
    }

    /**
     * Checks if the player owns the country with the given name
     *
     * @param name the name of the country to be checked.
     * @return True if the player owns the country False otherwise
     */
    public boolean hasCountry (String name) {
        for (Country c : countries)
            if (c.toString().equals(name))
                return true;
        return false;
    }

    /**
     * Gets the country object with the given name
     *
     * @param name the name of the country to be found
     * @return the object of the name.
     */
    public Country getCountry(String name) {
        for (Country c : countries)
            if (c.toString().equals(name))
                return c;
        throw new IllegalArgumentException(name + " is not a valid country");
    }

    /**
     * Prints the current state of the player.
     *
     * TODO: Change the toString of countries to output the country name and then the number of troops.
     */
    public void print(){
        System.out.println("[" + name + "]");
        for (Country country: countries) {
            System.out.println(country.toString());
        }
    }

    /**
     * Sorts the players countries in alphabetical order.
     */
    public void sortCountries () {
        countries.sort(Comparator.comparing(Country::getName));
    }

//    public void testPathExists () {
//        for (Country c : countries)
//            System.out.println(c.toString());
//
//        for (Country c : countries) {
//            System.out.print("Path between " + countries.get(0) + " and " + c.toString());
//            if (pathExists(countries.get(0), c))
//                System.out.println(" exists");
//            else
//                System.out.println(" does not exist");
//        }
//    }

    /**
     * Checks if a path is owned by this player between the two countries (i.e. if it is possible to move troops from
     * start - finish.
     *
     * @param start starting country
     * @param finish destination country
     * @return true if there is a path that exists between start and finish that is owned by Player
     */
    public boolean pathExists (Country start, Country finish) {
        if (!countries.contains(start) || !countries.contains(finish)) {
            System.out.println("Player does not own both countries");
            return false;
        }
        ArrayList<Country> accessibleCountries = new ArrayList<>();
        accessibleCountries.add(start);
        getAccessibleCountries(start, finish, accessibleCountries);

        return accessibleCountries.contains(finish);
    }

    /**
     * Helper method that gets the accessible countries iteratively
     *
     * @param country the starting country
     * @param finish the destination
     * @param accessibleCountries every accessible country.
     */
    private void getAccessibleCountries (Country country, Country finish, ArrayList<Country> accessibleCountries) {
        for (Country n : country.getNeighbors()) {
            if (!countries.contains(n))
                continue;
            if (n.equals(finish)) {
                accessibleCountries.add(n);
                return;
            }
            if (!accessibleCountries.contains(n)) {
                accessibleCountries.add(n);
                getAccessibleCountries(n, finish, accessibleCountries);
            }
        }
    }
}
