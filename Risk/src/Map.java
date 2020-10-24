import java.util.ArrayList;

import java.util.HashMap;


/**
 * Map class
 * Map creates a new map, and loads it with the countries and sets each country's neighbors
 * Map creates continents
 * @author Baillie Noell, Sarah Abdallah - Team Group
 * @version 4: Oct 19 2020
 *
 */

public class Map {
    private final HashMap<String,Country> countries;
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


    public Map() {
        countries = new HashMap<>();
        continents = new ArrayList<>();
        this.loadMap();
        this.loadContinents();
        this.setNeighbors();
    }

    public void loadMap() {

        //North America
        countries.put(ALASKA, new Country(ALASKA));
        countries.put(ALBERTA, new Country(ALBERTA));
        countries.put(CENTRAL_AMERICA, new Country(CENTRAL_AMERICA));
        countries.put(EASTERN_UNITED_STATES, new Country(EASTERN_UNITED_STATES));
        countries.put(GREENLAND, new Country(GREENLAND));
        countries.put(NORTHWEST_TERRITORY, new Country(NORTHWEST_TERRITORY));
        countries.put(ONTARIO, new Country(ONTARIO));
        countries.put(QUEBEC ,new Country(QUEBEC));
        countries.put(WESTERN_UNITED_STATES ,new Country(WESTERN_UNITED_STATES));

        //South America
        countries.put(ARGENTINA ,new Country(ARGENTINA));
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
        countries.put(EGYPT,new Country(EGYPT));
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

    public void loadContinents() {

        //Continents
        String NORTH_AMERICA = "North America";
        String[] NORTH_AMERICA_COUNTRIES = {ALASKA,ALBERTA,CENTRAL_AMERICA,EASTERN_UNITED_STATES,
                GREENLAND,NORTHWEST_TERRITORY,ONTARIO,QUEBEC,WESTERN_UNITED_STATES};

        String SOUTH_AMERICA = "South America";
        String[] SOUTH_AMERICA_COUNTRIES = {ARGENTINA,BRAZIL,PERU,VENEZUELA};

        String EUROPE = "Europe";
        String[] EUROPE_COUNTRIES ={GREAT_BRITAIN,ICELAND,NORTHERN_EUROPE,SCANDINAVIA,SOUTHERN_EUROPE,UKRAINE,WESTERN_EUROPE};

        String AFRICA = "Africa";
        String[] AFRICA_COUNTRIES = {CONGO,EAST_AFRICA,EGYPT,MADAGASCAR,NORTH_AFRICA,SOUTH_AFRICA};

        String ASIA = "Asia";
        String[] ASIA_COUNTRIES ={AFGHANISTAN,CHINA,INDIA,IRKUTSK,JAPAN,KAMCHATKA,MIDDLE_EAST,MONGOLIA,SIAM,SIBERIA,URAL,YAKUTSK};

        String AUSTRALIA = "Australia";
        String[] AUSTRALIA_COUNTRIES ={EASTERN_AUSTRALIA,INDONESIA,NEW_GUINEA,WESTERN_AUSTRALIA};


        continents.add(new Continent(NORTH_AMERICA, 5));
        continents.add(new Continent(SOUTH_AMERICA, 2));
        continents.add(new Continent(EUROPE, 5));
        continents.add(new Continent(AFRICA, 3));
        continents.add(new Continent(ASIA, 7));
        continents.add(new Continent(AUSTRALIA, 2));

        //North America
        getContinent(NORTH_AMERICA).addCountries(getCountries(NORTH_AMERICA_COUNTRIES));
        getContinent(SOUTH_AMERICA).addCountries(getCountries(SOUTH_AMERICA_COUNTRIES));
        getContinent(EUROPE).addCountries(getCountries(EUROPE_COUNTRIES));
        getContinent(AFRICA).addCountries(getCountries(AFRICA_COUNTRIES));
        getContinent(ASIA).addCountries(getCountries(ASIA_COUNTRIES));
        getContinent(AUSTRALIA).addCountries(getCountries(AUSTRALIA_COUNTRIES));
    }

    public void removeCountry(Country country) {
        countries.remove(country);
    }

    public void printMap() {
        countries.forEach((k, v) -> {v.print();});
    }

//    public LinkedList<Country> getCountries() {
//        return countries;
//    }


    public ArrayList<Continent> getContinents(){
        return continents;
    }

//    public boolean countryExists (String countryName) {
//        for (Country c : countries) {
//            if (c.toString().equals(countryName))
//                return true;
//        }
//        return false;
//    }

    public Country getCountry(String name){
        Country country =countries.get(name);
        if (country!=null) return country;
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

    private ArrayList<Country> getCountries(String[] toGet){
        ArrayList<Country> found = new ArrayList<>();
        for(String countryString :toGet){
            found.add(countries.get(countryString));
        }
        return found;
    }

//    public void shuffleCountries () {
//        for (int i = 0; i < 1000; i++) {
//            Collections.swap(countries, ThreadLocalRandom.current().nextInt(0, countries.size()),
//                    ThreadLocalRandom.current().nextInt(0, countries.size()));
//        }
//    }
}
