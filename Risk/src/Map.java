/*
Map class
Map creates a new map, and loads it with the countries and sets each country's neighbors

Baillie Noell
Version 1: Oct 17 2020
 */
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.zip.CheckedOutputStream;

public class Map {
    private ArrayList<Country> countries;


    public Map() {
        countries = new ArrayList<>();
        this.createCountries();
        this.setNeighbors();
        this.loadMap();
    }

    public void createCountries() {
        //North America
        Country alaska = new Country("Alaska");
        Country alberta = new Country("Alberta");
        Country centralAmerica = new Country("Central America");
        Country easternUnitedStates = new Country("Eastern United States");
        Country greenland = new Country("Greenland");
        Country northwestTerritory = new Country("Northwest Territory");
        Country ontario = new Country("Ontario");
        Country quebec = new Country("Quebec");
        Country westernUnitedStates = new Country("Western United States");

        //South America
        Country argentina = new Country("Argentina");
        Country brazil = new Country("Brazil");
        Country peru = new Country("Peru");
        Country venezuela = new Country("Venezuela");

        //Europe
        Country greatBritain = new Country("Great Britain");
        Country iceland = new Country("Iceland");
        Country northernEurope = new Country("Northern Europe");
        Country scandinavia = new Country("Scandinavia");
        Country southernEurope = new Country("Southern Europe");
        Country ukraine = new Country("Ukraine");
        Country westernEurope = new Country("Western Europe");

        //Africa
        Country congo = new Country("Congo");
        Country eastAfrica = new Country("East Africa");
        Country egypt = new Country("Egypt");
        Country madagascar = new Country("Madagascar");
        Country northAfrica = new Country("North Africa");
        Country southAfrica = new Country("South Africa");

        //Asia
        Country afghanistan = new Country("Afghanistan");
        Country china = new Country("China");
        Country india = new Country("India");
        Country irkutsk = new Country("Irkutsk");
        Country japan = new Country("Japan");
        Country kamchatka = new Country("Kamchatka");
        Country middleEast = new Country("Middle East");
        Country mongolia = new Country("Mongolia");
        Country siam = new Country("Siam");
        Country siberia = new Country("Siberia");
        Country ural = new Country("Ural");
        Country yakutsk = new Country("Yakutsk");

        //Australia
        Country easternAustralia = new Country("Eastern Australia");
        Country indonesia = new Country("Indonesia");
        Country newGuinea = new Country("New Guinea");
        Country westernAustralia = new Country("Western Australia");


    }
    public void setNeighbors() {

    }

    public void loadMap() {

    }

}

