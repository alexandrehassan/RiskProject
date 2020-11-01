import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Series of Tests for class Player.
 * @author Team Group -Alexandre Hassan
 * @version 2020-10-31
 */
class PlayerTest {
    private final static String NAME = "Name";
    private final Map map = new Map();
    private final Player testPlayer = new Player(NAME);

    @Test
    void constructorInvalidName(){
        assertThrows(IllegalArgumentException.class,() -> new Player(null),
                "Constructor allowed null as a name");
        assertThrows(IllegalArgumentException.class,() -> new Player("     "),
                "Constructor allowed \"     \" as a name");
        assertThrows(IllegalArgumentException.class,() -> new Player("\n"),
                "Constructor allowed \\n as a name");
    }

    @Test
    void assignBeginningTroops() {
        int beginningTroops = 20;
        ArrayList<Country> countries = map.getContinent(Map.ASIA).getCountries();
        countries.forEach(testPlayer::addCountry);

        testPlayer.assignBeginningTroops(beginningTroops);

        int count = 0;
        for (Country country : countries) count += country.getTroops();
        assertEquals(count, beginningTroops);
    }

    @Test
    void isEliminated() {
        Player player = new Player(NAME);
        assertTrue(player.isEliminated());
        player.addCountry(map.getCountry(Map.EASTERN_AUSTRALIA));
        player.lost(map.getCountry(Map.EASTERN_AUSTRALIA));
        assertTrue(player.isEliminated());
    }


    @Test
    void numberOfCountries() {
        assertEquals(testPlayer.numberOfCountries(), 0);
        testPlayer.addCountry(map.getCountry(Map.EAST_AFRICA));
        assertEquals(testPlayer.numberOfCountries(),1);
    }

    @Test
    void hasCountry() {
        assertFalse(testPlayer.hasCountry(map.getCountry(Map.EAST_AFRICA)));
        ArrayList<Country> countries = map.getContinent(Map.ASIA).getCountries();
        for (Country country : countries) {
            testPlayer.addCountry(country);
            assertTrue(testPlayer.hasCountry(country));
        }
    }

    @Test
    void hasCountries() {
        ArrayList<Country> countries = map.getContinent(Map.ASIA).getCountries();
        countries.forEach(testPlayer::addCountry);
        assertTrue(testPlayer.hasCountries(countries));
    }

    @Test
    void pathExists() {
        ArrayList<Country> countries = map.getContinent(Map.ASIA).getCountries();
        countries.forEach(testPlayer::addCountry);

        assertTrue(testPlayer.pathExists(map.getCountry(Map.AFGHANISTAN),map.getCountry(Map.SIBERIA)));
        assertFalse(testPlayer.pathExists(map.getCountry(Map.AFGHANISTAN),map.getCountry(Map.BRAZIL)));
        assertFalse(testPlayer.pathExists(map.getCountry(Map.AFGHANISTAN),map.getCountry(Map.UKRAINE)));
        assertTrue(testPlayer.pathExists(map.getCountry(Map.AFGHANISTAN),map.getCountry(Map.INDIA)));
    }

    @Test
    void getPerimeterCountries() {
        ArrayList<Country> countries = map.getContinent(Map.ASIA).getCountries();
        countries.forEach(testPlayer::addCountry);

        ArrayList<Country> expectedPerimeterCountries = new ArrayList<>();
        expectedPerimeterCountries.add(map.getCountry(Map.URAL));
        expectedPerimeterCountries.add(map.getCountry(Map.AFGHANISTAN));
        expectedPerimeterCountries.add(map.getCountry(Map.MIDDLE_EAST));
        expectedPerimeterCountries.add(map.getCountry(Map.SIAM));
        expectedPerimeterCountries.add(map.getCountry(Map.KAMCHATKA));

        assertEquals(testPlayer.getPerimeterCountries().size(), expectedPerimeterCountries.size());
        assertTrue(testPlayer.getPerimeterCountries().containsAll(expectedPerimeterCountries));
    }
}