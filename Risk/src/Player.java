import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @version 17-10-2020
 * @author Team Group
 */
public class Player {
    public final ArrayList<Country> countries;
    private final String name;
    private boolean eliminated;

    public Player(String name) {
        this.name = name;
        countries = new ArrayList<>();
        eliminated = false;
    }

    public void addCountry(Country country){
        countries.add(country);
    }

    public String getName () {
        return name;
    }

    public boolean isEliminated () {
        return eliminated;
    }

    public void checkEliminated () {
        setEliminated(!(countries.size() > 0));
    }

    private void setEliminated (boolean isEliminated) {
        this.eliminated = isEliminated;
    }

    public boolean hasCountry (String name) {
        for (Country c : countries)
            if (c.toString().equals(name))
                return true;
        return false;
    }

    /**
     * gets the number of reinforcements the player should be able to place at the beginning of the turn
     *
     * @return The number of reinforcement allowed.
     */
    public int getReinforcements(){
        return Math.max(3, countries.size()/3) + checkContinents();
    }

    /**
     * This will be used to check ownership of the different continents when the continents are added.
     *
     * @return the number of bonus troops earned from the continents
     */
    private int checkContinents(){
        return 0;

        //TODO: Discuss implementation of this. (how to grab all continents)
//        int extraTroops = 0;
//        for(Continent continent: map.continents){
//            if(countries.containsAll(continent)){
//                extraTroops += continent.getReinforcements();
//            }
//        }
//        return extraTroops;
    }


    public Country getCountry (String name) {
        for (Country c : countries)
            if (c.toString().equals(name))
                return c;
        return null;
    }

    public void print(){
        System.out.println("[" + name + "]");
        for (Country country: countries) {
            System.out.println(country.toString() + "  Troops: " + country.getTroops());
        }
    }

    public void sortCountries () {
        countries.sort(Comparator.comparing(Country::toString));
    }

//    public void testPathExists () {
//        for (Country c : countries)
//            System.out.println(c.toString());
//
//        for (Country c : countries) {
//            System.out.print("Path between " + countries.get(0) + " and " + c.toString());
//            if (pathExists(countries.get(0), c))
//                System.out.println(" exists");
//            else
//                System.out.println(" does not exist");
//        }
//    }

    public boolean pathExists (Country start, Country finish) {
        ArrayList<Country> accessibleCountries = new ArrayList<>();
        accessibleCountries.add(start);
        getAccessibleCountries(start, finish, accessibleCountries);
        return accessibleCountries.contains(finish);
    }

    private void getAccessibleCountries (Country country, Country finish, ArrayList<Country> accessibleCountries) {
        for (Country n : country.getNeighbors()) {
            if (!countries.contains(n))
                continue;
            if (n.equals(finish)) {
                accessibleCountries.add(n);
                return;
            }
            if (!accessibleCountries.contains(n)) {
                accessibleCountries.add(n);
                getAccessibleCountries(n, finish, accessibleCountries);
            }
        }
    }
}
