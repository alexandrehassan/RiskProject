import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Player class represents the individual players in the Risk game.
 * Each player has a name, a list of countries they own and whether or not they have been eliminated.
 * <p>
 * The state of the player evolves as they acquire or lose countries.
 *
 * @author Team Group - Alexandre Hassan, Jonah Gaudet
 * @version 17-10-2020
 */

public class Player {
    private final LinkedList<Country> countries;
    private final String name;

    /**
     * Default constructor for the class Player.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        if (name == null) throw new IllegalArgumentException("Player name cannot be null");
        if (name.trim().equals("")) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        this.name = name;
        countries = new LinkedList<>();
    }

    /**
     * Adds a country to the ones owned by the Player
     *
     * @param country country to be added to the player's countries.
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    public void assignBeginningTroops(int beginningTroops) {
        //To stop too many troops from being assigned to a single country we set a max number of troops on one country
        //The maximum should be at least 4
        int maxTroops = Math.max(beginningTroops / countries.size() + 2, 4);

        int random;
        int assigned = countries.size();
        while (assigned < beginningTroops) {
            random = ThreadLocalRandom.current().nextInt(0, countries.size());
            if (countries.get(random).getTroops() < maxTroops) {
                countries.get(random).addTroop(1);
                assigned++;
            }
        }
    }

    /**
     * Gives the name of Player
     *
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Whether or not the player is eliminated
     *
     * @return True if Eliminated, False otherwise
     */
    public boolean isEliminated() {
        return countries.size() == 0;
    }

    /**
     * Gives the number of countries owned by Player.
     *
     * @return the size of countries.
     */
    public int numberOfCountries() {
        return countries.size();
    }

    /**
     * Removes a country from the countries player owns.
     *
     * @param c the country to be removed.
     */
    public void lost(Country c) {
        countries.remove(c);
    }

    /**
     * Checks if the player owns the given country
     *
     * @param country the name of the country to be checked.
     * @return True if the player owns the country False otherwise
     */
    public boolean hasCountry(Country country) {
        return countries.contains(country);
    }

    /**
     * Checks if the player owns the given country
     *
     * @param country the name of the country to be checked.
     * @return True if the player owns the country False otherwise
     */
    public boolean hasCountry(String country) {
        for (Country c : countries) {
            if (c.getName().equals(country)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player owns the countries
     *
     * @param countries the countries to be checked
     * @return True if the player owns the countries False otherwise
     */
    public boolean hasCountries(ArrayList<Country> countries) {
        return this.countries.containsAll(countries);
    }

    /**
     * Returns the current state of the player.
     *
     * @return a string with the current state of the player.
     */
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder("[" + name + "]\n");

        for (Country country : countries) stringBuilder.append(country.toString()).append("\n");
        return stringBuilder.toString();
    }

    /**
     * Sorts the players countries in alphabetical order.
     */
    public void sortCountries() {
        countries.sort(Comparator.comparing(Country::getName));
    }

    /**
     * Checks if a path is owned by this player between the two countries (i.e. if it is possible to move troops from
     * start - finish.
     *
     * @param start  starting country
     * @param finish destination country
     * @return true if there is a path that exists between start and finish that is owned by Player
     */
    public boolean pathExists(Country start, Country finish) {
        if (!(countries.contains(start) && countries.contains(finish))) return false;
        ArrayList<Country> accessibleCountries = new ArrayList<>();
        accessibleCountries.add(start);
        getAccessibleCountries(start, finish, accessibleCountries);

        return accessibleCountries.contains(finish);
    }

    /**
     * Helper method that gets the accessible countries iteratively
     *
     * @param country             the starting country
     * @param goal                the destination
     * @param accessibleCountries every accessible country.
     */
    private void getAccessibleCountries(Country country, Country goal, ArrayList<Country> accessibleCountries) {
        for (Country neighbor : country.getNeighbors()) {
            if (!countries.contains(neighbor)) continue;
            if (neighbor.equals(goal)) {
                accessibleCountries.add(neighbor);
                return;
            }
            if (!accessibleCountries.contains(neighbor)) {
                accessibleCountries.add(neighbor);
                getAccessibleCountries(neighbor, goal, accessibleCountries);
            }
        }
    }

    /**
     * Gets all of the countries that are on the outer perimeter of a player's territory.
     *
     * @return an Array Containing all exterior countries.
     */
    public ArrayList<Country> getPerimeterCountries() {
        ArrayList<Country> perimeterCountries = new ArrayList<>();
        for (Country c : countries) {
            if (!countries.containsAll(c.getNeighbors())) perimeterCountries.add(c);
        }
        return perimeterCountries;
    }

    /**
     * Gets all of the countries that are not on the outer perimeter of a player's territory.
     *
     * @return an Array Containing all interior countries.
     */
    public ArrayList<Country> getInnerCountries() {
        ArrayList<Country> innerCountries = new ArrayList<>();
        for (Country c : countries) {
            if (countries.containsAll(c.getNeighbors())) innerCountries.add(c);
        }
        return innerCountries;
    }


    /**
     * Gets all countries
     *
     * @return all countries
     */
    public LinkedList<Country> getCountries() {
        return countries;
    }

    public int getNumberOfTroops() {
        int count = 0;
        for (Country country : countries) {
            count += country.getTroops();
        }
        return count;
    }

    public int troopSelect(int minimum, int maximum) {
        if (minimum == maximum)
            return minimum;

        int toSelect = -1;
        while (toSelect < minimum || toSelect > maximum) {
            toSelect = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Number of troops (between " + minimum + " and " + maximum + "): ",
                    "Get number of troops",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    ""));
        }
        return toSelect;
    }

    public void handleError(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    public void handleMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
