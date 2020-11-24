import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 * <p>
 * This class contains all the elements of the board's GUI, such as
 * the board itself, buttons, etc. It implements GameView, and can be
 * updated accordingly using the latter's abstract methods called from
 * the Game Model.
 * <p>
 * To add: a board that we can interact with (each country has a node) and
 * whose action command is simply the country's name. Also need to clean up
 * the layout, because right now it looks bad.
 *
 * @author Team Group - Jonah Gaudet, Baillie Noell
 * @version 27-10-2020
 */

public class GameFrame extends JFrame implements GameView {

    private final ArrayList<JTextArea> playersInfo;
    private final JLabel updateLine;
    private String playerTurnInfo;
    private final ArrayList<JButton> buttons;
    private mxGraphComponent board;
    private final GameController gameController;
    private final JPanel boardPanel;
    private mxGraph graph;
    private JMenuItem showHistory;
    private JMenu gameMenu;

    public static final String VERTEX_STYLE = "shape=ellipse;whiteSpace=wrap;strokeWidth=4";
    public static final String VERTEX_STYLE_ONE_WORD = "shape=ellipse;strokeWidth=4";
    public static final int WIDTH = 65;
    public static final int HEIGHT = 50;
    public static final String EDGE_STYLE = "endArrow=false;";

    public static final String PLAYER1 = "#FF3333"; //red
    public static final String PLAYER2 = "#4AFF33"; //green
    public static final String PLAYER3 = "#336DFF"; //blue
    public static final String PLAYER4 = "#FFF533"; //yellow
    public static final String PLAYER5 = "#FF33EF"; //pink
    public static final String PLAYER6 = "#A633FF"; //purple

    public GameFrame(String name) {
        super(name);
        this.buttons = new ArrayList<>();
        this.playersInfo = new ArrayList<>();

        GameModel abm = new GameModel();
        abm.addGameView(this);
        this.gameController = new GameController(abm);
        makeMenuBar();

        JPanel mainPanel = (JPanel) this.getContentPane();

        this.updateLine = new JLabel("RISK: a multi-player game of world domination", SwingConstants.CENTER);
        updateLine.setFont(new Font(updateLine.getFont().getName(), Font.PLAIN, 20));

        JPanel middlePane = new JPanel();
        middlePane.setLayout(new GridBagLayout());

        //Put the board in this panel. For now, just a copy of the risk image
        this.boardPanel = new JPanel();
        JLabel placeholderBoard = new JLabel();
        ImageIcon image = new ImageIcon("Risk/images/riskmap.jpg");
        Image newImage = image.getImage().getScaledInstance(1000, 650, java.awt.Image.SCALE_SMOOTH);
        placeholderBoard.setIcon(new ImageIcon(newImage));
        placeholderBoard.setBounds(0, 0, 1100, 650);
        boardPanel.add(placeholderBoard);

        //Adds the boxes with the owned countries in them (right side)
        JPanel playerInfo = new JPanel();
        GridLayout layout1 = new GridLayout(3, 2);
        layout1.setVgap(5);
        layout1.setHgap(5);
        playerInfo.setLayout(layout1);
        for (int i = 0; i < layout1.getRows() * layout1.getColumns(); i++) {
            JTextArea textArea = new JTextArea(5, 20);
            textArea.setText("Reserved for player " + (i + 1));
            JScrollPane scrollPane = new JScrollPane(textArea);
            playerInfo.add(scrollPane);
            textArea.setEditable(false);
            playersInfo.add(textArea);
        }
        playerInfo.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Add the command buttons
        JPanel buttonOptions = new JPanel();
        GridLayout layout2 = new GridLayout(1, 4);
        layout2.setVgap(25);
        layout2.setHgap(25);
        buttonOptions.setLayout(layout2);
        String[] validCommands = {"Reinforcement", "Attack", "Move", "End"};
        for (String validCommand : validCommands) {
            JButton b = new JButton(validCommand);
            b.addActionListener(gameController);
            b.setActionCommand(b.getText().toLowerCase());
            b.setEnabled(false);
            buttons.add(b);
            buttonOptions.add(b);
        }

        middlePane.add(buttonOptions, getConstraints(0, 0, 3, 1, GridBagConstraints.HORIZONTAL));
        middlePane.add(boardPanel, getConstraints(0, 1, 3, 2, GridBagConstraints.HORIZONTAL));

        mainPanel.add(updateLine, BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(middlePane), BorderLayout.LINE_START);
        mainPanel.add(new JScrollPane(playerInfo), BorderLayout.LINE_END);
//        mainPanel.add(startButton, BorderLayout.PAGE_END);

        this.setContentPane(new JScrollPane(mainPanel));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(1550, 800);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void reset() {
        updateLine.setText("RISK: a multi-player game of world domination");
        JLabel placeholderBoard = new JLabel();
        ImageIcon image = new ImageIcon("Risk/images/riskmap.jpg");
        Image newImage = image.getImage().getScaledInstance(1000, 650, java.awt.Image.SCALE_SMOOTH);
        placeholderBoard.setIcon(new ImageIcon(newImage));
        placeholderBoard.setBounds(0, 0, 1100, 650);
        boardPanel.removeAll();
        boardPanel.add(placeholderBoard);

        for (int i = 0; i < playersInfo.size(); i++) {
            playersInfo.get(i).setText("Reserved for player " + (i + 1));
            playersInfo.get(i).setEnabled(false);
            playersInfo.get(i).setBackground(Color.white);
            playersInfo.get(i).setSelectedTextColor(Color.gray);
            playersInfo.get(i).setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        }

        for (JButton b : buttons) {
            b.setEnabled(false);
            b.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        }
        showHistory.setEnabled(false);
    }

    /**
     * Creates the menuBar and add it to the frame
     */
    private void makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");

        JMenuItem startGame = new JMenuItem("Start Game");
        startGame.addActionListener(gameController);
        startGame.setActionCommand("new");
        gameMenu.add(startGame);

        menuBar.add(gameMenu);

        showHistory = new JMenuItem("Show move history");
        showHistory.addActionListener(gameController);
        showHistory.setActionCommand("history");
        showHistory.setEnabled(false);
        gameMenu.add(showHistory);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(gameController);
        help.setActionCommand("help");
        helpMenu.add(help);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Should return the board (just voided now so no errors), created
     * in this method:
     * //TODO: Automation
     * @param map
     */
    private mxGraphComponent createBoard(Map map) {
        this.graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.setMaximumGraphBounds(new mxRectangle(0,0,990,600));
        graph.setMinimumGraphSize(new mxRectangle(0,0,990,600));


        graph.getModel().beginUpdate();

        try {
            int x = 0;
            int y = 0;
            HashMap<String, Object> vertices = new HashMap<>();
//            for(Country country: map.getAllCountries()){
//                x +=30;
//                y +=20;
//
//                vertices.put(country.getName(),insertVertex(country.getName(), x, y));
//            }

            for(Continent continent: map.getContinents()){
                x +=100;
                y +=100;

                for(Country country: continent.getCountries()){
                    x +=5;
                    y +=5;
                    vertices.put(country.getName(),insertVertex(country.getName(), x, y));
                }
            }
            Country country;
            for (String vertexName : vertices.keySet()) {
                country = map.getCountry(vertexName);
                for (Country neighbor : country.getNeighbors()) {
                    insertEdge(vertices.get(neighbor.getName()),vertices.get(vertexName));
                }
            }

            for(Continent continent: map.getContinents()){
                for(Country country1: continent.getCountries()){
                    graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, continent.getColor(),
                            new Object[]{vertices.get(country1.getName())});
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
        layout.setForceConstant(45);
        layout.setUseInputOrigin(false);
        layout.setInitialTemp(500);
        layout.execute(parent);

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);
        //graphComponent.setSize(1000,650);
        getContentPane().add(graphComponent);

        return graphComponent;
    }

    /**
     * Helper function for the creation of the insertion of vertices.
     *
     * @param name      the name of the vertex
     * @param xPosition .
     * @param yPosition .
     * @return the new vertex.
     */
    private Object insertVertex(String name, int xPosition, int yPosition) {
        return graph.insertVertex(graph.getDefaultParent(), name, name, xPosition, yPosition, WIDTH, HEIGHT, getVertexStyle(name));
    }

    /**
     * Creates an edge between two vertices.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    private void insertEdge(Object vertex1, Object vertex2) {
        graph.insertEdge(graph.getDefaultParent(), "", "", vertex1, vertex2, EDGE_STYLE);
    }

    private String getVertexStyle(String name){
        if(name.length()>10 && name.split(" ").length ==1){
            return VERTEX_STYLE_ONE_WORD;
        }
        return VERTEX_STYLE;
    }

    /**
     * Gets the desired colour based on the player's index
     *
     * @param playerIndex .
     * @return the colour to use as a String
     */
    private String getColorForPlayerIndex(int playerIndex) {
        return switch (playerIndex) {
            case 1 -> PLAYER2;
            case 2 -> PLAYER3;
            case 3 -> PLAYER4;
            case 4 -> PLAYER5;
            case 5 -> PLAYER6;
            default -> PLAYER1;
        };
    }

    /**
     * Handles the start of the game
     *
     * @param gameModel a GameStartEvent
     */
    public void handleGameStart(GameStartEvent gameModel) {
        ArrayList<Player> players = gameModel.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            playersInfo.get(i).setBorder(BorderFactory.createLineBorder(Color.decode(getColorForPlayerIndex(i))));
            playersInfo.get(i).setBackground(Color.white);
            playersInfo.get(i).setSelectedTextColor(Color.black);
        }

        for (int i = players.size(); i < playersInfo.size(); i++) {
            JTextArea temp = playersInfo.get(i);
            temp.setEnabled(false);
            temp.setBackground(Color.black);
            temp.setSelectedTextColor(Color.white);
        }

        for (JButton b : buttons) {
            b.setEnabled(true);
        }

        this.board = createBoard(gameModel.getMap());
        boardPanel.removeAll();
        boardPanel.add(board);
        gameController.addGameBoard(board);
        updateColors(gameModel.getPlayers());

        showHistory.setEnabled(true);
    }

    /**
     * Updates the colours of the nodes in the board
     *
     * @param players the players in the game
     */
    private void updateColors(ArrayList<Player> players) {
        mxGraph graph = board.getGraph();
        Object[] cells = graph.getChildVertices(graph.getDefaultParent());
        for (Object c : cells) {
            mxCell cell = (mxCell) c;
            String countryName = cell.getId();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).hasCountry(countryName)) {
                    String newColor = getColorForPlayerIndex(i);
                    graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, newColor, new Object[]{cell});
                    //cell.setStyle(cell.getStyle() + ";fillColor=" + newColor);
                }
            }
        }
    }

    /**
     * Returns a GridBagConstraints, avoids duplicated code and only takes in the field that
     * may vary within this frame
     *
     * @param gridX      gridX value
     * @param gridY      gridY value
     * @param gridWidth  gridWidth value
     * @param gridHeight gridHeight value
     * @param fill       fill value
     * @return a GridBagConstraints to use
     */
    public GridBagConstraints getConstraints(int gridX, int gridY, int gridWidth, int gridHeight, int fill) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridX;
        c.gridy = gridY;
        c.gridwidth = gridWidth;
        c.gridheight = gridHeight;
        c.fill = fill;
        c.insets = new Insets(5, 5, 5, 5);
        return c;
    }

    /**
     * Updates the player who's turn it it, changes GUI accordingly
     *
     * @param playerTurn a PlayerTurnEvent
     */
    public void handlePlayerTurnUpdate(PlayerTurnEvent playerTurn) {
        playerTurnInfo = "It is " + playerTurn.getName() + "'s turn. ";
        updateLine.setText(playerTurnInfo);
        for (int i = 0; i < playersInfo.size(); i++) {
            if (i == playerTurn.getOrder()) {
                playersInfo.get(i).setBackground(Color.white);
            } else if (playersInfo.get(i).isEnabled()) {
                playersInfo.get(i).setBackground(Color.lightGray);
            }
        }
    }

    /**
     * Updates the state of the current player's turn, updates the label
     *
     * @param turnState a TurnStateEvent
     */
    public void handleTurnStateChange(TurnStateEvent turnState) {
        for (JButton b : buttons) {
            b.setEnabled(false);
            b.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        }

        switch (turnState.getNewState().toLowerCase()) {
            case "reinforcement" -> {
                buttons.get(0).setBorder(BorderFactory.createLineBorder(Color.decode("#00ff00")));
                updateLine.setText(playerTurnInfo + " Click on a country to add reinforcements. " +
                        gameController.getCurrentReinforcements() + " remain.");
            }
            case "attack" -> {
                buttons.get(1).setBorder(BorderFactory.createLineBorder(Color.decode("#00ff00")));
                buttons.get(2).setEnabled(true);
                buttons.get(3).setEnabled(true);
                updateLine.setText(playerTurnInfo + " Select a country to attack with, then the country to attack");
            }
            case "move" -> {
                buttons.get(2).setBorder(BorderFactory.createLineBorder(Color.decode("#00ff00")));
                buttons.get(3).setEnabled(true);
                updateLine.setText(playerTurnInfo + " Select a country to move troops from, and " +
                        "a country to move troops to (1 allowed)");
            }
        }
    }

    /**
     * Handles the complete reset of the view concerning the player info text areas
     */
    @Override
    public void handleResetView() {
        for (int i = 0; i < playersInfo.size(); i++) {
            playersInfo.get(i).setText("Reserved for player " + (i + 1));
            playersInfo.get(i).setEnabled(false);
            playersInfo.get(i).setBackground(Color.black);
            playersInfo.get(i).setSelectedTextColor(Color.white);
        }
    }

    /**
     * Handles the elimination of a player
     *
     * @param eliminatedEvent a PlayerEliminatedEvent
     */
    @Override
    public void handlePlayerElimination(PlayerEliminatedEvent eliminatedEvent) {
        int index = eliminatedEvent.getPlayerNumber();
        playersInfo.get(index).setText(eliminatedEvent.getEliminated().getName() + " is eliminated");
        playersInfo.get(index).setEnabled(false);
        playersInfo.get(index).setBackground(Color.black);
        playersInfo.get(index).setSelectedTextColor(Color.white);

    }

    /**
     * Handles the end of a game
     *
     * @param gameOverEvent a GameOverEvent
     */
    @Override
    public void handleGameOver(GameOverEvent gameOverEvent) {
        updateLine.setText(gameOverEvent.getWinner().getName() + " won the Game");

        for (JButton b : buttons) {
            b.setEnabled(false);
        }
    }

    /**
     * Updates the state a player by their order
     *
     * @param playerState a PlayerStateEvent
     */
    public void handleStateUpdate(PlayerStateEvent playerState) {
        playersInfo.get(playerState.getOrder()).setText(playerState.getInfo());
    }

    /**
     * Updates the owner (a player) of a country with a certain ID
     *
     * @param ownerChange a OwnerChangeEvent
     */
    public void handleOwnerChange(OwnerChangeEvent ownerChange) {
        mxGraph graph = board.getGraph();
        Object[] cells = graph.getChildVertices(graph.getDefaultParent());
        for (Object c : cells) {
            mxCell cell = (mxCell) c;
            if (cell.getId().equals(ownerChange.getCountryName())) {
                graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, getColorForPlayerIndex(ownerChange.getOrder()), new Object[]{cell});
            }
        }
    }

    public static void main(String[] args) {
        File f = new File("Risk/images/images.jpg");
        System.out.println(f.exists());
        new GameFrame("The game of RISK");
    }
}
