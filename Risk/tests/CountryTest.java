import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Series of Tests for class Country.
 * @author Team Group - Alexandre Hassan
 * @version 2020-10-30
 */
class CountryTest {
    private static final String NAME = "TestName";
    private Country COUNTRY;
    private static ArrayList<Country> neighbors;
    private static final int InitialTroops = 1;

    @BeforeEach
    void setUp() {
        COUNTRY = new Country(NAME);

        neighbors = new ArrayList<>();
        neighbors.add(new Country("country1"));
        neighbors.add(new Country("country2"));
        neighbors.add(new Country("country3"));
        neighbors.add(new Country("country4"));
    }

    @Test
    void getName() {
        assertEquals(NAME, COUNTRY.getName());
    }

    @Test
    void countryNameNull(){
        Country country = new Country("null");
        assertNotNull(country.getName());
    }

    @Test
    void addNeighbor() {
        for (Country neighbor : neighbors) {
            COUNTRY.addNeighbor(neighbor);
            assertTrue(COUNTRY.hasNeighbor(neighbor));
            assertTrue(COUNTRY.getNeighbors().contains(neighbor));
        }
    }

    @Test
    void addTroopNegative() {
        COUNTRY.addTroop(-1);
        assertEquals(COUNTRY.getTroops(),InitialTroops);
    }

    @Test
    void addTroopZero() {
        COUNTRY.addTroop(0);
        assertEquals(COUNTRY.getTroops(),InitialTroops);
    }

    @Test
    void addTroopPositive() {
        COUNTRY.addTroop(1);
        assertEquals(COUNTRY.getTroops(),InitialTroops + 1);
    }


    @Test
    void removeTroopNegative() {
        COUNTRY.removeTroops(-1);
        assertEquals(COUNTRY.getTroops(),InitialTroops);
    }

    @Test
    void removeTroopZero() {
        COUNTRY.removeTroops(0);
        assertEquals(InitialTroops,COUNTRY.getTroops());
    }

    @Test
    void removeTroopPositive() {
        COUNTRY.removeTroops(1);
        assertEquals(InitialTroops - 1,COUNTRY.getTroops());
    }

    @Test
    void removeTroopBig() {
        COUNTRY.removeTroops(100);
        assertEquals(0,COUNTRY.getTroops());
    }
}