import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK.
 *
 * This class acts as the controller for the game's frame and the model.
 * It implements ActionListener and receives action commands from the
 * frame when that is interacted with before decoding them and calling the
 * correct method in the model accordingly.
 *
 * To add: should be able to receive countries instead of prompting the
 * user to input them, will add later
 *
 * @version 27-10-2020
 * @author Team Group - Jonah Gaudet
 */
public class GameController implements ActionListener {

    private GameModel gameModel;

    public GameController (GameModel gm) {
        this.gameModel = gm;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getActionCommand() == null)
            return;

        JOptionPane.showMessageDialog(null, "Action detected, action command is: " + e.getActionCommand());

        String command = e.getActionCommand().split(" ")[0];
        switch (command) {
            case "new" -> gameModel.userCreateGame();
            case "attack" -> {
                //TODO: add error handling (make country keys lower case? so we could just .lower everything)
                System.out.println("Attacking");
                String attackingCountry = (String) JOptionPane.showInputDialog(
                        null,
                        "Attacking country: \n" + gameModel.currentPlayerCountryString(),
                        "Country name: ",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                String defendingCountry = (String) JOptionPane.showInputDialog(
                        null,
                        "Defending country: \n" + gameModel.neighborString(attackingCountry),
                        "Country name: ",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                try {
                    if (attackingCountry == null || defendingCountry == null)
                        return;
                    gameModel.playAttack(attackingCountry, defendingCountry);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
            case "state" -> {
                System.out.println("Manually update state");
                gameModel.updateState();
            }
            case "help" -> {
                System.out.println("Get help");
                gameModel.printHelp();
            }
            case "end" -> {
                System.out.println("Get next player");
                gameModel.nextPlayer();
                gameModel.showCurrentPlayer();
            }
        }
    }
}