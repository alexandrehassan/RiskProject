import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Map class
 * Map creates a new map, and loads it with the countries and sets each country's neighbors
 * Map creates continents
 * @author Baillie Noell, Sarah Abdallah - Team Group
 * @version 3: Oct 18 2020
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

    public void setNeighbors() {
        //North America
        setAsNeighbours(getCountry("Alaska"), getCountry("Alberta"));
        setAsNeighbours(getCountry("Alaska"), getCountry("Northwest Territory"));
        setAsNeighbours(getCountry("Alaska"), getCountry("Kamchatka"));
        setAsNeighbours(getCountry("Alberta"), getCountry("Western United States"));
        setAsNeighbours(getCountry("Alberta"), getCountry("Alaska"));
        setAsNeighbours(getCountry("Alberta"), getCountry("Northwest Territory"));
        setAsNeighbours(getCountry("Alberta"), getCountry("Ontario"));
        setAsNeighbours(getCountry("Central America"), getCountry("Western United States"));
        setAsNeighbours(getCountry("Central America"), getCountry("Eastern United States"));
        setAsNeighbours(getCountry("Central America"), getCountry("Venezuela"));
        setAsNeighbours(getCountry("Eastern United States"), getCountry("Ontario"));
        setAsNeighbours(getCountry("Eastern United States"), getCountry("Quebec"));
        setAsNeighbours(getCountry("Eastern United States"), getCountry("Central America"));
        setAsNeighbours(getCountry("Eastern United States"), getCountry("Western United States"));
        setAsNeighbours(getCountry("Greenland"), getCountry("Northwest Territory"));
        setAsNeighbours(getCountry("Greenland"), getCountry("Ontario"));
        setAsNeighbours(getCountry("Greenland"), getCountry("Quebec"));
        setAsNeighbours(getCountry("Greenland"), getCountry("Iceland"));
        setAsNeighbours(getCountry("Northwest Territory"), getCountry("Alaska"));
        setAsNeighbours(getCountry("Northwest Territory"), getCountry("Alberta"));
        setAsNeighbours(getCountry("Northwest Territory"), getCountry("Ontario"));
        setAsNeighbours(getCountry("Northwest Territory"), getCountry("Great Britain"));
        setAsNeighbours(getCountry("Ontario"), getCountry("Northwest Territory"));
        setAsNeighbours(getCountry("Ontario"), getCountry("Alberta"));
        setAsNeighbours(getCountry("Ontario"), getCountry("Western United States"));
        setAsNeighbours(getCountry("Ontario"), getCountry("Eastern United States"));
        setAsNeighbours(getCountry("Ontario"), getCountry("Quebec"));
        setAsNeighbours(getCountry("Ontario"), getCountry("Greenland"));
        setAsNeighbours(getCountry("Quebec"), getCountry("Ontario"));
        setAsNeighbours(getCountry("Quebec"), getCountry("Eastern United States"));
        setAsNeighbours(getCountry("Quebec"), getCountry("Greenland"));
        setAsNeighbours(getCountry("Western United States"), getCountry("Alberta"));
        setAsNeighbours(getCountry("Western United States"), getCountry("Ontario"));
        setAsNeighbours(getCountry("Western United States"), getCountry("Eastern United States"));
        setAsNeighbours(getCountry("Western United States"), getCountry("Central America"));

        //South America
        setAsNeighbours(getCountry("Argentina"), getCountry("Brazil"));
        setAsNeighbours(getCountry("Argentina"), getCountry("Peru"));
        setAsNeighbours(getCountry("Brazil"), getCountry("Argentina"));
        setAsNeighbours(getCountry("Brazil"), getCountry("Peru"));
        setAsNeighbours(getCountry("Brazil"), getCountry("Venezuela"));
        setAsNeighbours(getCountry("Brazil"), getCountry("North Africa"));
        setAsNeighbours(getCountry("Peru"), getCountry("Argentina"));
        setAsNeighbours(getCountry("Peru"), getCountry("Brazil"));
        setAsNeighbours(getCountry("Peru"), getCountry("Venezuela"));
        setAsNeighbours(getCountry("Venezuela"), getCountry("Brazil"));
        setAsNeighbours(getCountry("Venezuela"), getCountry("Peru"));
        setAsNeighbours(getCountry("Venezuela"), getCountry("Central America"));

        //Europe
        setAsNeighbours(getCountry("Great Britain"), getCountry("Iceland"));
        setAsNeighbours(getCountry("Great Britain"), getCountry("Northern Europe"));
        setAsNeighbours(getCountry("Great Britain"), getCountry("Scandinavia"));
        setAsNeighbours(getCountry("Great Britain"), getCountry("Western Europe"));
        setAsNeighbours(getCountry("Iceland"), getCountry("Greenland"));
        setAsNeighbours(getCountry("Iceland"), getCountry("Great Britain"));
        setAsNeighbours(getCountry("Iceland"), getCountry("Scandinavia"));
        setAsNeighbours(getCountry("Northern Europe"), getCountry("Great Britain"));
        setAsNeighbours(getCountry("Northern Europe"), getCountry("Scandinavia"));
        setAsNeighbours(getCountry("Northern Europe"), getCountry("Ukraine"));
        setAsNeighbours(getCountry("Northern Europe"), getCountry("Southern Europe"));
        setAsNeighbours(getCountry("Northern Europe"), getCountry("Western Europe"));
        setAsNeighbours(getCountry("Scandinavia"), getCountry("Iceland"));
        setAsNeighbours(getCountry("Scandinavia"), getCountry("Great Britain"));
        setAsNeighbours(getCountry("Scandinavia"), getCountry("Northern Europe"));
        setAsNeighbours(getCountry("Scandinavia"), getCountry("Ukraine"));
        setAsNeighbours(getCountry("Southern Europe"), getCountry("Western Europe"));
        setAsNeighbours(getCountry("Southern Europe"), getCountry("Northern Europe"));
        setAsNeighbours(getCountry("Southern Europe"), getCountry("Ukraine"));
        setAsNeighbours(getCountry("Southern Europe"), getCountry("Middle East"));
        setAsNeighbours(getCountry("Southern Europe"), getCountry("Egypt"));
        setAsNeighbours(getCountry("Southern Europe"), getCountry("North Africa"));
        setAsNeighbours(getCountry("Ukraine"), getCountry("Scandinavia"));
        setAsNeighbours(getCountry("Ukraine"), getCountry("Northern Europe"));
        setAsNeighbours(getCountry("Ukraine"), getCountry("Southern Europe"));
        setAsNeighbours(getCountry("Ukraine"), getCountry("Afghanistan"));
        setAsNeighbours(getCountry("Ukraine"), getCountry("Middle East"));
        setAsNeighbours(getCountry("Ukraine"), getCountry("Ural"));
        setAsNeighbours(getCountry("Western Europe"), getCountry("Great Britain"));
        setAsNeighbours(getCountry("Western Europe"), getCountry("Northern Europe"));
        setAsNeighbours(getCountry("Western Europe"), getCountry("Southern Europe"));
        setAsNeighbours(getCountry("Western Europe"), getCountry("North Africa"));

        //Africa
        setAsNeighbours(getCountry("Congo"), getCountry("East Africa"));
        setAsNeighbours(getCountry("Congo"), getCountry("North Africa"));
        setAsNeighbours(getCountry("Congo"), getCountry("South Africa"));
        setAsNeighbours(getCountry("East Africa"), getCountry("Congo"));
        setAsNeighbours(getCountry("East Africa"), getCountry("Egypt"));
        setAsNeighbours(getCountry("East Africa"), getCountry("North Africa"));
        setAsNeighbours(getCountry("East Africa"), getCountry("South Africa"));
        setAsNeighbours(getCountry("East Africa"), getCountry("Madagascar"));
        setAsNeighbours(getCountry("Egypt"), getCountry("East Africa"));
        setAsNeighbours(getCountry("Egypt"), getCountry("North Africa"));
        setAsNeighbours(getCountry("Egypt"), getCountry("Southern Europe"));
        setAsNeighbours(getCountry("Madagascar"), getCountry("East Africa"));
        setAsNeighbours(getCountry("Madagascar"), getCountry("South Africa"));
        setAsNeighbours(getCountry("North Africa"), getCountry("Congo"));
        setAsNeighbours(getCountry("North Africa"), getCountry("East Africa"));
        setAsNeighbours(getCountry("North Africa"), getCountry("Egypt"));
        setAsNeighbours(getCountry("North Africa"), getCountry("Brazil"));
        setAsNeighbours(getCountry("North Africa"), getCountry("Southern Europe"));
        setAsNeighbours(getCountry("North Africa"), getCountry("Western Europe"));
        setAsNeighbours(getCountry("South Africa"), getCountry("Congo"));
        setAsNeighbours(getCountry("South Africa"), getCountry("East Africa"));
        setAsNeighbours(getCountry("South Africa"), getCountry("Madagascar"));

        //Asia
        setAsNeighbours(getCountry("Afghanistan"), getCountry("Middle East"));
        setAsNeighbours(getCountry("Afghanistan"), getCountry("India"));
        setAsNeighbours(getCountry("Afghanistan"), getCountry("China"));
        setAsNeighbours(getCountry("Afghanistan"), getCountry("Ural"));
        setAsNeighbours(getCountry("Afghanistan"), getCountry("Ukraine"));
        setAsNeighbours(getCountry("China"), getCountry("Afghanistan"));
        setAsNeighbours(getCountry("China"), getCountry("India"));
        setAsNeighbours(getCountry("China"), getCountry("Siam"));
        setAsNeighbours(getCountry("China"), getCountry("Mongolia"));
        setAsNeighbours(getCountry("China"), getCountry("Siberia"));
        setAsNeighbours(getCountry("China"), getCountry("Ural"));
        setAsNeighbours(getCountry("India"), getCountry("Afghanistan"));
        setAsNeighbours(getCountry("India"), getCountry("China"));
        setAsNeighbours(getCountry("India"), getCountry("Middle East"));
        setAsNeighbours(getCountry("India"), getCountry("Siam"));
        setAsNeighbours(getCountry("Irkutsk"), getCountry("Kamchatka"));
        setAsNeighbours(getCountry("Irkutsk"), getCountry("Mongolia"));
        setAsNeighbours(getCountry("Irkutsk"), getCountry("Siberia"));
        setAsNeighbours(getCountry("Irkutsk"), getCountry("Yakutsk"));
        setAsNeighbours(getCountry("Japan"), getCountry("Kamchatka"));
        setAsNeighbours(getCountry("Japan"), getCountry("Mongolia"));
        setAsNeighbours(getCountry("Kamchatka"), getCountry("Irkutsk"));
        setAsNeighbours(getCountry("Kamchatka"), getCountry("Japan"));
        setAsNeighbours(getCountry("Kamchatka"), getCountry("Mongolia"));
        setAsNeighbours(getCountry("Kamchatka"), getCountry("Yakutsk"));
        setAsNeighbours(getCountry("Middle East"), getCountry("East Africa"));
        setAsNeighbours(getCountry("Middle East"), getCountry("Egypt"));
        setAsNeighbours(getCountry("Middle East"), getCountry("Afghanistan"));
        setAsNeighbours(getCountry("Middle East"), getCountry("India"));
        setAsNeighbours(getCountry("Middle East"), getCountry("Ukraine"));
        setAsNeighbours(getCountry("Mongolia"), getCountry("China"));
        setAsNeighbours(getCountry("Mongolia"), getCountry("Siberia"));
        setAsNeighbours(getCountry("Mongolia"), getCountry("Irkutsk"));
        setAsNeighbours(getCountry("Mongolia"), getCountry("Kamchatka"));
        setAsNeighbours(getCountry("Mongolia"), getCountry("Japan"));
        setAsNeighbours(getCountry("Siam"), getCountry("China"));
        setAsNeighbours(getCountry("Siam"), getCountry("India"));
        setAsNeighbours(getCountry("Siam"), getCountry("Indonesia"));
        setAsNeighbours(getCountry("Siberia"), getCountry("Ural"));
        setAsNeighbours(getCountry("Siberia"), getCountry("China"));
        setAsNeighbours(getCountry("Siberia"), getCountry("Mongolia"));
        setAsNeighbours(getCountry("Siberia"), getCountry("Irkutsk"));
        setAsNeighbours(getCountry("Siberia"), getCountry("Yakutsk"));
        setAsNeighbours(getCountry("Ural"), getCountry("Afghanistan"));
        setAsNeighbours(getCountry("Ural"), getCountry("China"));
        setAsNeighbours(getCountry("Ural"), getCountry("Siberia"));
        setAsNeighbours(getCountry("Ural"), getCountry("Ukraine"));
        setAsNeighbours(getCountry("Yakutsk"), getCountry("Irkutsk"));
        setAsNeighbours(getCountry("Yakutsk"), getCountry("Kamchatka"));
        setAsNeighbours(getCountry("Yakutsk"), getCountry("Siberia"));

        //Oceania
        setAsNeighbours(getCountry("Eastern Australia"), getCountry("New Guinea"));
        setAsNeighbours(getCountry("Eastern Australia"), getCountry("Western Australia"));
        setAsNeighbours(getCountry("Indonesia"), getCountry("New Guinea"));
        setAsNeighbours(getCountry("Indonesia"), getCountry("Western Australia"));
        setAsNeighbours(getCountry("Indonesia"), getCountry("Siam"));
        setAsNeighbours(getCountry("New Guinea"), getCountry("Eastern Australia"));
        setAsNeighbours(getCountry("New Guinea"), getCountry("Western Australia"));
        setAsNeighbours(getCountry("New Guinea"), getCountry("Indonesia"));
        setAsNeighbours(getCountry("Western Australia"), getCountry("Eastern Australia"));
        setAsNeighbours(getCountry("Western Australia"), getCountry("Indonesia"));
        setAsNeighbours(getCountry("Western Australia"), getCountry("New Guinea"));
    }

    private void setAsNeighbours(Country c1, Country c2) {
        if (!c1.getNeighbors().contains(c2)) {
            c1.addNeighbor(c2);
        }
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

    public void loadContinents() {
        //System.out.println("N America");
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

        //System.out.println("S America");
        //South America
        for (int i = 9; i < 13; i++) {
            southAmerica.addCountry(countries.get(i));
        }

        //System.out.println("Europe");
        //Europe
        for (int i = 13; i < 20; i++) {
            europe.addCountry(countries.get(i));
        }

        //System.out.println("Africa");
        //Africa
        for (int i = 20; i < 26; i++) {
            africa.addCountry(countries.get(i));
        }

        //System.out.println("Asia");
        //Asia
        for (int i = 26; i < 38; i++) {
            asia.addCountry(countries.get(i));
        }

        //System.out.println("Australia");
        //Australia
        for (int i = 38; i < 42; i++) {
            australia.addCountry(countries.get(i));
        }

        for (Continent c : continents) {
            System.out.println(c.getName());
            for (Country co : c.getCountries()) {
                System.out.println(co.toString());
            }
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
