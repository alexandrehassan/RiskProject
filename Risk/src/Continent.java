import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Continents store a list of countries that they contain
 *
 * @author Team Group - Baillie Noell, Alexandre Hassan
 * @version 3: October 18 2020
 */
public class Continent {
    private final String name;
    private ArrayList<Country> countries;
    private final int reinforcements;

    /**
     * Default constructor for class Continent.
     *
     * @param name the name of the continent
     * @param reinforcements the number of bonus reinforcements given if all of the continent is owned by one player.
     */
    public Continent(String name, int reinforcements) {
        this.name = name;
        this.reinforcements = reinforcements;
        countries = new ArrayList<>();
    }

    /**
     * Gives the name of the continent.
     *
     * @return the name of the continent.
     */
    public String getName() {
        return name;
    }

    /**
     * Gives all of the countries contained in the continent.
     *
     * @return the countries that are part of the continent.
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * Gives the number of reinforcement the continent is worth.
     *
     * @return the number of bonus reinforcements.
     */
    public int getReinforcements() {
        return reinforcements;
    }

    /**
    * Adds the given countries to the continent.
    *
    * @param countries the countries to be added.
    */
    public void addCountries(ArrayList<Country> countries){
        Set<Country> countryHashSet = new HashSet<>(this.countries);
        countryHashSet.addAll(countries);
        this.countries = new ArrayList<>(countryHashSet);
    }
}
