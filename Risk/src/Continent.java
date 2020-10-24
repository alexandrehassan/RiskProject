import java.util.ArrayList;
/**
 * Continents store a list of countries that they contain
 *
 * @author Team Group - Baillie Noell, Alexandre Hassan
 * @version 3: October 18 2020
 */
public class Continent {
    private String name;
    private final ArrayList<Country> countries;
    private int reinforcements;

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
     * Adds a country to the continent.
     *
     * @param country the country to be added.
     */
    public void addCountry(Country country) {
        countries.add(country);
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
     * Gives the name of the continent.
     *
     * @return the name of the continent.
     */
    public String getName() {
        return name;
    }

    public void addCountries(ArrayList<Country> countries){
        this.countries.addAll(countries);
    }

}
