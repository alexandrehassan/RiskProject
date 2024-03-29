import java.util.ArrayList;
import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 * <p>
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * GameEvent contains only a name to be used by the frame
 *
 * @author Team Group - Jonah Gaudet
 * @version 27-10-2020
 */

public class GameStartEvent extends EventObject {

    private final Map map;
    private final ArrayList<Player> players;

    public GameStartEvent(GameModel gameModel, Map map, ArrayList<Player> players) {
        super(gameModel);
        this.map = map;
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Map getMap() {
        return map;
    }
}
