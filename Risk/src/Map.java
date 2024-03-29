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

    /**
     * Checks if the map is valid by ensuring that there exists a path between all countries
     * @return true the map is valid, false if not
     */
    public boolean checkMapValidity () {
        HashMap<String, Boolean> accessibleCountries = new HashMap<>();
        String firstCountry = null;
        for (String key: countries.keySet()) {
            if (firstCountry == null)
                firstCountry = key;
            accessibleCountries.put(key, false);
        }
        accessibleCountries.put(firstCountry, true);
        getAccessibleCountries(accessibleCountries, firstCountry);

        for (String key: accessibleCountries.keySet()) {
            if (!accessibleCountries.get(key))
                return false;
        }
        return true;
    }

    /**
     * Helper method that iteratively gets all countries that can be accessed by the current
     * country without checking countries that have already been accessed
     *
     * @param accessibleCountries hashmap of countries with whether they have been accessed
     *                              (true/false)
     * @param currentCountry      the current country
     */
    public void getAccessibleCountries (HashMap<String, Boolean> accessibleCountries, String currentCountry) {
        Country country = getCountry(currentCountry);
        for (Country neighbor : country.getNeighbors()) {
            if (!accessibleCountries.get(neighbor.getName())) {
                accessibleCountries.put(neighbor.getName(), true);
                getAccessibleCountries(accessibleCountries, neighbor.getName());
            }
        }
    }

}
