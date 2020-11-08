import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 * <p>
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * PlayerTurnEvents contains only a name to be used by the frame,
 * and corresponds to the player whose turn it currently is
 *
 * @author Team Group - Jonah Gaudet
 * @version 27-10-2020
 */

public class PlayerTurnEvent extends EventObject {

    private final String playerName;
    private final int order;

    public PlayerTurnEvent(GameModel gameModel, String name, int order) {
        super(gameModel);
        this.playerName = name;
        this.order = order;
    }

    public String getName() {
        return playerName;
    }

    public int getOrder() {
        return order;
    }
}
