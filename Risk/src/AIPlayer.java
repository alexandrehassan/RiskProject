import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexandre Hassan
 * @version 2020-11-15
 */
public class AIPlayer extends Player{

    private boolean hadError;
    private StringBuilder turnMessages;
    private GameModel model;

    /**
     * Default constructor for the class Player.
     *
     * @param name the name of the player.
     * @param gameModel
     */
    public AIPlayer(String name, GameModel gameModel) {
        super(name);
        this.turnMessages = new StringBuilder();
        this.model = gameModel;
    }

    @Override
    public void handleError(Exception e) {
        hadError = true;
    }

    @Override
    public void handleMessage(String message) {
        turnMessages.append(message).append("\n");
    }

    public void playTurn(int currentPlayerReinforcements){
        autoPutReinforcements(currentPlayerReinforcements);

        model.updateState();
    }

    public String getTurnMessages(){
        return turnMessages.toString();
    }

    /**
     * Method to add reinforcement to a player's countries automatically,
     * will always add on countries on the exterior of a player's territory.
     * <p>
     * Early version of what will be used for AI players.
     *
     * @param reinforcements the number of troops to place.
     */
    private void autoPutReinforcements(int reinforcements) {
        ArrayList<Country> perimeterCountries = getPerimeterCountries();
        for (int assigned = 0; assigned < reinforcements; assigned++) {
            perimeterCountries.get(ThreadLocalRandom.current().nextInt(0, perimeterCountries.size())).addTroop(1);
        }
    }


    private void autoAttack(){
        for (Country country : super.countries) {

        }
    }
}
