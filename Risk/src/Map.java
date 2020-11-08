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

    //Countries
    //North America
    public static final String ALASKA = "Alaska";
    public static final String ALBERTA = "Alberta";
    public static final String CENTRAL_AMERICA = "Central America";
    public static final String EASTERN_UNITED_STATES = "Eastern United States";
    public static final String GREENLAND = "Greenland";
    public static final String NORTHWEST_TERRITORY = "Northwest Territory";
    public static final String ONTARIO = "Ontario";
    public static final String QUEBEC = "Quebec";
    public static final String WESTERN_UNITED_STATES = "Western United States";

    //South America
    public static final String ARGENTINA = "Argentina";
    public static final String BRAZIL = "Brazil";
    public static final String PERU = "Peru";
    public static final String VENEZUELA = "Venezuela";

    //Europe
    public static final String GREAT_BRITAIN = "Great Britain";
    public static final String ICELAND = "Iceland";
    public static final String NORTHERN_EUROPE = "Northern Europe";
    public static final String SCANDINAVIA = "Scandinavia";
    public static final String SOUTHERN_EUROPE = "Southern Europe";
    public static final String UKRAINE = "Ukraine";
    public static final String WESTERN_EUROPE = "Western Europe";

    //Africa
    public static final String CONGO = "Congo";
    public static final String EAST_AFRICA = "East Africa";
    public static final String EGYPT = "Egypt";
    public static final String MADAGASCAR = "Madagascar";
    public static final String NORTH_AFRICA = "North Africa";
    public static final String SOUTH_AFRICA = "South Africa";

    //Asia
    public static final String AFGHANISTAN = "Afghanistan";
    public static final String CHINA = "China";
    public static final String INDIA = "India";
    public static final String IRKUTSK = "Irkutsk";
    public static final String JAPAN = "Japan";
    public static final String KAMCHATKA = "Kamchatka";
    public static final String MIDDLE_EAST = "Middle East";
    public static final String MONGOLIA = "Mongolia";
    public static final String SIAM = "Siam";
    public static final String SIBERIA = "Siberia";
    public static final String URAL = "Ural";
    public static final String YAKUTSK = "Yakutsk";

    //Australia
    public static final String EASTERN_AUSTRALIA = "Eastern Australia";
    public static final String INDONESIA = "Indonesia";
    public static final String NEW_GUINEA = "New Guinea";
    public static final String WESTERN_AUSTRALIA = "Western Australia";

    //Continents
    public static final String NORTH_AMERICA = "North America";
    public static final String SOUTH_AMERICA = "South America";
    public static final String EUROPE = "Europe";
    public static final String AFRICA = "Africa";
    public static final String ASIA = "Asia";
    public static final String AUSTRALIA = "Australia";


    /**
     * Constructor for the Map class.
     * Generates a map of the classic Risk game.
     */
    public Map() {
        countries = new HashMap<>();
        continents = new HashMap<>();
        this.loadMap();
        this.loadContinents();
        this.setNeighbors();
    }

    /**
     * Helper method for constructor of map that creates all the country objects.
     */
    private void loadMap() {
        //North America
        countries.put(ALASKA, new Country(ALASKA));
        countries.put(ALBERTA, new Country(ALBERTA));
        countries.put(CENTRAL_AMERICA, new Country(CENTRAL_AMERICA));
        countries.put(EASTERN_UNITED_STATES, new Country(EASTERN_UNITED_STATES));
        countries.put(GREENLAND, new Country(GREENLAND));
        countries.put(NORTHWEST_TERRITORY, new Country(NORTHWEST_TERRITORY));
        countries.put(ONTARIO, new Country(ONTARIO));
        countries.put(QUEBEC, new Country(QUEBEC));
        countries.put(WESTERN_UNITED_STATES, new Country(WESTERN_UNITED_STATES));

        //South America
        countries.put(ARGENTINA, new Country(ARGENTINA));
        countries.put(BRAZIL, new Country(BRAZIL));
        countries.put(PERU, new Country(PERU));
        countries.put(VENEZUELA, new Country(VENEZUELA));

        //Europe
        countries.put(GREAT_BRITAIN, new Country(GREAT_BRITAIN));
        countries.put(ICELAND, new Country(ICELAND));
        countries.put(NORTHERN_EUROPE, new Country(NORTHERN_EUROPE));
        countries.put(SCANDINAVIA, new Country(SCANDINAVIA));
        countries.put(SOUTHERN_EUROPE, new Country(SOUTHERN_EUROPE));
        countries.put(UKRAINE, new Country(UKRAINE));
        countries.put(WESTERN_EUROPE, new Country(WESTERN_EUROPE));

        //Africa
        countries.put(CONGO, new Country(CONGO));
        countries.put(EAST_AFRICA, new Country(EAST_AFRICA));
        countries.put(EGYPT, new Country(EGYPT));
        countries.put(MADAGASCAR, new Country(MADAGASCAR));
        countries.put(NORTH_AFRICA, new Country(NORTH_AFRICA));
        countries.put(SOUTH_AFRICA, new Country(SOUTH_AFRICA));

        //Asia
        countries.put(AFGHANISTAN, new Country(AFGHANISTAN));
        countries.put(CHINA, new Country(CHINA));
        countries.put(INDIA, new Country(INDIA));
        countries.put(IRKUTSK, new Country(IRKUTSK));
        countries.put(JAPAN, new Country(JAPAN));
        countries.put(KAMCHATKA, new Country(KAMCHATKA));
        countries.put(MIDDLE_EAST, new Country(MIDDLE_EAST));
        countries.put(MONGOLIA, new Country(MONGOLIA));
        countries.put(SIAM, new Country(SIAM));
        countries.put(SIBERIA, new Country(SIBERIA));
        countries.put(URAL, new Country(URAL));
        countries.put(YAKUTSK, new Country(YAKUTSK));

        //Australia
        countries.put(EASTERN_AUSTRALIA, new Country(EASTERN_AUSTRALIA));
        countries.put(INDONESIA, new Country(INDONESIA));
        countries.put(NEW_GUINEA, new Country(NEW_GUINEA));
        countries.put(WESTERN_AUSTRALIA, new Country(WESTERN_AUSTRALIA));
    }

    /**
     * Helper method for constructor of map that sets all neighbor relations.
     */
    private void setNeighbors() {
        //North America
        getCountry(ALASKA).addNeighbor(getCountry(ALBERTA));
        getCountry(ALASKA).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(ALASKA).addNeighbor(getCountry(KAMCHATKA));
        getCountry(ALBERTA).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(ALBERTA).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(ALBERTA).addNeighbor(getCountry(ONTARIO));
        getCountry(CENTRAL_AMERICA).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(CENTRAL_AMERICA).addNeighbor(getCountry(EASTERN_UNITED_STATES));
        getCountry(CENTRAL_AMERICA).addNeighbor(getCountry(VENEZUELA));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(ONTARIO));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(QUEBEC));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(GREENLAND).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(GREENLAND).addNeighbor(getCountry(ONTARIO));
        getCountry(GREENLAND).addNeighbor(getCountry(QUEBEC));
        getCountry(GREENLAND).addNeighbor(getCountry(ICELAND));
        getCountry(NORTHWEST_TERRITORY).addNeighbor(getCountry(ONTARIO));
        getCountry(ONTARIO).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(ONTARIO).addNeighbor(getCountry(QUEBEC));

        //South America
        getCountry(ARGENTINA).addNeighbor(getCountry(BRAZIL));
        getCountry(ARGENTINA).addNeighbor(getCountry(PERU));
        getCountry(BRAZIL).addNeighbor(getCountry(PERU));
        getCountry(BRAZIL).addNeighbor(getCountry(VENEZUELA));
        getCountry(BRAZIL).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(PERU).addNeighbor(getCountry(VENEZUELA));

        //Europe
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(ICELAND));
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(NORTHERN_EUROPE));
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(ICELAND).addNeighbor(getCountry(GREENLAND));
        getCountry(ICELAND).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(UKRAINE));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(SOUTHERN_EUROPE));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(UKRAINE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(UKRAINE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(EGYPT));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(UKRAINE).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(UKRAINE).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(UKRAINE).addNeighbor(getCountry(URAL));
        getCountry(WESTERN_EUROPE).addNeighbor(getCountry(NORTH_AFRICA));

        //Africa
        getCountry(CONGO).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(CONGO).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(CONGO).addNeighbor(getCountry(SOUTH_AFRICA));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(EGYPT));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(SOUTH_AFRICA));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(MADAGASCAR));
        getCountry(EGYPT).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(MADAGASCAR).addNeighbor(getCountry(SOUTH_AFRICA));

        //Asia
        getCountry(AFGHANISTAN).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(INDIA));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(CHINA));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(URAL));
        getCountry(CHINA).addNeighbor(getCountry(INDIA));
        getCountry(CHINA).addNeighbor(getCountry(SIAM));
        getCountry(CHINA).addNeighbor(getCountry(MONGOLIA));
        getCountry(CHINA).addNeighbor(getCountry(SIBERIA));
        getCountry(CHINA).addNeighbor(getCountry(URAL));
        getCountry(INDIA).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(INDIA).addNeighbor(getCountry(SIAM));
        getCountry(IRKUTSK).addNeighbor(getCountry(KAMCHATKA));
        getCountry(IRKUTSK).addNeighbor(getCountry(MONGOLIA));
        getCountry(IRKUTSK).addNeighbor(getCountry(SIBERIA));
        getCountry(IRKUTSK).addNeighbor(getCountry(YAKUTSK));
        getCountry(JAPAN).addNeighbor(getCountry(KAMCHATKA));
        getCountry(JAPAN).addNeighbor(getCountry(MONGOLIA));
        getCountry(KAMCHATKA).addNeighbor(getCountry(MONGOLIA));
        getCountry(KAMCHATKA).addNeighbor(getCountry(YAKUTSK));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(EGYPT));
        getCountry(MONGOLIA).addNeighbor(getCountry(SIBERIA));
        getCountry(SIAM).addNeighbor(getCountry(INDONESIA));
        getCountry(SIBERIA).addNeighbor(getCountry(URAL));
        getCountry(SIBERIA).addNeighbor(getCountry(YAKUTSK));

        //Oceania
        getCountry(EASTERN_AUSTRALIA).addNeighbor(getCountry(NEW_GUINEA));
        getCountry(EASTERN_AUSTRALIA).addNeighbor(getCountry(WESTERN_AUSTRALIA));
        getCountry(INDONESIA).addNeighbor(getCountry(NEW_GUINEA));
        getCountry(INDONESIA).addNeighbor(getCountry(WESTERN_AUSTRALIA));
        getCountry(NEW_GUINEA).addNeighbor(getCountry(WESTERN_AUSTRALIA));
    }

    /**
     * Helper method for constructor of map that makes all of the continents.
     */
    private void loadContinents() {
        //Continents
        String[] NORTH_AMERICA_COUNTRIES = {ALASKA, ALBERTA, CENTRAL_AMERICA, EASTERN_UNITED_STATES,
                GREENLAND, NORTHWEST_TERRITORY, ONTARIO, QUEBEC, WESTERN_UNITED_STATES};

        String[] SOUTH_AMERICA_COUNTRIES = {ARGENTINA, BRAZIL, PERU, VENEZUELA};

        String[] EUROPE_COUNTRIES = {GREAT_BRITAIN, ICELAND, NORTHERN_EUROPE, SCANDINAVIA, SOUTHERN_EUROPE, UKRAINE, WESTERN_EUROPE};

        String[] AFRICA_COUNTRIES = {CONGO, EAST_AFRICA, EGYPT, MADAGASCAR, NORTH_AFRICA, SOUTH_AFRICA};

        String[] ASIA_COUNTRIES = {AFGHANISTAN, CHINA, INDIA, IRKUTSK, JAPAN, KAMCHATKA, MIDDLE_EAST, MONGOLIA, SIAM, SIBERIA, URAL, YAKUTSK};

        String[] AUSTRALIA_COUNTRIES = {EASTERN_AUSTRALIA, INDONESIA, NEW_GUINEA, WESTERN_AUSTRALIA};

        continents.put(NORTH_AMERICA, new Continent(NORTH_AMERICA, 5));
        continents.put(SOUTH_AMERICA, new Continent(SOUTH_AMERICA, 2));
        continents.put(EUROPE, new Continent(EUROPE, 5));
        continents.put(AFRICA, new Continent(AFRICA, 3));
        continents.put(ASIA, new Continent(ASIA, 7));
        continents.put(AUSTRALIA, new Continent(AUSTRALIA, 2));

        //North America
        getContinent(NORTH_AMERICA).addCountries(getCountries(NORTH_AMERICA_COUNTRIES));
        getContinent(SOUTH_AMERICA).addCountries(getCountries(SOUTH_AMERICA_COUNTRIES));
        getContinent(EUROPE).addCountries(getCountries(EUROPE_COUNTRIES));
        getContinent(AFRICA).addCountries(getCountries(AFRICA_COUNTRIES));
        getContinent(ASIA).addCountries(getCountries(ASIA_COUNTRIES));
        getContinent(AUSTRALIA).addCountries(getCountries(AUSTRALIA_COUNTRIES));
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
}
