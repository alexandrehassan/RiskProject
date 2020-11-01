import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.event.*;

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
    private mxGraphComponent gameBoard;

    private String attacker;
    private String defender;
    private boolean attackMode;

    public GameController (GameModel gm) {
        this.gameModel = gm;
        this.attackMode = false;
    }

    public void addGameBoard (mxGraphComponent gameBoard) {
        this.gameBoard = gameBoard;
        gameBoard.getGraphControl().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.println("Click received");
                Object cell = gameBoard.getCellAt(e.getX(), e.getY());
                if (!(cell instanceof mxCell))
                {
                    System.out.println("Not a cell");
                    return;
                }

                if (attackMode) {
                    if (attacker.equals("")) {
                        attacker = (String) ((mxCell) cell).getValue();
                        JOptionPane.showMessageDialog(null, "Attacking with " + attacker);
                    }
                    else if (defender.equals("")) {
                        defender = (String) ((mxCell) cell).getValue();
                        JOptionPane.showMessageDialog(null, "Attacking " + defender);
                        gameModel.playAttack(attacker, defender);
                        attackMode = false;
                    }
                }
                else {
                    gameModel.getCountryInfo((String) ((mxCell) cell).getValue());
                }
            }
        });
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getActionCommand() == null)
            return;

        String command = e.getActionCommand().split(" ")[0];
        switch (command) {
            case "new" -> gameModel.userCreateGame();
            case "attack" -> {
                System.out.println("Attacking");
                attackMode = true;
                attacker = "";
                defender = "";
                JOptionPane.showMessageDialog(null,
                        "Select a country to attack with, then a country to attack");
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