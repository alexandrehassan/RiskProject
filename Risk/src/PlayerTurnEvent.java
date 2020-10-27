import javax.swing.*;
import java.util.EventObject;

public class PlayerTurnEvent extends EventObject {

    private String playerName;
    public PlayerTurnEvent (GameModel gameModel, String name) {
        super(gameModel);
        this.playerName = name;
    }

    public String getName () { return playerName; }
}
