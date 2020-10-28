import java.util.*;
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

    private Map map;
    private ArrayList<Player> players;
    public GameStartEvent (GameModel gameModel, Map map, ArrayList<Player> players) {
        super(gameModel);
        this.map = map;
        this.players = players;
    }

    public ArrayList<Player> getPlayers () {
        return players;
    }

    public Map getMap () { return map; }
}
