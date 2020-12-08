import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Map class
 * Map creates a new map, and loads it with the countries and sets each country's neighbors
 * Map creates continents
 *
 * @author Baillie Noell, Sarah Abdallah, Jonah Gaudet, Alexandre Hassan - Team Group
 * @version 4: Oct 19 2020
 */

public class Map {
    private final HashMap<String, Country> countries;
    private final HashMap<String, Continent> continents;
    private final HashMap<Country, Point> positionsCountries;
    public static final String BLACK = "#000000";


    /**
     * Constructor for the Map class.
     * Generates a map of the classic Risk game.
     */
    public Map(HashMap<String, Country> countries,
               HashMap<String, Continent> continents,
               HashMap<Country, Point> positionsCountries) {
        this.countries = countries;
        this.continents = continents;
        this.positionsCountries = positionsCountries;
    }


    /**
     * Gets the country objects for all the countries with the given names.
     *
     * @param toGet an Array containing the names of every country to return
     * @return an array of country objects.
     */
    private ArrayList<Country> getCountries(String[] toGet) {
        ArrayList<Country> found = new ArrayList<>();
        for (String countryString : toGet) {
            found.add(countries.get(countryString));
        }
        return found;
    }

    /**
     * Gives the country object of the country with the given name.
     *
     * @param name the name of the country
     * @return the country object with the correct name.
     * @throws IllegalArgumentException if there is no country with name name.
     */
    public Country getCountry(String name) {
        Country country = countries.get(name);
        if (country != null) return country;
        throw new IllegalArgumentException(name + " is not a valid country");
    }

    /**
     * Gives all the continents of the map.
     *
     * @return ArrayList of continents.
     */
    public ArrayList<Continent> getContinents() {
        ArrayList<Continent> continentArrayList = new ArrayList<>();
        for (String key : continents.keySet()) {
            continentArrayList.add(continents.get(key));
        }
        return continentArrayList;
    }

    /**
     * Gives the continent object of the country with the given name.
     *
     * @param name the name of the continent
     * @return the continent object with the correct name.
     * @throws IllegalArgumentException if there is no continent with name name.
     */
    public Continent getContinent(String name) {
        Continent continent = continents.get(name);
        if (continent != null) return continent;
        throw new IllegalArgumentException(name + " is not a valid continent");
    }

    /**
     * Shuffles the keys of the countries and returns them as an array.
     *
     * @return an Array containing all the keys in a random order.
     */
    public ArrayList<String> getShuffledKeys() {
        ArrayList<String> keys = new ArrayList<>(countries.keySet());
        Collections.shuffle(keys);
        return keys;
    }

    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> countryArrayList = new ArrayList<>();
        for (String key : countries.keySet()) {
            countryArrayList.add(countries.get(key));
        }
        return countryArrayList;
    }

    public Point getPosition(Country country){
        return positionsCountries.get(country);
    }
}
