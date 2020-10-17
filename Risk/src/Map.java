/*
Map class
Map creates a new map, and loads it with the countries and sets each country's neighbors

Baillie Noell
Version 1: Oct 17 2020
 */
import java.util.ArrayList;


public class Map {
    private ArrayList<Country> countries;

    //North America
    private Country alaska;
    private Country alberta;
    private Country centralAmerica;
    private Country easternUnitedStates;
    private Country greenland;
    private Country northwestTerritory;
    private Country ontario;
    private Country quebec;
    private Country westernUnitedStates;

    //South America
    private Country argentina;
    private Country brazil;
    private Country peru;
    private Country venezuela;

    //Europe
    private Country greatBritain;
    private Country iceland;
    private Country northernEurope;
    private Country scandinavia;
    private Country southernEurope;
    private Country ukraine;
    private Country westernEurope;

    //Africa
    private Country congo;
    private Country eastAfrica;
    private Country egypt;
    private Country madagascar;
    private Country northAfrica;
    private Country southAfrica;

    //Asia
    private Country afghanistan;
    private Country china;
    private Country india;
    private Country irkutsk;
    private Country japan;
    private Country kamchatka;
    private Country middleEast;
    private Country mongolia;
    private Country siam;
    private Country siberia;
    private Country ural;
    private Country yakutsk;

    //Australia
    private Country easternAustralia;
    private Country indonesia;
    private Country newGuinea;
    private Country westernAustralia;



    public Map() {
        countries = new ArrayList<>();
        this.createCountries();
        this.setNeighbors();
        this.loadMap();
    }

    public void createCountries() {
        //North America
        alaska = new Country("Alaska");
        alberta = new Country("Alberta");
        centralAmerica = new Country("Central America");
        easternUnitedStates = new Country("Eastern United States");
        greenland = new Country("Greenland");
        northwestTerritory = new Country("Northwest Territory");
        ontario = new Country("Ontario");
        quebec = new Country("Quebec");
        westernUnitedStates = new Country("Western United States");

        //South America
        argentina = new Country("Argentina");
        brazil = new Country("Brazil");
        peru = new Country("Peru");
        venezuela = new Country("Venezuela");

        //Europe
        greatBritain = new Country("Great Britain");
        iceland = new Country("Iceland");
        northernEurope = new Country("Northern Europe");
        scandinavia = new Country("Scandinavia");
        southernEurope = new Country("Southern Europe");
        ukraine = new Country("Ukraine");
        westernEurope = new Country("Western Europe");

        //Africa
        congo = new Country("Congo");
        eastAfrica = new Country("East Africa");
        egypt = new Country("Egypt");
        madagascar = new Country("Madagascar");
        northAfrica = new Country("North Africa");
        southAfrica = new Country("South Africa");

        //Asia
        afghanistan = new Country("Afghanistan");
        china = new Country("China");
        india = new Country("India");
        irkutsk = new Country("Irkutsk");
        japan = new Country("Japan");
        kamchatka = new Country("Kamchatka");
        middleEast = new Country("Middle East");
        mongolia = new Country("Mongolia");
        siam = new Country("Siam");
        siberia = new Country("Siberia");
        ural = new Country("Ural");
        yakutsk = new Country("Yakutsk");

        //Australia
        easternAustralia = new Country("Eastern Australia");
        indonesia = new Country("Indonesia");
        newGuinea = new Country("New Guinea");
        westernAustralia = new Country("Western Australia");


    }
    public void setNeighbors() {

    }

    public void loadMap() {
        //North America
        countries.add(alaska);
        countries.add(alberta);
        countries.add(centralAmerica);
        countries.add(easternUnitedStates);
        countries.add(greenland);
        countries.add(northwestTerritory);
        countries.add(ontario);
        countries.add(quebec);
        countries.add(westernUnitedStates);

        //South America
        countries.add(argentina);
        countries.add(brazil);
        countries.add(peru);
        countries.add(venezuela);

        //Europe
        countries.add(greatBritain);
        countries.add(iceland);
        countries.add(northernEurope);
        countries.add(scandinavia);
        countries.add(southernEurope);
        countries.add(ukraine);
        countries.add(westernEurope);

        //Africa
        countries.add(congo);
        countries.add(eastAfrica);
        countries.add(egypt);
        countries.add(madagascar);
        countries.add(northAfrica);
        countries.add(southAfrica);

        //Asia
        countries.add(afghanistan);
        countries.add(china);
        countries.add(india);
        countries.add(irkutsk);
        countries.add(japan);
        countries.add(kamchatka);
        countries.add(middleEast);
        countries.add(mongolia);
        countries.add(siam);
        countries.add(siberia);
        countries.add(ural);
        countries.add(yakutsk);

        //Australia
        countries.add(easternAustralia);
        countries.add(indonesia);
        countries.add(newGuinea);
        countries.add(westernAustralia);

    }

}

