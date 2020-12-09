import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Series of Tests for class Continent.
 * @author Team Group - Alexandre Hassan
 * @version 2020-10-30
 */
class ContinentTest {
    private static final String NAME = "TestName";
    private static final int TROOPS = 3;
    private static Continent CONTINENT;
    private static ArrayList<Country> countries;

    @BeforeEach
    void setUp() {
        CONTINENT = new Continent(NAME, TROOPS, Map.BLACK);

        countries = new ArrayList<>();
        countries.add(new Country("country1"));
        countries.add(new Country("country2"));
        countries.add(new Country("country3"));
        countries.add(new Country("country4"));
    }

    @Test
    void getName() {
        Continent continent = new Continent(NAME, TROOPS, Map.BLACK);
        assertEquals(NAME, continent.getName());
    }

    @Test
    void continentNameNull(){
        Continent continent = new Continent(NAME, TROOPS, Map.BLACK);
        assertNotNull(continent.getName());
    }

    @Test
    void twoContinentSameNameNotEqual(){
        Continent continent1 = new Continent(NAME, TROOPS, Map.BLACK);
        Continent continent2 = new Continent(NAME, TROOPS, Map.BLACK);
        assertNotSame(continent1, continent2);
    }

    @Test
    void getCountriesEmpty() {
        assertEquals(new ArrayList<>(), CONTINENT.getCountries());
    }

    @Test
    void getCountriesNotEmpty() {
        CONTINENT.addCountries(countries);
        assertTrue(CONTINENT.getCountries().containsAll(countries));
    }

    @Test
    void getReinforcements() {
        assertEquals(CONTINENT.getReinforcements(), TROOPS);
    }

    @Test
    void addCountriesNoDuplicates(){
        CONTINENT.addCountries(countries);
        ArrayList<Country> expected = (ArrayList<Country>) CONTINENT.getCountries().clone();
        CONTINENT.addCountries(countries);
        assert(expected.equals(CONTINENT.getCountries()));
    }
}
