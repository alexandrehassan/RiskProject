import java.util.ArrayList;

/**
 * Map class
 * Map creates a new map, and loads it with the countries and sets each country's neighbors
 * @author Baillie Noell, Sarah Abdallah - Team Group
 * @version 3: Oct 18 2020
 *
 */
public class Map {
    private final ArrayList<Country> countries;

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
        //North America
        alaska.addNeighbor(alberta);
        alaska.addNeighbor(northwestTerritory);
        alaska.addNeighbor(kamchatka);

        alberta.addNeighbor(westernUnitedStates);
        alberta.addNeighbor(alaska);
        alberta.addNeighbor(northwestTerritory);
        alberta.addNeighbor(ontario);

        centralAmerica.addNeighbor(westernUnitedStates);
        centralAmerica.addNeighbor(easternUnitedStates);
        centralAmerica.addNeighbor(venezuela);

        easternUnitedStates.addNeighbor(ontario);
        easternUnitedStates.addNeighbor(quebec);
        easternUnitedStates.addNeighbor(centralAmerica);
        easternUnitedStates.addNeighbor(westernUnitedStates);

        greenland.addNeighbor(northwestTerritory);
        greenland.addNeighbor(ontario);
        greenland.addNeighbor(quebec);
        greenland.addNeighbor(iceland);

        northwestTerritory.addNeighbor(alaska);
        northwestTerritory.addNeighbor(alberta);
        northwestTerritory.addNeighbor(ontario);
        northwestTerritory.addNeighbor(greatBritain);

        ontario.addNeighbor(northwestTerritory);
        ontario.addNeighbor(alberta);
        ontario.addNeighbor(westernUnitedStates);
        ontario.addNeighbor(easternUnitedStates);
        ontario.addNeighbor(quebec);
        ontario.addNeighbor(greenland);

        quebec.addNeighbor(ontario);
        quebec.addNeighbor(easternUnitedStates);
        quebec.addNeighbor(greenland);

        westernUnitedStates.addNeighbor(alberta);
        westernUnitedStates.addNeighbor(ontario);
        westernUnitedStates.addNeighbor(easternUnitedStates);
        westernUnitedStates.addNeighbor(centralAmerica);

        //South America
        argentina.addNeighbor(brazil);
        argentina.addNeighbor(peru);

        brazil.addNeighbor(argentina);
        brazil.addNeighbor(peru);
        brazil.addNeighbor(venezuela);
        brazil.addNeighbor(northAfrica);

        peru.addNeighbor(argentina);
        peru.addNeighbor(brazil);
        peru.addNeighbor(venezuela);

        venezuela.addNeighbor(brazil);
        venezuela.addNeighbor(peru);
        venezuela.addNeighbor(centralAmerica);

        //Europe
        greatBritain.addNeighbor(iceland);
        greatBritain.addNeighbor(northernEurope);
        greatBritain.addNeighbor(scandinavia);
        greatBritain.addNeighbor(westernEurope);

        iceland.addNeighbor(greenland);
        iceland.addNeighbor(greatBritain);
        iceland.addNeighbor(scandinavia);

        northernEurope.addNeighbor(greatBritain);
        northernEurope.addNeighbor(scandinavia);
        northernEurope.addNeighbor(ukraine);
        northernEurope.addNeighbor(southernEurope);
        northernEurope.addNeighbor(westernEurope);

        scandinavia.addNeighbor(iceland);
        scandinavia.addNeighbor(greatBritain);
        scandinavia.addNeighbor(northernEurope);
        scandinavia.addNeighbor(ukraine);

        southernEurope.addNeighbor(westernEurope);
        southernEurope.addNeighbor(northernEurope);
        southernEurope.addNeighbor(ukraine);
        southernEurope.addNeighbor(middleEast);
        southernEurope.addNeighbor(egypt);
        southernEurope.addNeighbor(northAfrica);

        ukraine.addNeighbor(scandinavia);
        ukraine.addNeighbor(northernEurope);
        ukraine.addNeighbor(southernEurope);
        ukraine.addNeighbor(afghanistan);
        ukraine.addNeighbor(middleEast);
        ukraine.addNeighbor(ural);

        westernEurope.addNeighbor(greatBritain);
        westernEurope.addNeighbor(northernEurope);
        westernEurope.addNeighbor(southernEurope);
        westernEurope.addNeighbor(northAfrica);

        //Africa
        congo.addNeighbor(eastAfrica);
        congo.addNeighbor(northAfrica);
        congo.addNeighbor(southAfrica);

        eastAfrica.addNeighbor(congo);
        eastAfrica.addNeighbor(egypt);
        eastAfrica.addNeighbor(northAfrica);
        eastAfrica.addNeighbor(southAfrica);
        eastAfrica.addNeighbor(madagascar);

        egypt.addNeighbor(eastAfrica);
        egypt.addNeighbor(northAfrica);
        egypt.addNeighbor(southernEurope);

        madagascar.addNeighbor(eastAfrica);
        madagascar.addNeighbor(southAfrica);

        northAfrica.addNeighbor(congo);
        northAfrica.addNeighbor(eastAfrica);
        northAfrica.addNeighbor(egypt);
        northAfrica.addNeighbor(brazil);
        northAfrica.addNeighbor(southernEurope);
        northAfrica.addNeighbor(westernEurope);

        southAfrica.addNeighbor(congo);
        southAfrica.addNeighbor(eastAfrica);
        southAfrica.addNeighbor(madagascar);

        //Asia
        afghanistan.addNeighbor(middleEast);
        afghanistan.addNeighbor(india);
        afghanistan.addNeighbor(china);
        afghanistan.addNeighbor(ural);
        afghanistan.addNeighbor(ukraine);

        china.addNeighbor(afghanistan);
        china.addNeighbor(india);
        china.addNeighbor(siam);
        china.addNeighbor(mongolia);
        china.addNeighbor(siberia);
        china.addNeighbor(ural);

        india.addNeighbor(afghanistan);
        india.addNeighbor(china);
        india.addNeighbor(middleEast);
        india.addNeighbor(siam);

        irkutsk.addNeighbor(kamchatka);
        irkutsk.addNeighbor(mongolia);
        irkutsk.addNeighbor(siberia);
        irkutsk.addNeighbor(yakutsk);

        japan.addNeighbor(kamchatka);
        japan.addNeighbor(mongolia);

        kamchatka.addNeighbor(irkutsk);
        kamchatka.addNeighbor(japan);
        kamchatka.addNeighbor(mongolia);
        kamchatka.addNeighbor(yakutsk);

        middleEast.addNeighbor(eastAfrica);
        middleEast.addNeighbor(egypt);
        middleEast.addNeighbor(afghanistan);
        middleEast.addNeighbor(india);
        middleEast.addNeighbor(ukraine);

        mongolia.addNeighbor(china);
        mongolia.addNeighbor(siberia);
        mongolia.addNeighbor(irkutsk);
        mongolia.addNeighbor(kamchatka);
        mongolia.addNeighbor(japan);

        siam.addNeighbor(china);
        siam.addNeighbor(india);
        siam.addNeighbor(indonesia);

        siberia.addNeighbor(ural);
        siberia.addNeighbor(china);
        siberia.addNeighbor(mongolia);
        siberia.addNeighbor(irkutsk);
        siberia.addNeighbor(yakutsk);

        ural.addNeighbor(afghanistan);
        ural.addNeighbor(china);
        ural.addNeighbor(siberia);
        ural.addNeighbor(ukraine);

        yakutsk.addNeighbor(irkutsk);
        yakutsk.addNeighbor(kamchatka);
        yakutsk.addNeighbor(siberia);

        //Australia
        easternAustralia.addNeighbor(newGuinea);
        easternAustralia.addNeighbor(westernAustralia);

        indonesia.addNeighbor(newGuinea);
        indonesia.addNeighbor(westernAustralia);
        indonesia.addNeighbor(siam);

        newGuinea.addNeighbor(easternAustralia);
        newGuinea.addNeighbor(westernAustralia);
        newGuinea.addNeighbor(indonesia);

        westernAustralia.addNeighbor(easternAustralia);
        westernAustralia.addNeighbor(indonesia);
        westernAustralia.addNeighbor(newGuinea);

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


}

