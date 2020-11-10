import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK.
 * <p>
 * This class acts as the controller for the game's frame and the model.
 * It implements ActionListener and receives action commands from the
 * frame when that is interacted with before decoding them and calling the
 * correct method in the model accordingly.
 * <p>
 * To add: should be able to receive countries instead of prompting the
 * user to input them, will add later
 *
 * @author Team Group - Jonah Gaudet
 * @version 27-10-2020
 */
public class GameController implements ActionListener {
    private enum State {REINFORCEMENT,ATTACK,MOVEMENT}

    private static final String HELP_COMMAND = "help";
    private static final String NEW_COMMAND = "new";
    private static final String ATTACK_COMMAND = "attack";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private static final String EMPTY = "";

    private final GameModel gameModel;

    private String from;
    private String to;
    private State state;

    public GameController(GameModel gm) {
        this.gameModel = gm;
    }

    public void addGameBoard(mxGraphComponent gameBoard) {
        gameBoard.getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Object cell = gameBoard.getCellAt(e.getX(), e.getY());
                if (!(cell instanceof mxCell)) {
                    System.out.println("Not a cell");
                    return;
                }

                String clickedCountry = (String) ((mxCell) cell).getValue();

                if (clickedCountry.equals(EMPTY)) {
                    return;
                }

                if (!gameModel.playerOwns(clickedCountry) && state != State.ATTACK) {
                    JOptionPane.showMessageDialog(null, "Current player does not own " + clickedCountry);
                    return;
                }

                switch (state) {
                    case REINFORCEMENT -> {
                        try {
                            int reinforcements = gameModel.getCurrentPlayerReinforcements();
                            if (reinforcements <= 0) {
                                toAttackPhase();
                            }

                            int toPut = gameModel.troopSelect(1, reinforcements);
                            gameModel.placeCurrentPlayerReinforcements(clickedCountry, toPut);
                            if (gameModel.getCurrentPlayerReinforcements() == 0) {
                                toAttackPhase();
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case ATTACK -> {
                        if (from.equals(EMPTY)) {
                            from = clickedCountry;
                            JOptionPane.showMessageDialog(null, "Attacking with " + from);
                        } else if (to.equals(EMPTY)) {
                            to = clickedCountry;
                            JOptionPane.showMessageDialog(null, "Attacking " + to);
                            int reply = JOptionPane.showConfirmDialog(null,
                                    "Attacking " + to + " with " + from + ". Blitz attack?",
                                    "Select attack type", JOptionPane.YES_NO_OPTION);
                            gameModel.playAttack(from, to, reply == JOptionPane.YES_OPTION);
                            from = EMPTY;
                            to = EMPTY;
                        }
                    }
                    case MOVEMENT -> {
                        if (from.equals(EMPTY)) {
                            from = clickedCountry;
                            JOptionPane.showMessageDialog(null, "Moving from " + from);
                        } else if (to.equals(EMPTY)) {
                            to = clickedCountry;
                            if (to.equals(from)) {
                                JOptionPane.showMessageDialog(null, "Cannot move to the same country. Input cleared");
                                to = EMPTY;
                                from = EMPTY;
                                return;
                            }
                            JOptionPane.showMessageDialog(null, "Moving troops from  " + from + " to " + to);
                            boolean successfulMove = gameModel.moveTroops(from, to);
                            if (successfulMove) {
                                gameModel.updateState();
                                state = State.REINFORCEMENT;
                                gameModel.nextPlayer(true);
                                gameModel.showCurrentPlayer();
                                gameModel.updateGameViewsTurnState("reinforcement");
                            }
                            from = EMPTY;
                            to = EMPTY;
                        }
                    }
                }
            }
        });
    }

    /**
     * Moves on to the attack phase (removes duplicate code)
     */
    private void toAttackPhase() {
        state = State.ATTACK;
        JOptionPane.showMessageDialog(null,
                "Done placing reinforcements (none left)");
        gameModel.updateGameViewsTurnState("attack");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == null)
            return;

        String command = e.getActionCommand();
        switch (command) {
            case HELP_COMMAND -> gameModel.printHelp();
            case NEW_COMMAND -> {
                gameModel.userCreateGame();
                this.state = State.REINFORCEMENT;
                from = EMPTY;
                to = EMPTY;
                gameModel.updateGameViewsTurnState("reinforcement");
            }
            case ATTACK_COMMAND -> {
                state = State.ATTACK;
                JOptionPane.showMessageDialog(null,
                        "Select a country to attack with, then a country to attack");
                gameModel.updateGameViewsTurnState("attack");
            }
            case MOVE_COMMAND -> {
                state = State.MOVEMENT;
                gameModel.updateGameViewsTurnState("move");
            }
            case END_COMMAND -> {
                state = State.REINFORCEMENT;
                gameModel.nextPlayer(true);
                gameModel.showCurrentPlayer();
                gameModel.updateGameViewsTurnState("reinforcement");
            }
        }
    }

    public int getCurrentReinforcements() {
        return gameModel.getCurrentPlayerReinforcements();
    }
}