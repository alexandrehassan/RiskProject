import javax.swing.*;

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
import java.util.EventObject;

public class GameEvent extends EventObject {

    private String name;

    public GameEvent (GameModel gameModel, String name) {
        super(gameModel);
        this.name = name;
    }

    public String getName () {
        return name;
    }
}
