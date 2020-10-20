import java.util.ArrayList;
import java.util.Collections;
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
    private final ArrayList<Country> countries;
    private final ArrayList<Continent> continents;

    //Countries
    //North America
    private final String ALASKA = "Alaska";
    private final String ALBERTA = "Alberta";
    private final String CENTRALAMERICA = "Central America";
    private final String EASTERNUNITEDSTATES = "Eastern United States";
    private final String GREENLAND = "Greenland";
    private final String NORTHWESTTERRITORY = "Northwest Territory";
    private final String ONTARIO = "Ontario";
    private final String QUEBEC = "Quebec";
    private final String WESTERNUNITEDSTATES = "Western United States";

    //South America
    private final String ARGENTINA = "Argentina";
    private final String BRAZIL = "Brazil";
    private final String PERU = "Peru";
    private final String VENEZUELA = "Venezuela";

    //Europe
    private final String GREATBRITAIN = "Great Britain";
    private final String ICELAND = "Iceland";
    private final String NORTHERNEUROPE = "Northern Europe";
    private final String SCANDINAVIA = "Scandinavia";
    private final String SOUTHERNEUROPE = "Southern Europe";
    private final String UKRAINE = "Ukraine";
    private final String WESTERNEUROPE = "Western Europe";

    //Africa
    private final String CONGO = "Congo";
    private final String EASTAFRICA = "East Africa";
    private final String EGYPT = "Egypt";
    private final String MADAGASCAR = "Madagascar";
    private final String NORTHAFRICA = "North Africa";
    private final String SOUTHAFRICA = "South Africa";

    //Asia
    private final String AFGHANISTAN = "Afghanistan";
    private final String CHINA = "China";
    private final String INDIA = "India";
    private final String IRKUTSK = "Irkutsk";
    private final String JAPAN = "Japan";
    private final String KAMCHATKA = "Kamchatka";
    private final String MIDDLEEAST = "Middle East";
    private final String MONGOLIA = "Mongolia";
    private final String SIAM = "Siam";
    private final String SIBERIA = "Siberia";
    private final String URAL = "Ural";
    private final String YAKUTSK = "Yakutsk";

    //Austraila
    private final String EASTERNAUSTRALIA= "Eastern Australia";
    private final String INDONESIA = "Indonesia";
    private final String NEWGUINEA= "New Guinea";
    private final String WESTERNAUSTRALIA= "Western Australia";


    public Map() {
        countries = new ArrayList<>();
        continents = new ArrayList<>();
        this.loadMap();
        this.loadContinents();
        this.setNeighbors();
    }

    public void loadMap() {

        //North America
        countries.add(new Country(ALASKA));
        countries.add(new Country(ALBERTA));
        countries.add(new Country(CENTRALAMERICA));
        countries.add(new Country(EASTERNUNITEDSTATES));
        countries.add(new Country(GREENLAND));
        countries.add(new Country(NORTHWESTTERRITORY));
        countries.add(new Country(ONTARIO));
        countries.add(new Country(QUEBEC));
        countries.add(new Country(WESTERNUNITEDSTATES));

        //South America
        countries.add(new Country(ARGENTINA));
        countries.add(new Country(BRAZIL));
        countries.add(new Country(PERU));
        countries.add(new Country(VENEZUELA));

        //Europe
        countries.add(new Country(GREATBRITAIN));
        countries.add(new Country(ICELAND));
        countries.add(new Country(NORTHERNEUROPE));
        countries.add(new Country(SCANDINAVIA));
        countries.add(new Country(SOUTHERNEUROPE));
        countries.add(new Country(UKRAINE));
        countries.add(new Country(WESTERNEUROPE));

        //Africa
        countries.add(new Country(CONGO));
        countries.add(new Country(EASTAFRICA));
        countries.add(new Country(EGYPT));
        countries.add(new Country(MADAGASCAR));
        countries.add(new Country(NORTHAFRICA));
        countries.add(new Country(SOUTHAFRICA));

        //Asia
        countries.add(new Country(AFGHANISTAN));
        countries.add(new Country(CHINA));
        countries.add(new Country(INDIA));
        countries.add(new Country(IRKUTSK));
        countries.add(new Country(JAPAN));
        countries.add(new Country(KAMCHATKA));
        countries.add(new Country(MIDDLEEAST));
        countries.add(new Country(MONGOLIA));
        countries.add(new Country(SIAM));
        countries.add(new Country(SIBERIA));
        countries.add(new Country(URAL));
        countries.add(new Country(YAKUTSK));

        //Australia
        countries.add(new Country(EASTERNAUSTRALIA));
        countries.add(new Country(INDONESIA));
        countries.add(new Country(NEWGUINEA));
        countries.add(new Country(WESTERNAUSTRALIA));

    }

    public void setNeighbors() {
        //North America
        getCountry(ALASKA).addNeighbor(getCountry(ALBERTA));
        getCountry(ALASKA).addNeighbor(getCountry(NORTHWESTTERRITORY));
        getCountry(ALASKA).addNeighbor(getCountry(KAMCHATKA));
        getCountry(ALBERTA).addNeighbor(getCountry(WESTERNUNITEDSTATES));
        getCountry(ALBERTA).addNeighbor(getCountry(ALASKA));
        getCountry(ALBERTA).addNeighbor(getCountry(NORTHWESTTERRITORY));
        getCountry(ALBERTA).addNeighbor(getCountry(ONTARIO));
        getCountry(CENTRALAMERICA).addNeighbor(getCountry(WESTERNUNITEDSTATES));
        getCountry(CENTRALAMERICA).addNeighbor(getCountry(EASTERNUNITEDSTATES));
        getCountry(CENTRALAMERICA).addNeighbor(getCountry(VENEZUELA));
        getCountry(EASTERNUNITEDSTATES).addNeighbor(getCountry(ONTARIO));
        getCountry(EASTERNUNITEDSTATES).addNeighbor(getCountry(QUEBEC));
        getCountry(EASTERNUNITEDSTATES).addNeighbor(getCountry(CENTRALAMERICA));
        getCountry(EASTERNUNITEDSTATES).addNeighbor(getCountry(WESTERNUNITEDSTATES));
        getCountry(GREENLAND).addNeighbor(getCountry(NORTHWESTTERRITORY));
        getCountry(GREENLAND).addNeighbor(getCountry(ONTARIO));
        getCountry(GREENLAND).addNeighbor(getCountry(QUEBEC));
        getCountry(GREENLAND).addNeighbor(getCountry(ICELAND));
        getCountry(NORTHWESTTERRITORY).addNeighbor(getCountry(ALASKA));
        getCountry(NORTHWESTTERRITORY).addNeighbor(getCountry(ALBERTA));
        getCountry(NORTHWESTTERRITORY).addNeighbor(getCountry(ONTARIO));
        getCountry(NORTHWESTTERRITORY).addNeighbor(getCountry(GREATBRITAIN));
        getCountry(ONTARIO).addNeighbor(getCountry(NORTHWESTTERRITORY));
        getCountry(ONTARIO).addNeighbor(getCountry(ALBERTA));
        getCountry(ONTARIO).addNeighbor(getCountry(WESTERNUNITEDSTATES));
        getCountry(ONTARIO).addNeighbor(getCountry(EASTERNUNITEDSTATES));
        getCountry(ONTARIO).addNeighbor(getCountry(QUEBEC));
        getCountry(ONTARIO).addNeighbor(getCountry(GREENLAND));
        getCountry(QUEBEC).addNeighbor(getCountry(ONTARIO));
        getCountry(QUEBEC).addNeighbor(getCountry(EASTERNUNITEDSTATES));
        getCountry(QUEBEC).addNeighbor(getCountry(GREENLAND));
        getCountry(WESTERNUNITEDSTATES).addNeighbor(getCountry(ALBERTA));
        getCountry(WESTERNUNITEDSTATES).addNeighbor(getCountry(ONTARIO));
        getCountry(WESTERNUNITEDSTATES).addNeighbor(getCountry(EASTERNUNITEDSTATES));
        getCountry(WESTERNUNITEDSTATES).addNeighbor(getCountry(CENTRALAMERICA));

        //South America
        getCountry(ARGENTINA).addNeighbor(getCountry(BRAZIL));
        getCountry(ARGENTINA).addNeighbor(getCountry(PERU));
        getCountry(BRAZIL).addNeighbor(getCountry(ARGENTINA));
        getCountry(BRAZIL).addNeighbor(getCountry(PERU));
        getCountry(BRAZIL).addNeighbor(getCountry(VENEZUELA));
        getCountry(BRAZIL).addNeighbor(getCountry(NORTHAFRICA));
        getCountry(PERU).addNeighbor(getCountry(ARGENTINA));
        getCountry(PERU).addNeighbor(getCountry(BRAZIL));
        getCountry(PERU).addNeighbor(getCountry(VENEZUELA));
        getCountry(VENEZUELA).addNeighbor(getCountry(BRAZIL));
        getCountry(VENEZUELA).addNeighbor(getCountry(PERU));
        getCountry(VENEZUELA).addNeighbor(getCountry(CENTRALAMERICA));

        //Europe
        getCountry(GREATBRITAIN).addNeighbor(getCountry(ICELAND));
        getCountry(GREATBRITAIN).addNeighbor(getCountry(NORTHERNEUROPE));
        getCountry(GREATBRITAIN).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(GREATBRITAIN).addNeighbor(getCountry(WESTERNEUROPE));
        getCountry(ICELAND).addNeighbor(getCountry(GREENLAND));
        getCountry(ICELAND).addNeighbor(getCountry(GREATBRITAIN));
        getCountry(ICELAND).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(NORTHERNEUROPE).addNeighbor(getCountry(GREATBRITAIN));
        getCountry(NORTHERNEUROPE).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(NORTHERNEUROPE).addNeighbor(getCountry(UKRAINE));
        getCountry(NORTHERNEUROPE).addNeighbor(getCountry(SOUTHERNEUROPE));
        getCountry(NORTHERNEUROPE).addNeighbor(getCountry(WESTERNEUROPE));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(ICELAND));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(GREATBRITAIN));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(NORTHERNEUROPE));
        getCountry(SCANDINAVIA).addNeighbor(getCountry(UKRAINE));
        getCountry(SOUTHERNEUROPE).addNeighbor(getCountry(WESTERNEUROPE));
        getCountry(SOUTHERNEUROPE).addNeighbor(getCountry(NORTHERNEUROPE));
        getCountry(SOUTHERNEUROPE).addNeighbor(getCountry(UKRAINE));
        getCountry(SOUTHERNEUROPE).addNeighbor(getCountry(MIDDLEEAST));
        getCountry(SOUTHERNEUROPE).addNeighbor(getCountry(EGYPT));
        getCountry(SOUTHERNEUROPE).addNeighbor(getCountry(NORTHAFRICA));
        getCountry(UKRAINE).addNeighbor(getCountry(SCANDINAVIA));
        getCountry(UKRAINE).addNeighbor(getCountry(NORTHERNEUROPE));
        getCountry(UKRAINE).addNeighbor(getCountry(SOUTHERNEUROPE));
        getCountry(UKRAINE).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(UKRAINE).addNeighbor(getCountry(MIDDLEEAST));
        getCountry(UKRAINE).addNeighbor(getCountry(URAL));
        getCountry(WESTERNEUROPE).addNeighbor(getCountry(GREATBRITAIN));
        getCountry(WESTERNEUROPE).addNeighbor(getCountry(NORTHERNEUROPE));
        getCountry(WESTERNEUROPE).addNeighbor(getCountry(SOUTHERNEUROPE));
        getCountry(WESTERNEUROPE).addNeighbor(getCountry(NORTHAFRICA));

        //Africa
        getCountry(CONGO).addNeighbor(getCountry(EASTAFRICA));
        getCountry(CONGO).addNeighbor(getCountry(NORTHAFRICA));
        getCountry(CONGO).addNeighbor(getCountry(SOUTHAFRICA));
        getCountry(EASTAFRICA).addNeighbor(getCountry(CONGO));
        getCountry(EASTAFRICA).addNeighbor(getCountry(EGYPT));
        getCountry(EASTAFRICA).addNeighbor(getCountry(NORTHAFRICA));
        getCountry(EASTAFRICA).addNeighbor(getCountry(SOUTHAFRICA));
        getCountry(EASTAFRICA).addNeighbor(getCountry(MADAGASCAR));
        getCountry(EGYPT).addNeighbor(getCountry(EASTAFRICA));
        getCountry(EGYPT).addNeighbor(getCountry(NORTHAFRICA));
        getCountry(EGYPT).addNeighbor(getCountry(SOUTHERNEUROPE));
        getCountry(MADAGASCAR).addNeighbor(getCountry(EASTAFRICA));
        getCountry(MADAGASCAR).addNeighbor(getCountry(SOUTHAFRICA));
        getCountry(NORTHAFRICA).addNeighbor(getCountry(CONGO));
        getCountry(NORTHAFRICA).addNeighbor(getCountry(EASTAFRICA));
        getCountry(NORTHAFRICA).addNeighbor(getCountry(EGYPT));
        getCountry(NORTHAFRICA).addNeighbor(getCountry(BRAZIL));
        getCountry(NORTHAFRICA).addNeighbor(getCountry(SOUTHERNEUROPE));
        getCountry(NORTHAFRICA).addNeighbor(getCountry(WESTERNEUROPE));
        getCountry(SOUTHAFRICA).addNeighbor(getCountry(CONGO));
        getCountry(SOUTHAFRICA).addNeighbor(getCountry(EASTAFRICA));
        getCountry(SOUTHAFRICA).addNeighbor(getCountry(MADAGASCAR));

        //Asia
        getCountry(AFGHANISTAN).addNeighbor(getCountry(MIDDLEEAST));
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
        getCountry(INDIA).addNeighbor(getCountry(MIDDLEEAST));
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
        getCountry(MIDDLEEAST).addNeighbor(getCountry(EASTAFRICA));
        getCountry(MIDDLEEAST).addNeighbor(getCountry(EGYPT));
        getCountry(MIDDLEEAST).addNeighbor(getCountry(AFGHANISTAN));
        getCountry(MIDDLEEAST).addNeighbor(getCountry(INDIA));
        getCountry(MIDDLEEAST).addNeighbor(getCountry(UKRAINE));
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
        getCountry(EASTERNAUSTRALIA).addNeighbor(getCountry(NEWGUINEA));
        getCountry(EASTERNAUSTRALIA).addNeighbor(getCountry(WESTERNAUSTRALIA));
        getCountry(INDONESIA).addNeighbor(getCountry(NEWGUINEA));
        getCountry(INDONESIA).addNeighbor(getCountry(WESTERNAUSTRALIA));
        getCountry(INDONESIA).addNeighbor(getCountry(SIAM));
        getCountry(NEWGUINEA).addNeighbor(getCountry(EASTERNAUSTRALIA));
        getCountry(NEWGUINEA).addNeighbor(getCountry(WESTERNAUSTRALIA));
        getCountry(NEWGUINEA).addNeighbor(getCountry(INDONESIA));
        getCountry(WESTERNAUSTRALIA).addNeighbor(getCountry(EASTERNAUSTRALIA));
        getCountry(WESTERNAUSTRALIA).addNeighbor(getCountry(INDONESIA));
        getCountry(WESTERNAUSTRALIA).addNeighbor(getCountry(NEWGUINEA));
    }

    public void loadContinents() {

        //Continents
        String NORTHAMERICA = "North America";
        String SOUTHAMERICA = "South America";
        String EUROPE = "Europe";
        String AFRICA = "Africa";
        String ASIA = "Asia";
        String AUSTRALIA = "Australia";

        continents.add(new Continent(NORTHAMERICA, 5));
        continents.add(new Continent(SOUTHAMERICA, 2));
        continents.add(new Continent(EUROPE, 5));
        continents.add(new Continent(AFRICA, 3));
        continents.add(new Continent(ASIA, 7));
        continents.add(new Continent(AUSTRALIA, 2));

        //North America
        for (int i = 0; i < 9; i++) {
            getContinent(NORTHAMERICA).addCountry(countries.get(i));
        }

        //South America
        for (int i = 9; i < 13; i++) {
            getContinent(SOUTHAMERICA).addCountry(countries.get(i));
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

    public void removeCountry(Country country) {
        countries.remove(country);
    }

    public void printMap() {
        for (Country c : countries) {
            System.out.println(c.toString());
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public ArrayList<Continent> getContinents() {
        return continents;
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
            Collections.swap(countries, ThreadLocalRandom.current().nextInt(0, countries.size()), ThreadLocalRandom.current().nextInt(0, countries.size()));
        }
    }
}
