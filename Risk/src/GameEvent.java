import javax.swing.*;
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
