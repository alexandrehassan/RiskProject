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
                break;
            }
            case "attack": {
                System.out.println("Attacking");
                String defendingCountry = (String) JOptionPane.showInputDialog(
                        null,
                        "Defending country: ",
                        "Country name: ",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                String attackingCountry = (String) JOptionPane.showInputDialog(
                        null,
                        "Attacking country: ",
                        "Country name: ",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                try {
                    gameModel.playAttack(attackingCountry, defendingCountry);
                }
                catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
                break;
            }
            case "state": {
                System.out.println("Print State");
                gameModel.updateState();
                break;
            }
            case "help": {
                System.out.println("Get help");
                gameModel.printHelp();
                break;
            }
            case "end": {
                System.out.println("Get next player");
                gameModel.nextPlayer();
                gameModel.showCurrentPlayer();
                break;
            }
        }
    }
}