import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private GameModel gameModel;

    public GameController (GameModel gm) {
        this.gameModel = gm;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getActionCommand() == null)
            return;
        String command = e.getActionCommand().split(" ")[0];
        switch (command) {
            case "new": {
                gameModel.userCreateGame();
                gameModel.playGame();
            }
        }
    }
}