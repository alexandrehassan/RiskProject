import javax.swing.*;
import java.util.EventObject;

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
