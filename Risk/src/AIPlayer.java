/**
 * @author Alexandre Hassan
 * @version 2020-11-15
 */
public class AIPlayer extends Player{

    private boolean hadError;
    private StringBuilder turnMessages;

    /**
     * Default constructor for the class Player.
     *
     * @param name the name of the player.
     */
    public AIPlayer(String name) {
        super(name);
        this.turnMessages = new StringBuilder();
    }

    @Override
    public void handleError(Exception e) {
        hadError = true;
    }

    @Override
    public void handleMessage(String message) {
        turnMessages.append(message).append("\n");
    }
}
