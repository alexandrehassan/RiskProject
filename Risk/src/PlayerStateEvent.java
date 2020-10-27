import javax.swing.*;
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

public class PlayerStateEvent extends EventObject {

    private String info;
    private int playerOrder;
    public PlayerStateEvent (GameModel gameModel, String info, int order) {
        super(gameModel);
        this.info = info;
        this.playerOrder = order;
    }

    public String getInfo () {
        return info;
    }

    public int getOrder () { return playerOrder; }
}
