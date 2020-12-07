import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

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
    public enum State {UNDECLARED, REINFORCEMENT, ATTACK, MOVEMENT}

    public static final String HELP_COMMAND = "help";
    public static final String NEW_COMMAND = "new";
    public static final String ATTACK_COMMAND = "attack";
    public static final String MOVE_COMMAND = "move";
    public static final String END_COMMAND = "end";
    public static final String REINFORCEMENT_COMMAND = "reinforcement";
    public static final String EMPTY = "";
    public static final String HISTORY_COMMAND = "history";

    private final GameModel gameModel;

    private String from;
    private String to;
    private State state;

    /**
     * Constructor for the GameController
     *
     * @param gm a GameModel to control
     */
    public GameController(GameModel gm) {
        this.gameModel = gm;
    }

    /**
     * Adds a game board to receive / process commands from
     *
     * @param gameBoard a gameBoard to get commands from
     */
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
                    case UNDECLARED, REINFORCEMENT -> reinforcementState(clickedCountry);
                    case ATTACK -> attackState(clickedCountry);
                    case MOVEMENT -> movementState(clickedCountry);
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
        gameModel.updateGameViewsTurnState(state);
    }

    /**
     * Reads an action from the view, then interprets it and executes commands
     * accordingly
     *
     * @param e the ActionEvent to interpret
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == null)
            return;

        String command = e.getActionCommand();
        switch (command) {

            case HELP_COMMAND -> JOptionPane.showMessageDialog(null, gameModel.getHelp());

            case NEW_COMMAND -> {
                if(gameModel.getCurrentPlayer() == null){
                    createNewGame();
                }
                else if(JOptionPane.showConfirmDialog(null,"You are about to start a new game",
                        "Confirm",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    gameModel.resetModel();
                    createNewGame();
                }
            }
            case ATTACK_COMMAND -> {
                state = State.ATTACK;
                JOptionPane.showMessageDialog(null,
                        "Select a country to attack with, then a country to attack");
                gameModel.updateGameViewsTurnState(state);
            }
            case MOVE_COMMAND -> {
                state = State.MOVEMENT;
                gameModel.updateGameViewsTurnState(state);
            }
            case END_COMMAND -> {
                state = State.REINFORCEMENT;
                gameModel.nextPlayer(true);
                if (gameModel.getCurrentPlayer() != null) {
                    gameModel.showCurrentPlayer();
                    gameModel.updateGameViewsTurnState(state);
                }
            }
            case HISTORY_COMMAND -> {
                JTextArea textArea = new JTextArea(gameModel.getHistory(), 50, 70);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
                JOptionPane.showMessageDialog(null, scrollPane);
            }
        }
    }

    private void createNewGame() {
        if (gameModel.userCreateGame()) {
            from = EMPTY;
            to = EMPTY;
            System.out.println(gameModel.getCurrentPlayer().getName());
            if (gameModel.getCurrentPlayer() instanceof AIPlayer) {
                this.state = State.UNDECLARED;
                gameModel.nextPlayer(true);
            } else {
                this.state = State.REINFORCEMENT;
                gameModel.updateGameViewsTurnState(state);
            }
        }
    }

    /**
     * Gets the number of reinforcements currently available to the current players
     *
     * @return the number of available reinforcements
     */
    public int getCurrentReinforcements() {
        return gameModel.getCurrentPlayerReinforcements();
    }

    public void reinforcementState(String clickedCountry) {
        state = State.REINFORCEMENT;
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

    public void attackState(String clickedCountry) {
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

    public void movementState(String clickedCountry) {
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
                gameModel.updateGameViewsTurnState(state);
            }
            from = EMPTY;
            to = EMPTY;
        }
    }
}