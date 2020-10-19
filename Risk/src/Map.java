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

    public Map() {
        countries = new ArrayList<>();
        continents = new ArrayList<>();
        this.loadMap();
        this.loadContinents();
        this.setNeighbors();
    }

    public void loadMap() {

        //North America
        countries.add(new Country("Alaska"));
        countries.add(new Country("Alberta"));
        countries.add(new Country("Central America"));
        countries.add(new Country("Eastern United States"));
        countries.add(new Country("Greenland"));
        countries.add(new Country("Northwest Territory"));
        countries.add(new Country("Ontario"));
        countries.add(new Country("Quebec"));
        countries.add(new Country("Western United States"));

        //South America
        countries.add(new Country("Argentina"));
        countries.add(new Country("Brazil"));
        countries.add(new Country("Peru"));
        countries.add(new Country("Venezuela"));

        //Europe
        countries.add(new Country("Great Britain"));
        countries.add(new Country("Iceland"));
        countries.add(new Country("Northern Europe"));
        countries.add(new Country("Scandinavia"));
        countries.add(new Country("Southern Europe"));
        countries.add(new Country("Ukraine"));
        countries.add(new Country("Western Europe"));

        //Africa
        countries.add(new Country("Congo"));
        countries.add(new Country("East Africa"));
        countries.add(new Country("Egypt"));
        countries.add(new Country("Madagascar"));
        countries.add(new Country("North Africa"));
        countries.add(new Country("South Africa"));

        //Asia
        countries.add(new Country("Afghanistan"));
        countries.add(new Country("China"));
        countries.add(new Country("India"));
        countries.add(new Country("Irkutsk"));
        countries.add(new Country("Japan"));
        countries.add(new Country("Kamchatka"));
        countries.add(new Country("Middle East"));
        countries.add(new Country("Mongolia"));
        countries.add(new Country("Siam"));
        countries.add(new Country("Siberia"));
        countries.add(new Country("Ural"));
        countries.add(new Country("Yakutsk"));

        //Australia
        countries.add(new Country("Eastern Australia"));
        countries.add(new Country("Indonesia"));
        countries.add(new Country("New Guinea"));
        countries.add(new Country("Western Australia"));

    }

    public void setNeighbors() {
        //North America
        getCountry("Alaska").addNeighbor(getCountry("Alberta"));
        getCountry("Alaska").addNeighbor(getCountry("Northwest Territory"));
        getCountry("Alaska").addNeighbor(getCountry("Kamchatka"));
        getCountry("Alberta").addNeighbor(getCountry("Western United States"));
        getCountry("Alberta").addNeighbor(getCountry("Alaska"));
        getCountry("Alberta").addNeighbor(getCountry("Northwest Territory"));
        getCountry("Alberta").addNeighbor(getCountry("Ontario"));
        getCountry("Central America").addNeighbor(getCountry("Western United States"));
        getCountry("Central America").addNeighbor(getCountry("Eastern United States"));
        getCountry("Central America").addNeighbor(getCountry("Venezuela"));
        getCountry("Eastern United States").addNeighbor(getCountry("Ontario"));
        getCountry("Eastern United States").addNeighbor(getCountry("Quebec"));
        getCountry("Eastern United States").addNeighbor(getCountry("Central America"));
        getCountry("Eastern United States").addNeighbor(getCountry("Western United States"));
        getCountry("Greenland").addNeighbor(getCountry("Northwest Territory"));
        getCountry("Greenland").addNeighbor(getCountry("Ontario"));
        getCountry("Greenland").addNeighbor(getCountry("Quebec"));
        getCountry("Greenland").addNeighbor(getCountry("Iceland"));
        getCountry("Northwest Territory").addNeighbor(getCountry("Alaska"));
        getCountry("Northwest Territory").addNeighbor(getCountry("Alberta"));
        getCountry("Northwest Territory").addNeighbor(getCountry("Ontario"));
        getCountry("Northwest Territory").addNeighbor(getCountry("Great Britain"));
        getCountry("Ontario").addNeighbor(getCountry("Northwest Territory"));
        getCountry("Ontario").addNeighbor(getCountry("Alberta"));
        getCountry("Ontario").addNeighbor(getCountry("Western United States"));
        getCountry("Ontario").addNeighbor(getCountry("Eastern United States"));
        getCountry("Ontario").addNeighbor(getCountry("Quebec"));
        getCountry("Ontario").addNeighbor(getCountry("Greenland"));
        getCountry("Quebec").addNeighbor(getCountry("Ontario"));
        getCountry("Quebec").addNeighbor(getCountry("Eastern United States"));
        getCountry("Quebec").addNeighbor(getCountry("Greenland"));
        getCountry("Western United States").addNeighbor(getCountry("Alberta"));
        getCountry("Western United States").addNeighbor(getCountry("Ontario"));
        getCountry("Western United States").addNeighbor(getCountry("Eastern United States"));
        getCountry("Western United States").addNeighbor(getCountry("Central America"));

        //South America
        getCountry("Argentina").addNeighbor(getCountry("Brazil"));
        getCountry("Argentina").addNeighbor(getCountry("Peru"));
        getCountry("Brazil").addNeighbor(getCountry("Argentina"));
        getCountry("Brazil").addNeighbor(getCountry("Peru"));
        getCountry("Brazil").addNeighbor(getCountry("Venezuela"));
        getCountry("Brazil").addNeighbor(getCountry("North Africa"));
        getCountry("Peru").addNeighbor(getCountry("Argentina"));
        getCountry("Peru").addNeighbor(getCountry("Brazil"));
        getCountry("Peru").addNeighbor(getCountry("Venezuela"));
        getCountry("Venezuela").addNeighbor(getCountry("Brazil"));
        getCountry("Venezuela").addNeighbor(getCountry("Peru"));
        getCountry("Venezuela").addNeighbor(getCountry("Central America"));

        //Europe
        getCountry("Great Britain").addNeighbor(getCountry("Iceland"));
        getCountry("Great Britain").addNeighbor(getCountry("Northern Europe"));
        getCountry("Great Britain").addNeighbor(getCountry("Scandinavia"));
        getCountry("Great Britain").addNeighbor(getCountry("Western Europe"));
        getCountry("Iceland").addNeighbor(getCountry("Greenland"));
        getCountry("Iceland").addNeighbor(getCountry("Great Britain"));
        getCountry("Iceland").addNeighbor(getCountry("Scandinavia"));
        getCountry("Northern Europe").addNeighbor(getCountry("Great Britain"));
        getCountry("Northern Europe").addNeighbor(getCountry("Scandinavia"));
        getCountry("Northern Europe").addNeighbor(getCountry("Ukraine"));
        getCountry("Northern Europe").addNeighbor(getCountry("Southern Europe"));
        getCountry("Northern Europe").addNeighbor(getCountry("Western Europe"));
        getCountry("Scandinavia").addNeighbor(getCountry("Iceland"));
        getCountry("Scandinavia").addNeighbor(getCountry("Great Britain"));
        getCountry("Scandinavia").addNeighbor(getCountry("Northern Europe"));
        getCountry("Scandinavia").addNeighbor(getCountry("Ukraine"));
        getCountry("Southern Europe").addNeighbor(getCountry("Western Europe"));
        getCountry("Southern Europe").addNeighbor(getCountry("Northern Europe"));
        getCountry("Southern Europe").addNeighbor(getCountry("Ukraine"));
        getCountry("Southern Europe").addNeighbor(getCountry("Middle East"));
        getCountry("Southern Europe").addNeighbor(getCountry("Egypt"));
        getCountry("Southern Europe").addNeighbor(getCountry("North Africa"));
        getCountry("Ukraine").addNeighbor(getCountry("Scandinavia"));
        getCountry("Ukraine").addNeighbor(getCountry("Northern Europe"));
        getCountry("Ukraine").addNeighbor(getCountry("Southern Europe"));
        getCountry("Ukraine").addNeighbor(getCountry("Afghanistan"));
        getCountry("Ukraine").addNeighbor(getCountry("Middle East"));
        getCountry("Ukraine").addNeighbor(getCountry("Ural"));
        getCountry("Western Europe").addNeighbor(getCountry("Great Britain"));
        getCountry("Western Europe").addNeighbor(getCountry("Northern Europe"));
        getCountry("Western Europe").addNeighbor(getCountry("Southern Europe"));
        getCountry("Western Europe").addNeighbor(getCountry("North Africa"));

        //Africa
        getCountry("Congo").addNeighbor(getCountry("East Africa"));
        getCountry("Congo").addNeighbor(getCountry("North Africa"));
        getCountry("Congo").addNeighbor(getCountry("South Africa"));
        getCountry("East Africa").addNeighbor(getCountry("Congo"));
        getCountry("East Africa").addNeighbor(getCountry("Egypt"));
        getCountry("East Africa").addNeighbor(getCountry("North Africa"));
        getCountry("East Africa").addNeighbor(getCountry("South Africa"));
        getCountry("East Africa").addNeighbor(getCountry("Madagascar"));
        getCountry("Egypt").addNeighbor(getCountry("East Africa"));
        getCountry("Egypt").addNeighbor(getCountry("North Africa"));
        getCountry("Egypt").addNeighbor(getCountry("Southern Europe"));
        getCountry("Madagascar").addNeighbor(getCountry("East Africa"));
        getCountry("Madagascar").addNeighbor(getCountry("South Africa"));
        getCountry("North Africa").addNeighbor(getCountry("Congo"));
        getCountry("North Africa").addNeighbor(getCountry("East Africa"));
        getCountry("North Africa").addNeighbor(getCountry("Egypt"));
        getCountry("North Africa").addNeighbor(getCountry("Brazil"));
        getCountry("North Africa").addNeighbor(getCountry("Southern Europe"));
        getCountry("North Africa").addNeighbor(getCountry("Western Europe"));
        getCountry("South Africa").addNeighbor(getCountry("Congo"));
        getCountry("South Africa").addNeighbor(getCountry("East Africa"));
        getCountry("South Africa").addNeighbor(getCountry("Madagascar"));

        //Asia
        getCountry("Afghanistan").addNeighbor(getCountry("Middle East"));
        getCountry("Afghanistan").addNeighbor(getCountry("India"));
        getCountry("Afghanistan").addNeighbor(getCountry("China"));
        getCountry("Afghanistan").addNeighbor(getCountry("Ural"));
        getCountry("Afghanistan").addNeighbor(getCountry("Ukraine"));
        getCountry("China").addNeighbor(getCountry("Afghanistan"));
        getCountry("China").addNeighbor(getCountry("India"));
        getCountry("China").addNeighbor(getCountry("Siam"));
        getCountry("China").addNeighbor(getCountry("Mongolia"));
        getCountry("China").addNeighbor(getCountry("Siberia"));
        getCountry("China").addNeighbor(getCountry("Ural"));
        getCountry("India").addNeighbor(getCountry("Afghanistan"));
        getCountry("India").addNeighbor(getCountry("China"));
        getCountry("India").addNeighbor(getCountry("Middle East"));
        getCountry("India").addNeighbor(getCountry("Siam"));
        getCountry("Irkutsk").addNeighbor(getCountry("Kamchatka"));
        getCountry("Irkutsk").addNeighbor(getCountry("Mongolia"));
        getCountry("Irkutsk").addNeighbor(getCountry("Siberia"));
        getCountry("Irkutsk").addNeighbor(getCountry("Yakutsk"));
        getCountry("Japan").addNeighbor(getCountry("Kamchatka"));
        getCountry("Japan").addNeighbor(getCountry("Mongolia"));
        getCountry("Kamchatka").addNeighbor(getCountry("Irkutsk"));
        getCountry("Kamchatka").addNeighbor(getCountry("Japan"));
        getCountry("Kamchatka").addNeighbor(getCountry("Mongolia"));
        getCountry("Kamchatka").addNeighbor(getCountry("Yakutsk"));
        getCountry("Middle East").addNeighbor(getCountry("East Africa"));
        getCountry("Middle East").addNeighbor(getCountry("Egypt"));
        getCountry("Middle East").addNeighbor(getCountry("Afghanistan"));
        getCountry("Middle East").addNeighbor(getCountry("India"));
        getCountry("Middle East").addNeighbor(getCountry("Ukraine"));
        getCountry("Mongolia").addNeighbor(getCountry("China"));
        getCountry("Mongolia").addNeighbor(getCountry("Siberia"));
        getCountry("Mongolia").addNeighbor(getCountry("Irkutsk"));
        getCountry("Mongolia").addNeighbor(getCountry("Kamchatka"));
        getCountry("Mongolia").addNeighbor(getCountry("Japan"));
        getCountry("Siam").addNeighbor(getCountry("China"));
        getCountry("Siam").addNeighbor(getCountry("India"));
        getCountry("Siam").addNeighbor(getCountry("Indonesia"));
        getCountry("Siberia").addNeighbor(getCountry("Ural"));
        getCountry("Siberia").addNeighbor(getCountry("China"));
        getCountry("Siberia").addNeighbor(getCountry("Mongolia"));
        getCountry("Siberia").addNeighbor(getCountry("Irkutsk"));
        getCountry("Siberia").addNeighbor(getCountry("Yakutsk"));
        getCountry("Ural").addNeighbor(getCountry("Afghanistan"));
        getCountry("Ural").addNeighbor(getCountry("China"));
        getCountry("Ural").addNeighbor(getCountry("Siberia"));
        getCountry("Ural").addNeighbor(getCountry("Ukraine"));
        getCountry("Yakutsk").addNeighbor(getCountry("Irkutsk"));
        getCountry("Yakutsk").addNeighbor(getCountry("Kamchatka"));
        getCountry("Yakutsk").addNeighbor(getCountry("Siberia"));

        //Oceania
        getCountry("Eastern Australia").addNeighbor(getCountry("New Guinea"));
        getCountry("Eastern Australia").addNeighbor(getCountry("Western Australia"));
        getCountry("Indonesia").addNeighbor(getCountry("New Guinea"));
        getCountry("Indonesia").addNeighbor(getCountry("Western Australia"));
        getCountry("Indonesia").addNeighbor(getCountry("Siam"));
        getCountry("New Guinea").addNeighbor(getCountry("Eastern Australia"));
        getCountry("New Guinea").addNeighbor(getCountry("Western Australia"));
        getCountry("New Guinea").addNeighbor(getCountry("Indonesia"));
        getCountry("Western Australia").addNeighbor(getCountry("Eastern Australia"));
        getCountry("Western Australia").addNeighbor(getCountry("Indonesia"));
        getCountry("Western Australia").addNeighbor(getCountry("New Guinea"));
    }

    public void loadContinents() {
        //North America
        Continent northAmerica = new Continent("North America", 5);
        Continent southAmerica = new Continent("South America", 2);
        Continent europe = new Continent("Europe", 5);
        Continent africa = new Continent("Africa", 3);
        Continent asia = new Continent("Asia", 7);
        Continent australia = new Continent("Australia", 2);

        continents.add(northAmerica);
        continents.add(southAmerica);
        continents.add(europe);
        continents.add(africa);
        continents.add(asia);
        continents.add(australia);

        for (int i = 0; i < 9; i++) {
            northAmerica.addCountry(countries.get(i));
        }

        //South America
        for (int i = 9; i < 13; i++) {
            southAmerica.addCountry(countries.get(i));
        }

        //Europe
        for (int i = 13; i < 20; i++) {
            europe.addCountry(countries.get(i));
        }

        //Africa
        for (int i = 20; i < 26; i++) {
            africa.addCountry(countries.get(i));
        }

        //Asia
        for (int i = 26; i < 38; i++) {
            asia.addCountry(countries.get(i));
        }

        //Australia
        for (int i = 38; i < 42; i++) {
            australia.addCountry(countries.get(i));
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

    public Country getCountry(String name){
        for (Country country: countries) {
            if(country.getName().equals(name)){
                return country;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid country");
    }

    public Continent getContinent(Continent continent) {
        return continent;
    }

    public void shuffleCountries () {
        for (int i = 0; i < 1000; i++) {
            Collections.swap(countries, ThreadLocalRandom.current().nextInt(0, countries.size()), ThreadLocalRandom.current().nextInt(0, countries.size()));
        }
    }
}
