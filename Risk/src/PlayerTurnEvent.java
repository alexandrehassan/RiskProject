import javax.swing.*;
import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * PlayerTurnEvents contains only a name to be used by the frame,
 * and corresponds to the player whose turn it currently is
 *
 * @version 27-10-2020
 * @author Team Group - Jonah Gaudet
 */

public class PlayerTurnEvent extends EventObject {

    private String playerName;
    public PlayerTurnEvent (GameModel gameModel, String name) {
        super(gameModel);
        this.playerName = name;
    }

    public String getName () { return playerName; }
}
