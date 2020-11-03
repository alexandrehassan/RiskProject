import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * PlayerStateEvent contains a player's name and all info pertaining
 * to the player, such as their countries, troops, etc
 *
 * @version 27-10-2020
 * @author Team Group - Jonah Gaudet
 */

public class TurnStateEvent extends EventObject {

    private final String newState;
    public TurnStateEvent (GameModel gameModel, String newState) {
        super(gameModel);
        this.newState = newState;
    }

    public String getNewState () {
        return newState;
    }
}
