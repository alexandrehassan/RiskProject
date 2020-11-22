import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    private static final String PLAYER1 = "player 1";
    private static final String PLAYER2 = "player 2";
    private static final String PLAYER3 = "player 3";
    private static final String PLAYER4 = "player 4";
    private static final String PLAYER5 = "player 5";
    private static final String PLAYER6 = "player 6";
    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 6;

    private DummyModel model;
    private ArrayList<Player> PLAYERS;

    @BeforeEach
    void setUp() {
        PLAYERS = new ArrayList<>();
        PLAYERS.add(new Player(PLAYER1));
        PLAYERS.add(new Player(PLAYER2));
        PLAYERS.add(new Player(PLAYER3));
        PLAYERS.add(new Player(PLAYER4));
        PLAYERS.add(new Player(PLAYER5));
        PLAYERS.add(new Player(PLAYER6));
        model = new DummyModel(PLAYERS);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void gameGenerationWrongNumberOfPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        PLAYERS.add(new Player("test"));
        assertThrows(IllegalArgumentException.class, () -> new GameModel(players), "model allowed 7 players.");
        assertThrows(IllegalArgumentException.class, () -> new GameModel(PLAYERS), "model allowed 7 players.");
    }

    @Test
    void gameGenerationTroopNumber() {
        ArrayList<Player> players = new ArrayList<>();
        int count = 0;
        for (int i = MINIMUM_PLAYERS; i < MAXIMUM_PLAYERS + 1; i++) {
            for (int j = 0; j < i; j++) {
                players.add(new Player("" + j));
            }
            new GameModel(players);

            for (Player player : players) {
                count += player.getNumberOfTroops();
            }
            assertEquals(GameModel.BEGINNING_TROOPS[i - 2] * players.size(), count, i + " players did not " +
                    "generate the correct amount of total troops.");
            count = 0;
            players.clear();
        }
    }

    @Test
    void putReinforcements() {
        Player currentPlayer = model.getCurrentPlayer();
        int beginningCount = currentPlayer.getNumberOfTroops();
        assertThrows(IllegalArgumentException.class,
                () -> model.placeCurrentPlayerReinforcements(currentPlayer.getPerimeterCountries().get(0).getName(), -1));
        assertEquals(beginningCount, currentPlayer.getNumberOfTroops(), "Number of troops was modified by negative number");
        model.placeCurrentPlayerReinforcements(currentPlayer.getPerimeterCountries().get(0).getName(), 1); //Picks a random country.
        assertEquals(beginningCount + 1, currentPlayer.getNumberOfTroops(), "Number of troops didn't go up");
    }

    @Test//Only tests Blitz Attack TODO: Test other attacks.
    void playAttack() {

        Player currentPlayer = model.getCurrentPlayer();
        ArrayList<Country> perimeterCountries = currentPlayer.getPerimeterCountries();
        Country attacker = perimeterCountries.get(0);
        Country defender = attacker.getNeighbors().get(0);

        //Make sure attacker has more than one troop.
        for (Country country : perimeterCountries) {
            if (country.getTroops() > 1) {
                attacker = country;
                break;
            }
        }
        //Get a Possible Attack
        for (Country country : attacker.getNeighbors()) {
            if (!currentPlayer.hasCountry(country)) {
                defender = country;
            }
        }
        int initialTroops = attacker.getTroops() + defender.getTroops();
        model.playAttack(attacker.getName(), defender.getName(), true);
        assertNotEquals(initialTroops, attacker.getTroops() + defender.getTroops(), "A valid Attack Failed");

        //Check attack himself.
        attacker = currentPlayer.getPerimeterCountries().get(0);
        defender = currentPlayer.getPerimeterCountries().get(1);
        Country finalAttacker = attacker;
        Country finalDefender = defender;


        model.playAttack(finalAttacker.getName(),
                finalDefender.getName(), true);
        assertTrue(model.threwException);

    }

    @Test
    void moveTroops() {
    }

    @Test
    void playerOwns() {
        ArrayList<Country> p1OutsideCountries = model.getCurrentPlayer().getPerimeterCountries();
        for (Country outsideCountry : p1OutsideCountries) {
            assertTrue(model.playerOwns(outsideCountry.getName()),
                    "Returned false to a country the player owns");
        }
        model.nextPlayer(true);
        for (Country outsideCountry : p1OutsideCountries) {
            assertFalse(model.playerOwns(outsideCountry.getName()),
                    "Returned true to a country the player doesn't owns");
        }
    }

    @Test
    void nextPlayer() {
        int startingIndex = PLAYERS.indexOf(model.getCurrentPlayer());
        model.nextPlayer(true);
        assertNotEquals(startingIndex, PLAYERS.indexOf(model.getCurrentPlayer()));
    }

    @Test
    void placeCurrentPlayerReinforcements() {
        int playerReinforcements = model.getCurrentPlayerReinforcements();
        ArrayList<Country> p1OutsideCountries = model.getCurrentPlayer().getPerimeterCountries();
        int countryBeginningTroops = p1OutsideCountries.get(0).getTroops();

        assertThrows(IllegalArgumentException.class,
                () -> model.placeCurrentPlayerReinforcements(p1OutsideCountries.get(0).getName(), -1),
                "Allowed an invalid number of troops");
        assertEquals(countryBeginningTroops, p1OutsideCountries.get(0).getTroops(),
                "Errored but modified the number of troops anyway");
        assertEquals(playerReinforcements, model.getCurrentPlayerReinforcements(),
                "Errored but modified the number of reinforcements left to place anyway");

        assertDoesNotThrow(() -> model.placeCurrentPlayerReinforcements(p1OutsideCountries.get(0).getName(), 1),
                "Threw an error when it shouldn't have");
        assertNotEquals(countryBeginningTroops, p1OutsideCountries.get(0).getTroops(),
                "Did not modify the number of troops on the country");
        assertNotEquals(playerReinforcements, model.getCurrentPlayerReinforcements(),
                "Did not modify the number of reinforcements left");

        model.nextPlayer(true);
        playerReinforcements = model.getCurrentPlayerReinforcements();
        assertThrows(IllegalArgumentException.class,
                () -> model.placeCurrentPlayerReinforcements(p1OutsideCountries.get(0).getName(), 1),
                "Allowed an invalid country");
        assertEquals(countryBeginningTroops + 1, p1OutsideCountries.get(0).getTroops(),
                "Errored but modified the number of troops anyway");
        assertEquals(playerReinforcements, model.getCurrentPlayerReinforcements(),
                "Errored but modified the number of reinforcements left to place anyway");
    }
    public class DummyModel extends GameModel {

        public DummyModel(ArrayList<Player> players) {
            super(players);
        }
        public boolean threwException = false;

        public int troopSelect(int minimum, int maximum) {
            //System.out.println("Reached 1");
            return minimum;
        }

        public void showErrorPopUp(Exception e) {
            threwException = true;
        }
        public void resetError(){
            threwException = false;
        }

        public void showMessage(String message) {
        }
    }

}