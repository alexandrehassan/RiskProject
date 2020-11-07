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
        model.putReinforcements(currentPlayer.getPerimeterCountries().get(0).getName(),-1);
        assertEquals(beginningCount,currentPlayer.getNumberOfTroops(),"Number of troops was modified by negative number");
        model.putReinforcements(currentPlayer.getPerimeterCountries().get(0).getName(),1); //Picks a random country.
        assertEquals(beginningCount+1,currentPlayer.getNumberOfTroops(),"Number of troops didn't go up");
    }

    @Test //Only tests Blitz Attack
    void playAttack() {
        GameModel mocked =  spy(new GameModel(PLAYERS));
        doNothing().when(mocked).showMessage(Matchers.anyString()) ;
        Player currentPlayer= mocked.getCurrentPlayer();

        ArrayList<Country> perimeterCountries = currentPlayer.getPerimeterCountries();
        Country attacker = perimeterCountries.get(0);
        //Get a Possible Attack
        int index =0;
        Country defender = attacker.getNeighbors().get(index);
        while(currentPlayer.hasCountry(defender)){
            index++;
        }
        int initialTroops = attacker.getTroops() + defender.getTroops();
        mocked.playAttack(attacker.getName(),defender.getName(),true);
        assertNotEquals(initialTroops, attacker.getTroops() + defender.getTroops(), "A valid Attack Failed");

        attacker = currentPlayer.getPerimeterCountries().get(0);
        defender = currentPlayer.getPerimeterCountries().get(1);

    }

    @Test
    void moveTroops() {
    }

    @Test
    void playerOwns() {
    }

    @Test
    void nextPlayer() {
    }

    @Test
    void getCurrentPlayerReinforcements() {
    }

    @Test
    void placeCurrentPlayerReinforcements() {
    }

    @Test
    void showCurrentPlayer() {
    }

    @Test
    void updateState() {
    }

    @Test
    void troopSelect() {
    }
}