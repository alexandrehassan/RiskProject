import java.util.ArrayList;
/**
 * Continents store a list of countries that they contain
 *
 * @author Baillie Noell - Team Group
 * @version 3: October 18 2020
 */
public class Continent {
    private String name;
    private final ArrayList<Country> countries;
    private int reinforcements;

    public Continent(String name, int reinforcements) {
        this.name = name;
        this.reinforcements = reinforcements;
        countries = new ArrayList<>();
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public int getReinforcements() {
        return reinforcements;
    }

    public String getName() {
        return name;
    }

}
