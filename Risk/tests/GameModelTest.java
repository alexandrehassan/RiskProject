import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Mockito is used to mock gameModel to stub some functions.
import static org.mockito.Mockito.*;


class GameModelTest {
    private static final String PLAYER1 = "player 1";
    private static final String PLAYER2 = "player 2";
    private static final String PLAYER3 = "player 3";
    private static final String PLAYER4 = "player 4";
    private static final String PLAYER5 = "player 5";
    private static final String PLAYER6 = "player 6";
    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 6;

    private GameModel model;
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
        model = new GameModel(PLAYERS);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void gameGenerationWrongNumberOfPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        PLAYERS.add(new Player("test"));
        assertThrows(IllegalArgumentException.class, ()-> new GameModel(players), "model allowed 7 players.");
        assertThrows(IllegalArgumentException.class, ()-> new GameModel(PLAYERS), "model allowed 7 players.");
    }

    @Test
    void gameGenerationTroopNumber(){
        ArrayList<Player> players = new ArrayList<>();
        int count=0;
        for(int i = MINIMUM_PLAYERS; i<MAXIMUM_PLAYERS+1; i++){
            for(int j = 0; j<i; j++){
                players.add(new Player(""+j));
            }
            new GameModel(players);

            for (Player player : players) {
                count+=player.getNumberOfTroops();
            }
            assertEquals(GameModel.BEGINNING_TROOPS[i-2]*players.size(),count, i +" players did not " +
                    "generate the correct amount of total troops.");
            count = 0;
            players.clear();
        }
    }

    @Test
    void putReinforcements() {
        Player currentPlayer= model.getCurrentPlayer();
        int beginningCount = currentPlayer.getNumberOfTroops();
        model.placeCurrentPlayerReinforcements(currentPlayer.getPerimeterCountries().get(0).getName(),-1);
        assertEquals(beginningCount,currentPlayer.getNumberOfTroops(),"Number of troops was modified by negative number");
        model.placeCurrentPlayerReinforcements(currentPlayer.getPerimeterCountries().get(0).getName(),1); //Picks a random country.
        assertEquals(beginningCount+1,currentPlayer.getNumberOfTroops(),"Number of troops didn't go up");
    }

    @Test //Only tests Blitz Attack
    void playAttack() {
        GameModel mocked =  spy(new GameModel(PLAYERS));
        Country attacker;
        Country defender;

        //Suppress dialogs/mock inputs.
        doNothing().when(mocked).showMessage(Matchers.anyString());
        when(mocked.troopSelect(anyInt(),anyInt())).thenReturn(1);
        doThrow(new IllegalArgumentException()).when(mocked).showErrorPupUp(Matchers.any());

        Player currentPlayer= mocked.getCurrentPlayer();
        ArrayList<Country> perimeterCountries = currentPlayer.getPerimeterCountries();

        //Make sure attacker has more than one troop.
        int index = 0;
        attacker = perimeterCountries.get(index);
        while(attacker.getTroops()==1){
            index++;
            attacker = perimeterCountries.get(index);
        }

        //Get a Possible Attack
        index =0;
        defender = attacker.getNeighbors().get(index);
        while(currentPlayer.hasCountry(defender.getName())){
            index++;
            defender = attacker.getNeighbors().get(index);
        }

        int initialTroops = attacker.getTroops() + defender.getTroops();
        mocked.playAttack(attacker.getName(),defender.getName(),true);
        assertNotEquals(initialTroops, attacker.getTroops() + defender.getTroops(), "A valid Attack Failed");


        //Check attack himself.
        attacker = currentPlayer.getPerimeterCountries().get(0);
        defender = currentPlayer.getPerimeterCountries().get(1);
        Country finalAttacker = attacker;
        Country finalDefender = defender;

        assertThrows(IllegalArgumentException.class,()->
                mocked.playAttack(finalAttacker.getName(),
                        finalDefender.getName(),true));
    }

    @Test
    void moveTroops() {
    }

    @Test
    void playerOwns() {
        Player p1 = model.getCurrentPlayer();
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
        assertNotEquals(startingIndex,PLAYERS.indexOf(model.getCurrentPlayer()));
    }

    @Test
    void placeCurrentPlayerReinforcements() {
        int playerReinforcements = model.getCurrentPlayerReinforcements();
        ArrayList<Country> p1OutsideCountries = model.getCurrentPlayer().getPerimeterCountries();
        int countryBeginningTroops = p1OutsideCountries.get(0).getTroops();

        assertThrows(IllegalArgumentException.class,
                ()-> model.placeCurrentPlayerReinforcements(p1OutsideCountries.get(0).getName(),-1),
                "Allowed an invalid number of troops");
        assertEquals(countryBeginningTroops, p1OutsideCountries.get(0).getTroops(),
                "Errored but modified the number of troops anyway");
        assertEquals(playerReinforcements,model.getCurrentPlayerReinforcements(),
                "Errored but modified the number of reinforcements left to place anyway");

        assertDoesNotThrow(()-> model.placeCurrentPlayerReinforcements(p1OutsideCountries.get(0).getName(),1),
                "Threw an error when it shouldn't have");
        assertNotEquals(countryBeginningTroops, p1OutsideCountries.get(0).getTroops(),
                "Did not modify the number of troops on the country");
        assertNotEquals(playerReinforcements,model.getCurrentPlayerReinforcements(),
                "Did not modify the number of reinforcements left");

        model.nextPlayer(true);
        playerReinforcements = model.getCurrentPlayerReinforcements();
        assertThrows(IllegalArgumentException.class,
                ()-> model.placeCurrentPlayerReinforcements(p1OutsideCountries.get(0).getName(),1),
                "Allowed an invalid country");
        assertEquals(countryBeginningTroops+1, p1OutsideCountries.get(0).getTroops(),
                "Errored but modified the number of troops anyway");
        assertEquals(playerReinforcements,model.getCurrentPlayerReinforcements(),
                "Errored but modified the number of reinforcements left to place anyway");
    }
}