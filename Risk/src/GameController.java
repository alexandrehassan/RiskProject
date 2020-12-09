import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
 * @author Team Group - Jonah Gaudet, Baillie Noell
 * @version 27-10-2020
 */
public class GameController implements ActionListener {
    public enum State {UNDECLARED, REINFORCEMENT, ATTACK, MOVEMENT}

    public static final String HELP_COMMAND = "help";
    public static final String NEW_COMMAND = "new";
    public static final String ATTACK_COMMAND = "attack";
    public static final String MOVE_COMMAND = "move";
    public static final String REINFORCEMENT_COMMAND = "reinforce";
    public static final String END_COMMAND = "end";
    public static final String EMPTY = "";
    public static final String HISTORY_COMMAND = "history";
    public static final String SAVE_COMMAND = "save";
    public static final String LOAD_GAME_COMMAND = "load";


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
                    //System.out.println("Not a cell");
                    return;
                }

                String clickedCountry = (String) ((mxCell) cell).getValue();

                if (!checkCountry(clickedCountry)) {
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
        if (e.getActionCommand() == null) {
            return;
        }

        String command = e.getActionCommand();
        switch (command) {
            case HELP_COMMAND -> helpCommand();
            case NEW_COMMAND -> newCommand();
            case ATTACK_COMMAND -> attackCommand();
            case MOVE_COMMAND -> moveCommand();
            case END_COMMAND -> endCommand();
            case HISTORY_COMMAND -> historyCommand();
            case LOAD_GAME_COMMAND -> loadCommand();
            case SAVE_COMMAND -> saveCommand();
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
            checkReinforcements();

            int toPut = gameModel.troopSelect(1, gameModel.getCurrentPlayerReinforcements());
            gameModel.placeCurrentPlayerReinforcements(clickedCountry, toPut);

            checkReinforcements();

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
            setEmpty();
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
                setEmpty();
                return;
            }
            JOptionPane.showMessageDialog(null, "Moving troops from  " + from + " to " + to);
            checkSuccessfulMove();
            setEmpty();
        }
    }

    public void helpCommand() {
        JOptionPane.showMessageDialog(null, gameModel.getHelp());
    }

    public void newCommand() {
        if(gameModel.getCurrentPlayer() == null){
            createNewGame();
        }
        else if(JOptionPane.showConfirmDialog(null,"You are about to start a new game",
                "Confirm",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            gameModel.resetModel();
            createNewGame();
        }
    }

    private void createNewGame() {
        Object[] choices = {"Default Map", "Custom Map", "Cancel"};
        int option = JOptionPane.showOptionDialog(null,
                "Choose your map",
                "New Game",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[2]);
        
        if (option == 1) 
            loadMap();
         else if (option == 2) 
            return;
        
        if (gameModel.userCreateGame()) {
            try {
                setEmpty();
                gameModel.nextPlayer();
                this.state = State.REINFORCEMENT;
            }catch (Exception e){

            }
        }
    }

    public void attackCommand() {
        state = State.ATTACK;
        JOptionPane.showMessageDialog(null,
                "Select a country to attack with, then a country to attack");
        gameModel.updateGameViewsTurnState(state);
    }

    public void moveCommand() {
        state = State.MOVEMENT;
        gameModel.updateGameViewsTurnState(state);
    }

    public void endCommand() {
        state = State.REINFORCEMENT;
        gameModel.nextPlayer();
        if (gameModel.getCurrentPlayer() != null) {
            gameModel.showCurrentPlayer();
            gameModel.updateGameViewsTurnState(state);
        }
    }

    public void historyCommand() {
        JTextArea textArea = new JTextArea(gameModel.getHistory(), 50, 70);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    public void setEmpty() {
        from = EMPTY;
        to = EMPTY;
    }

    public Boolean checkCountry(String clickedCountry) {
        if (clickedCountry.equals(EMPTY)) {
            return false;
        }
        else if (!gameModel.playerOwns(clickedCountry) && state != State.ATTACK) {
            JOptionPane.showMessageDialog(null, "Current player does not own " + clickedCountry);
            return false;
        }
        else {
            return true;
        }
    }

    public void checkSuccessfulMove() {
        if (gameModel.moveTroops(from, to)) {
            gameModel.updateState();
            state = State.REINFORCEMENT;
            gameModel.nextPlayer();
            gameModel.showCurrentPlayer();
            gameModel.updateGameViewsTurnState(state);
        }
    }

    public void checkReinforcements() {
        if (gameModel.getCurrentPlayerReinforcements() <= 0) {
            toAttackPhase();
        }
    }
    
    private void loadCommand() {
        File selectedFile = selectFile();
        if(selectedFile==null)
            return;
        GameModel model= XML.loadGame(selectedFile);
        if(model ==null){
            JOptionPane.showMessageDialog(null, "file doesn't represent a valid save file");
            return;
        }
        GameFrame gameFrame= new GameFrame("test", model);
        model.addGameView(gameFrame);
        model.loadGame();
    }
    private void saveCommand() {
        String filename = JOptionPane.showInputDialog("Name of Save File");
        XML.saveGame(filename, gameModel);
    }

    private void loadMap() {
        File selectedFile = selectFile();
        if(selectedFile==null)
            return;
        Map map = XML.mapFromXML(selectedFile);
        if (map ==null){
            JOptionPane.showMessageDialog(null, "file doesn't represent a valid map file");
            return;
        }
        gameModel.setMap(map);
    }

    /**
     * Method that prompts the user to select a XML file
     * @return the file the user selected.
     */
    private File selectFile(){
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "XML"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }

        return selectedFile;
    }

}
