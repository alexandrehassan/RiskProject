import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * GameEvent contains only a name to be used by the frame
 *
 * @version 27-10-2020
 * @author Team Group - Jonah Gaudet
 */

public class GameStartEvent extends EventObject {

    private String name;
    private int numPlayers;
    public GameStartEvent (GameModel gameModel, String name, int players) {
        super(gameModel);
        this.name = name;
        this.numPlayers = players;
    }

    public String getName () {
        return name;
    }

    public int getNumPlayers () { return numPlayers; }
}
