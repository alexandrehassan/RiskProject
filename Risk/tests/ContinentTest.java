import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContinentTest {
    private static String NAME = "TestName";
    private static int TROOPS = 3;
    private static Continent CONTINENT;
    private static ArrayList<Country> countries;
    private static Country country1, country2, country3, country4;

    @BeforeEach
    void setUp() {
        CONTINENT = new Continent(NAME, TROOPS);

        countries = new ArrayList<>();
        countries.add(country1 = new Country("country1"));
        countries.add(country2 = new Country("country2"));
        countries.add(country3 = new Country("country3"));
        countries.add(country4 =new Country("country4"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        Continent continent = new Continent(NAME, TROOPS);
        assertTrue(continent.getName().equals(NAME));
    }

    @Test
    void continentNameNull(){
        Continent continent = new Continent("null", TROOPS);
        assertTrue(continent.getName() != null);
    }

    @Test
    void twoContinentSameNameNotEqual(){
        Continent continent1 = new Continent(NAME, TROOPS);
        Continent continent2 = new Continent(NAME, TROOPS);
        assertTrue (continent1 != continent2);
    }

    @Test
    void getCountriesEmpty() {
        assertTrue(CONTINENT.getCountries().equals(new ArrayList<>()));
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