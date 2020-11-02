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
    private final static int REINFORCEMENT_STATE = 0;
    private final static int ATTACK_STATE = 1;
    private final static int MOVEMENT_STATE = 2;
    private int reinforcements;

    private final GameModel gameModel;

    private String from;
    private String to;
    private int state;

    public GameController (GameModel gm) {
        this.gameModel = gm;

    }

    public void addGameBoard (mxGraphComponent gameBoard) {
        gameBoard.getGraphControl().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Object cell = gameBoard.getCellAt(e.getX(), e.getY());
                if (!(cell instanceof mxCell))
                {
                    System.out.println("Not a cell");
                    return;
                }

                String clickedCountry = (String) ((mxCell) cell).getValue();
                if (!gameModel.playerOwns(clickedCountry) && state != ATTACK_STATE) {
                    JOptionPane.showMessageDialog(null, "Current player does not own " + clickedCountry);
                    return;
                }

                switch (state) {
                    case REINFORCEMENT_STATE -> {
                        try {
                            if (reinforcements <= 0) {
                                JOptionPane.showMessageDialog(null,
                                        "No more reinforcements to place");
                                return;
                            }
                            int toPut = gameModel.troopSelect(1, reinforcements);
                            gameModel.putReinforcements(clickedCountry, toPut);
                            reinforcements -= toPut;
                            if (reinforcements == 0) {
                                gameModel.updateGameViewsTurnState("attack");
                                state++;
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case ATTACK_STATE -> {
                        if (from.equals("")) {
                            from = clickedCountry;
                            JOptionPane.showMessageDialog(null, "Attacking with " + from);
                        } else if (to.equals("")) {
                            to = clickedCountry;
                            JOptionPane.showMessageDialog(null, "Attacking " + to);
                            gameModel.playAttack(from, to);
                            from = "";
                            to = "";
                        }
                    }
                    case MOVEMENT_STATE -> {
                        if (from.equals("")) {
                            from = clickedCountry;
                            JOptionPane.showMessageDialog(null, "Moving from " + from);
                        } else if (to.equals("")) {
                            to = clickedCountry;
                            if (to.equals(from)) {
                                JOptionPane.showMessageDialog(null, "Cannot move to the same country");
                                to = "";
                                from = "";
                                return;
                            }
                            JOptionPane.showMessageDialog(null, "Moving troops from  " + from + " to " + to);
                            boolean successfulMove = gameModel.moveTroops(from, to);
                            if (successfulMove) {
                                gameModel.updateState();
                                state = REINFORCEMENT_STATE;
                                gameModel.nextPlayer();
                                gameModel.showCurrentPlayer();
                                reinforcements = gameModel.getReinforcements();
                                gameModel.updateGameViewsTurnState("reinforcement");
                            }
                            from = "";
                            to = "";
                        }
                    }
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
            case "new" -> {
                gameModel.userCreateGame();
                this.state = REINFORCEMENT_STATE;
                from ="";
                to ="";
                reinforcements = gameModel.getReinforcements();
                gameModel.updateGameViewsTurnState("reinforcement");
            }
            case "attack" -> {
                state=ATTACK_STATE;
                JOptionPane.showMessageDialog(null,
                        "Select a country to attack with, then a country to attack");
                gameModel.updateGameViewsTurnState("attack");
            }
            case "move" -> {
                state=MOVEMENT_STATE;
                gameModel.updateGameViewsTurnState("move");
            }
            case "help" -> {
                gameModel.printHelp();
            }
            case "end" -> {
                state=REINFORCEMENT_STATE;
                gameModel.nextPlayer();
                gameModel.showCurrentPlayer();
                reinforcements = gameModel.getReinforcements();
                gameModel.updateGameViewsTurnState("reinforcement");
            }
        }
    }

    public int getCurrentReinforcements () {
        return reinforcements;
    }
}