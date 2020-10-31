import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * Default constructor for the class Player.
     * @param name the name of the player.
     */
    public Player(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        this.name = name;
        countries = new LinkedList<>();
    }

    /**
     * Adds a country to the ones owned by the Player
     * @param country country to be added to the player's countries.
     */
    public void addCountry(Country country){
        countries.add(country);
    }

    public void assignBeginningTroops(int beginningTroops) {
        //To stop to many troops from being assigned to a single country we set a max number of troops on one country
        //The maximum should be at least 4
        int maxTroops = Math.max(beginningTroops/countries.size() + 2, 4);

        int random;
        for(int assigned = countries.size(); assigned<beginningTroops;){
            random = ThreadLocalRandom.current().nextInt(0,countries.size());
            if(countries.get(random).getTroops() < maxTroops){
                countries.get(random).addTroop(1);
                assigned++;
            }
        }
    }

    /**
     * Gives the name of Player
     * @return the name of the player.
     */
    public String getName () {
        return name;
    }

    /**
     * Whether or not the player is eliminated
     * @return True if Eliminated, False otherwise
     */
    public boolean isEliminated () {
        return countries.size() ==0;
    }


    /**
     * Gives the number of countries owned by Player.
     * @return the size of countries.
     */
    public int NumberOfCountries(){
        return countries.size();
    }

    /**
     * Removes a country from the countries player owns.
     * @param c the country to be removed.
     */
    public void lost(Country c){
        countries.remove(c);
    }

    /**
     * Checks if the player owns the given country
     *
     * @param country the name of the country to be checked.
     * @return True if the player owns the country False otherwise
     */
    public boolean hasCountry (Country country) {
        return countries.contains(country);
    }

    /**
     * Checks if the player owns the countries
     *
     * @param countries the countries to be checked
     * @return True if the player owns the countries False otherwise
     */
    public boolean hasCountries (ArrayList<Country> countries) {
        return this.countries.containsAll(countries);
    }

    /**
     * Prints the current state of the player.
     */
    public String getInfo(){
        String s = "[" + name + "]\n";
        for (Country country: countries) {
            s += country.toString() + "\n";
        }
        return s;
    }

    /**
     * Sorts the players countries in alphabetical order.
     */
    public void sortCountries () {
        countries.sort(Comparator.comparing(Country::getName));
    }

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

    /**
     * Gets all of the countries that are on the outer perimeter of a player's territory.
     * @return an Array Containing all exterior countries.
     */
    public ArrayList<Country> getPerimeterCountries(){
        ArrayList<Country> perimeterCountries = new ArrayList<>();
        for (Country c: countries) {
            if (!countries.containsAll(c.getNeighbors())){
                perimeterCountries.add(c);
            }
        }
        return perimeterCountries;
    }

}
