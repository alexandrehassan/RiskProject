//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
///**
// * Series of Tests for class Map.
// * @author Team Group - Alexandre Hassan
// * @version 2020-10-31
// */
//class MapTest {
//    private final Map map = new Map();
//    private ArrayList<Continent> continents;
//    @BeforeEach
//    void setUp() {
//        continents = new ArrayList<>();
//        continents.add(map.getContinent(Map.NORTH_AMERICA));
//        continents.add(map.getContinent(Map.SOUTH_AMERICA));
//        continents.add(map.getContinent(Map.EUROPE));
//        continents.add(map.getContinent(Map.AFRICA));
//        continents.add(map.getContinent(Map.ASIA));
//        continents.add(map.getContinent(Map.AUSTRALIA));
//    }
//
//    @Test
//    void getCountry() {
//        assertEquals(map.getCountry(Map.EASTERN_AUSTRALIA).getName(),
//                Map.EASTERN_AUSTRALIA);
//    }
//
//    @Test
//    void getCountryNull() {
//        assertThrows(IllegalArgumentException.class, () -> map.getCountry(null));
//    }
//
//    @Test
//    void getInvalidCountry() {
//        assertThrows(IllegalArgumentException.class, () -> map.getCountry("NotACountry"));
//    }
//
//    @Test
//    void continentCreation() {
//        assertEquals(map.getContinent(Map.NORTH_AMERICA).getCountries().size(), 9,
//                Map.NORTH_AMERICA + " has the wrong number of countries");
//        assertEquals(map.getContinent(Map.SOUTH_AMERICA).getCountries().size(), 4,
//                Map.SOUTH_AMERICA + " has the wrong number of countries");
//        assertEquals(map.getContinent(Map.EUROPE).getCountries().size(), 7,
//                Map.EUROPE + " has the wrong number of countries");
//        assertEquals(map.getContinent(Map.AFRICA).getCountries().size(), 6,
//                Map.AFRICA + " has the wrong number of countries");
//        assertEquals(map.getContinent(Map.ASIA).getCountries().size(), 12,
//                Map.ASIA + " has the wrong number of countries");
//        assertEquals(map.getContinent(Map.AUSTRALIA).getCountries().size(), 4,
//                Map.AUSTRALIA + " has the wrong number of countries");
//    }
//
//    @Test
//    void getContinents() {
//        assert(map.getContinents().containsAll(continents));
//    }
//
//    @Test
//    void getInvalidContinent() {
//        assertThrows(IllegalArgumentException.class, () -> map.getContinent("NotACountry"));
//    }
//
//    @Test
//    void getNullContinent() {
//        assertThrows(IllegalArgumentException.class, () -> map.getContinent(null));
//    }
//
//    @Test
//    void getShuffledKeys() {
//        assertEquals(map.getShuffledKeys().size(), 42);
//    }
//}