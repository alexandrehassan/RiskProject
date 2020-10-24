import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Map class
 * Map creates a new map, and loads it with the countries and sets each country's neighbors
 * Map creates continents
 * @author Baillie Noell, Sarah Abdallah - Team Group
 * @version 4: Oct 19 2020
 *
 */

public class Map {
    private final LinkedList<Country> countries;
    private final ArrayList<Continent> continents;

    //Countries
    //North America
    private final String ALASKA = "Alaska";
    private final String ALBERTA = "Alberta";
    private final String CENTRAL_AMERICA = "Central America";
    private final String EASTERN_UNITED_STATES = "Eastern United States";
    private final String GREENLAND = "Greenland";
    private final String NORTHWEST_TERRITORY = "Northwest Territory";
    private final String ONTARIO = "Ontario";
    private final String QUEBEC = "Quebec";
    private final String WESTERN_UNITED_STATES = "Western United States";

    //South America
    private final String ARGENTINA = "Argentina";
    private final String BRAZIL = "Brazil";
    private final String PERU = "Peru";
    private final String VENEZUELA = "Venezuela";

    //Europe
    private final String GREAT_BRITAIN = "Great Britain";
    private final String ICELAND = "Iceland";
    private final String NORTHERN_EUROPE = "Northern Europe";
    private final String SCANDINAVIA = "Scandinavia";
    private final String SOUTHERN_EUROPE = "Southern Europe";
    private final String UKRAINE = "Ukraine";
    private final String WESTERN_EUROPE = "Western Europe";

    //Africa
    private final String CONGO = "Congo";
    private final String EAST_AFRICA = "East Africa";
    private final String EGYPT = "Egypt";
    private final String MADAGASCAR = "Madagascar";
    private final String NORTH_AFRICA = "North Africa";
    private final String SOUTH_AFRICA = "South Africa";

    //Asia
    private final String AFGHANISTAN = "Afghanistan";
    private final String CHINA = "China";
    private final String INDIA = "India";
    private final String IRKUTSK = "Irkutsk";
    private final String JAPAN = "Japan";
    private final String KAMCHATKA = "Kamchatka";
    private final String MIDDLE_EAST = "Middle East";
    private final String MONGOLIA = "Mongolia";
    private final String SIAM = "Siam";
    private final String SIBERIA = "Siberia";
    private final String URAL = "Ural";
    private final String YAKUTSK = "Yakutsk";

    //Austraila
    private final String EASTERN_AUSTRALIA = "Eastern Australia";
    private final String INDONESIA = "Indonesia";
    private final String NEW_GUINEA = "New Guinea";
    private final String WESTERN_AUSTRALIA = "Western Australia";

    /**
     * Constructor for the default map of Risk.
     */
    public Map() {
        countries = new LinkedList<>();
        continents = new ArrayList<>();
        this.loadMap();
        this.loadContinents();
        this.setNeighbors();
    }

    /**
     * Creates and adds all the countries of the standard map of Risk.
     */
    public void loadMap() {

        //North America
        countries.add(new Country(ALASKA));
        countries.add(new Country(ALBERTA));
        countries.add(new Country(CENTRAL_AMERICA));
        countries.add(new Country(EASTERN_UNITED_STATES));
        countries.add(new Country(GREENLAND));
        countries.add(new Country(NORTHWEST_TERRITORY));
        countries.add(new Country(ONTARIO));
        countries.add(new Country(QUEBEC));
        countries.add(new Country(WESTERN_UNITED_STATES));

        //South America
        countries.add(new Country(ARGENTINA));
        countries.add(new Country(BRAZIL));
        countries.add(new Country(PERU));
        countries.add(new Country(VENEZUELA));

        //Europe
        countries.add(new Country(GREAT_BRITAIN));
        countries.add(new Country(ICELAND));
        countries.add(new Country(NORTHERN_EUROPE));
        countries.add(new Country(SCANDINAVIA));
        countries.add(new Country(SOUTHERN_EUROPE));
        countries.add(new Country(UKRAINE));
        countries.add(new Country(WESTERN_EUROPE));

        //Africa
        countries.add(new Country(CONGO));
        countries.add(new Country(EAST_AFRICA));
        countries.add(new Country(EGYPT));
        countries.add(new Country(MADAGASCAR));
        countries.add(new Country(NORTH_AFRICA));
        countries.add(new Country(SOUTH_AFRICA));

        //Asia
        countries.add(new Country(AFGHANISTAN));
        countries.add(new Country(CHINA));
        countries.add(new Country(INDIA));
        countries.add(new Country(IRKUTSK));
        countries.add(new Country(JAPAN));
        countries.add(new Country(KAMCHATKA));
        countries.add(new Country(MIDDLE_EAST));
        countries.add(new Country(MONGOLIA));
        countries.add(new Country(SIAM));
        countries.add(new Country(SIBERIA));
        countries.add(new Country(URAL));
        countries.add(new Country(YAKUTSK));

        //Australia
        countries.add(new Country(EASTERN_AUSTRALIA));
        countries.add(new Country(INDONESIA));
        countries.add(new Country(NEW_GUINEA));
        countries.add(new Country(WESTERN_AUSTRALIA));

    }

    /**
     * Adds the neighbor relationships to the different countries in the map.
     */
    public void setNeighbors() {
        //North America
        getCountry(ALASKA).addNeighbor(getCountry(ALBERTA));
        getCountry(ALASKA).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(ALASKA).addNeighbor(getCountry(KAMCHATKA));
        getCountry(ALBERTA).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(ALBERTA).addNeighbor(getCountry(ALASKA));
        getCountry(ALBERTA).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(ALBERTA).addNeighbor(getCountry(ONTARIO));
        getCountry(CENTRAL_AMERICA).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(CENTRAL_AMERICA).addNeighbor(getCountry(EASTERN_UNITED_STATES));
        getCountry(CENTRAL_AMERICA).addNeighbor(getCountry(VENEZUELA));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(ONTARIO));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(QUEBEC));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(CENTRAL_AMERICA));
        getCountry(EASTERN_UNITED_STATES).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(GREENLAND).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(GREENLAND).addNeighbor(getCountry(ONTARIO));
        getCountry(GREENLAND).addNeighbor(getCountry(QUEBEC));
        getCountry(GREENLAND).addNeighbor(getCountry(ICELAND));
        getCountry(NORTHWEST_TERRITORY).addNeighbor(getCountry(ALASKA));
        getCountry(NORTHWEST_TERRITORY).addNeighbor(getCountry(ALBERTA));
        getCountry(NORTHWEST_TERRITORY).addNeighbor(getCountry(ONTARIO));
        getCountry(NORTHWEST_TERRITORY).addNeighbor(getCountry(GREAT_BRITAIN));
        getCountry(ONTARIO).addNeighbor(getCountry(NORTHWEST_TERRITORY));
        getCountry(ONTARIO).addNeighbor(getCountry(ALBERTA));
        getCountry(ONTARIO).addNeighbor(getCountry(WESTERN_UNITED_STATES));
        getCountry(ONTARIO).addNeighbor(getCountry(EASTERN_UNITED_STATES));
        getCountry(ONTARIO).addNeighbor(getCountry(QUEBEC));
        getCountry(ONTARIO).addNeighbor(getCountry(GREENLAND));
        getCountry(QUEBEC).addNeighbor(getCountry(ONTARIO));
        getCountry(QUEBEC).addNeighbor(getCountry(EASTERN_UNITED_STATES));
        getCountry(QUEBEC).addNeighbor(getCountry(GREENLAND));
        getCountry(WESTERN_UNITED_STATES).addNeighbor(getCountry(ALBERTA));
        getCountry(WESTERN_UNITED_STATES).addNeighbor(getCountry(ONTARIO));
        getCountry(WESTERN_UNITED_STATES).addNeighbor(getCountry(EASTERN_UNITED_STATES));
        getCountry(WESTERN_UNITED_STATES).addNeighbor(getCountry(CENTRAL_AMERICA));

        //South America
        getCountry(ARGENTINA).addNeighbor(getCountry(BRAZIL));
        getCountry(ARGENTINA).addNeighbor(getCountry(PERU));
        getCountry(BRAZIL).addNeighbor(getCountry(ARGENTINA));
        getCountry(BRAZIL).addNeighbor(getCountry(PERU));
        getCountry(BRAZIL).addNeighbor(getCountry(VENEZUELA));
        getCountry(BRAZIL).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(PERU).addNeighbor(getCountry(ARGENTINA));
        getCountry(PERU).addNeighbor(getCountry(BRAZIL));
        getCountry(PERU).addNeighbor(getCountry(VENEZUELA));
        getCountry(VENEZUELA).addNeighbor(getCountry(BRAZIL));
        getCountry(VENEZUELA).addNeighbor(getCountry(PERU));
        getCountry(VENEZUELA).addNeighbor(getCountry(CENTRAL_AMERICA));

        //Europe
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(ICELAND));
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(NORTHERN_EUROPE));
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(GREAT_BRITAIN).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(ICELAND).addNeighbor(getCountry(GREENLAND));
        getCountry(ICELAND).addNeighbor(getCountry(GREAT_BRITAIN));
        getCountry(ICELAND).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(GREAT_BRITAIN));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(UKRAINE));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(SOUTHERN_EUROPE));
        getCountry(NORTHERN_EUROPE).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(ICELAND));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(GREAT_BRITAIN));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(NORTHERN_EUROPE));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(UKRAINE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(NORTHERN_EUROPE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(UKRAINE));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(EGYPT));
        getCountry(SOUTHERN_EUROPE).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(UKRAINE).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(UKRAINE).addNeighbor(getCountry(NORTHERN_EUROPE));
        getCountry(UKRAINE).addNeighbor(getCountry(SOUTHERN_EUROPE));
        getCountry(UKRAINE).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(UKRAINE).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(UKRAINE).addNeighbor(getCountry(URAL));
        getCountry(WESTERN_EUROPE).addNeighbor(getCountry(GREAT_BRITAIN));
        getCountry(WESTERN_EUROPE).addNeighbor(getCountry(NORTHERN_EUROPE));
        getCountry(WESTERN_EUROPE).addNeighbor(getCountry(SOUTHERN_EUROPE));
        getCountry(WESTERN_EUROPE).addNeighbor(getCountry(NORTH_AFRICA));

        //Africa
        getCountry(CONGO).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(CONGO).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(CONGO).addNeighbor(getCountry(SOUTH_AFRICA));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(CONGO));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(EGYPT));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(SOUTH_AFRICA));
        getCountry(EAST_AFRICA).addNeighbor(getCountry(MADAGASCAR));
        getCountry(EGYPT).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(EGYPT).addNeighbor(getCountry(NORTH_AFRICA));
        getCountry(EGYPT).addNeighbor(getCountry(SOUTHERN_EUROPE));
        getCountry(MADAGASCAR).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(MADAGASCAR).addNeighbor(getCountry(SOUTH_AFRICA));
        getCountry(NORTH_AFRICA).addNeighbor(getCountry(CONGO));
        getCountry(NORTH_AFRICA).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(NORTH_AFRICA).addNeighbor(getCountry(EGYPT));
        getCountry(NORTH_AFRICA).addNeighbor(getCountry(BRAZIL));
        getCountry(NORTH_AFRICA).addNeighbor(getCountry(SOUTHERN_EUROPE));
        getCountry(NORTH_AFRICA).addNeighbor(getCountry(WESTERN_EUROPE));
        getCountry(SOUTH_AFRICA).addNeighbor(getCountry(CONGO));
        getCountry(SOUTH_AFRICA).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(SOUTH_AFRICA).addNeighbor(getCountry(MADAGASCAR));

        //Asia
        getCountry(AFGHANISTAN).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(INDIA));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(CHINA));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(URAL));
        getCountry(AFGHANISTAN).addNeighbor(getCountry(UKRAINE));
        getCountry(CHINA).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(CHINA).addNeighbor(getCountry(INDIA));
        getCountry(CHINA).addNeighbor(getCountry(SIAM));
        getCountry(CHINA).addNeighbor(getCountry(MONGOLIA));
        getCountry(CHINA).addNeighbor(getCountry(SIBERIA));
        getCountry(CHINA).addNeighbor(getCountry(URAL));
        getCountry(INDIA).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(INDIA).addNeighbor(getCountry(CHINA));
        getCountry(INDIA).addNeighbor(getCountry(MIDDLE_EAST));
        getCountry(INDIA).addNeighbor(getCountry(SIAM));
        getCountry(IRKUTSK).addNeighbor(getCountry(KAMCHATKA));
        getCountry(IRKUTSK).addNeighbor(getCountry(MONGOLIA));
        getCountry(IRKUTSK).addNeighbor(getCountry(SIBERIA));
        getCountry(IRKUTSK).addNeighbor(getCountry(YAKUTSK));
        getCountry(JAPAN).addNeighbor(getCountry(KAMCHATKA));
        getCountry(JAPAN).addNeighbor(getCountry(MONGOLIA));
        getCountry(KAMCHATKA).addNeighbor(getCountry(ALASKA));
        getCountry(KAMCHATKA).addNeighbor(getCountry(IRKUTSK));
        getCountry(KAMCHATKA).addNeighbor(getCountry(JAPAN));
        getCountry(KAMCHATKA).addNeighbor(getCountry(MONGOLIA));
        getCountry(KAMCHATKA).addNeighbor(getCountry(YAKUTSK));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(EAST_AFRICA));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(EGYPT));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(INDIA));
        getCountry(MIDDLE_EAST).addNeighbor(getCountry(UKRAINE));
        getCountry(MONGOLIA).addNeighbor(getCountry(CHINA));
        getCountry(MONGOLIA).addNeighbor(getCountry(SIBERIA));
        getCountry(MONGOLIA).addNeighbor(getCountry(IRKUTSK));
        getCountry(MONGOLIA).addNeighbor(getCountry(KAMCHATKA));
        getCountry(MONGOLIA).addNeighbor(getCountry(JAPAN));
        getCountry(SIAM).addNeighbor(getCountry(CHINA));
        getCountry(SIAM).addNeighbor(getCountry(INDIA));
        getCountry(SIAM).addNeighbor(getCountry(INDONESIA));
        getCountry(SIBERIA).addNeighbor(getCountry(URAL));
        getCountry(SIBERIA).addNeighbor(getCountry(CHINA));
        getCountry(SIBERIA).addNeighbor(getCountry(MONGOLIA));
        getCountry(SIBERIA).addNeighbor(getCountry(IRKUTSK));
        getCountry(SIBERIA).addNeighbor(getCountry(YAKUTSK));
        getCountry(URAL).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(URAL).addNeighbor(getCountry(CHINA));
        getCountry(URAL).addNeighbor(getCountry(SIBERIA));
        getCountry(URAL).addNeighbor(getCountry(UKRAINE));
        getCountry(YAKUTSK).addNeighbor(getCountry(IRKUTSK));
        getCountry(YAKUTSK).addNeighbor(getCountry(KAMCHATKA));
        getCountry(YAKUTSK).addNeighbor(getCountry(SIBERIA));

        //Oceania
        getCountry(EASTERN_AUSTRALIA).addNeighbor(getCountry(NEW_GUINEA));
        getCountry(EASTERN_AUSTRALIA).addNeighbor(getCountry(WESTERN_AUSTRALIA));
        getCountry(INDONESIA).addNeighbor(getCountry(NEW_GUINEA));
        getCountry(INDONESIA).addNeighbor(getCountry(WESTERN_AUSTRALIA));
        getCountry(INDONESIA).addNeighbor(getCountry(SIAM));
        getCountry(NEW_GUINEA).addNeighbor(getCountry(EASTERN_AUSTRALIA));
        getCountry(NEW_GUINEA).addNeighbor(getCountry(WESTERN_AUSTRALIA));
        getCountry(NEW_GUINEA).addNeighbor(getCountry(INDONESIA));
        getCountry(WESTERN_AUSTRALIA).addNeighbor(getCountry(EASTERN_AUSTRALIA));
        getCountry(WESTERN_AUSTRALIA).addNeighbor(getCountry(INDONESIA));
        getCountry(WESTERN_AUSTRALIA).addNeighbor(getCountry(NEW_GUINEA));
    }

    /**
     * Creates and sets the different continents.
     */
    public void loadContinents() {

        //Continents
        String NORTH_AMERICA = "North America";
        String SOUTH_AMERICA = "South America";
        String EUROPE = "Europe";
        String AFRICA = "Africa";
        String ASIA = "Asia";
        String AUSTRALIA = "Australia";

        continents.add(new Continent(NORTH_AMERICA, 5));
        continents.add(new Continent(SOUTH_AMERICA, 2));
        continents.add(new Continent(EUROPE, 5));
        continents.add(new Continent(AFRICA, 3));
        continents.add(new Continent(ASIA, 7));
        continents.add(new Continent(AUSTRALIA, 2));

        //North America
        for (int i = 0; i < 9; i++) {
            getContinent(NORTH_AMERICA).addCountry(countries.get(i));
        }

        //South America
        for (int i = 9; i < 13; i++) {
            getContinent(SOUTH_AMERICA).addCountry(countries.get(i));
        }

        //Europe
        for (int i = 13; i < 20; i++) {
            getContinent(EUROPE).addCountry(countries.get(i));
        }

        //Africa
        for (int i = 20; i < 26; i++) {
            getContinent(AFRICA).addCountry(countries.get(i));
        }

        //Asia
        for (int i = 26; i < 38; i++) {
            getContinent(ASIA).addCountry(countries.get(i));
        }

        //Australia
        for (int i = 38; i < 42; i++) {
            getContinent(AUSTRALIA).addCountry(countries.get(i));
        }

    }


    public void printMap() {
        for (Country c : countries) {
            System.out.println(c);
        }
    }

    public LinkedList<Country> getCountries() {
        return countries;
    }


    public ArrayList<Continent> getContinents(){
        return continents;
    }

    public boolean countryExists (String countryName) {
        for (Country c : countries) {
            if (c.toString().equals(countryName))
                return true;
        }
        return false;
    }

    public Country getCountry(String name){
        for (Country country: countries) {
            if(country.getName().equals(name)){
                return country;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid country");
    }

    public Continent getContinent(String name) {
        for (Continent continent : continents) {
            if(continent.getName().equals(name)){
                return continent;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid continent");
    }

    public void shuffleCountries () {
        for (int i = 0; i < 1000; i++) {
            Collections.swap(countries, ThreadLocalRandom.current().nextInt(0, countries.size()),
                    ThreadLocalRandom.current().nextInt(0, countries.size()));
        }
    }
}
