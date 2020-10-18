/**
 * Continents store a list of countries that they contain
 *
 * @author Baillie Noell - Team Group
 * @version 1: October 18 2020
 */

import java.util.ArrayList;

public class Continent {
    private final ArrayList<Country> countries;
    private int reinforcements;

    public Continent(int reinforcements) {
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

}
