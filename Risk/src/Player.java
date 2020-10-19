import java.util.ArrayList;
import java.util.Collections;

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

    public boolean hasCountry (String name) {
        for (Country c : countries)
            if (c.toString().equals(name))
                return true;
        return false;
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
        Collections.sort(countries, (s1, s2) -> {
            return (s1.toString().compareTo(s2.toString()));
        });
    }
}
