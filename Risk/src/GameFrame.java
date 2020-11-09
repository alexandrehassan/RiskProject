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
import java.util.LinkedList;

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

    public static final String VERTEX_STYLE = "shape=ellipse;whiteSpace=wrap;strokeWidth=4";
    public static final String VERTEX_STYLE_ONE_WORD = "shape=ellipse;strokeWidth=4";
    public static final String NA_COLOR = "strokeColor=#ff9000";
    public static final String SA_COLOR = "strokeColor=#ffd000";
    public static final String EU_COLOR = "strokeColor=#ff6500";
    public static final String AF_COLOR = "strokeColor=#ffa500";
    public static final String AS_COLOR = "strokeColor=#ffba00";
    public static final String AU_COLOR = "strokeColor=#ff7b00";
    public static final String BLACK = "strokeColor=#000000";
    public static final int WIDTH = 65;
    public static final int HEIGHT = 65;
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

        //Button to start the game
//        this.startButton = new JButton("Start Game");
//        startButton.addActionListener(gameController);
//        startButton.setActionCommand("new");

        middlePane.add(buttonOptions, getConstraints(0, 0, 3, 1, GridBagConstraints.HORIZONTAL));
        middlePane.add(boardPanel, getConstraints(0, 1, 3, 2, GridBagConstraints.HORIZONTAL));

        mainPanel.add(updateLine, BorderLayout.PAGE_START);
        mainPanel.add(middlePane, BorderLayout.LINE_START);
        mainPanel.add(playerInfo, BorderLayout.LINE_END);
//        mainPanel.add(startButton, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(1550, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem startGame = new JMenuItem("Start Game");
        startGame.addActionListener(gameController);
        startGame.setActionCommand("new");
        gameMenu.add(startGame);

        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Should return the board (just voided now so no errors), created
     * in this method:
     * //TODO: Automation
     */
    private mxGraphComponent createBoard() {
        this.graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.setMaximumGraphBounds(new mxRectangle(0, 0, 1000, 600));

        graph.getModel().beginUpdate();

        try {
            //North America
            Object alaska = insertVertex(Map.ALASKA, 20, 20, VERTEX_STYLE, NA_COLOR);
            Object alberta = insertVertex(Map.ALBERTA, 45, 105, VERTEX_STYLE, NA_COLOR);
            Object centralAmerica = insertVertex(Map.CENTRAL_AMERICA, 125, 260, VERTEX_STYLE, NA_COLOR);
            Object easternUnitedStates = insertVertex(Map.EASTERN_UNITED_STATES, 175, 185, VERTEX_STYLE, NA_COLOR);
            Object greenland = insertVertex(Map.GREENLAND, 250, 10, VERTEX_STYLE, NA_COLOR);
            Object northwestTerritory = insertVertex(Map.NORTHWEST_TERRITORY, 120, 25, VERTEX_STYLE, NA_COLOR);
            Object ontario = insertVertex(Map.ONTARIO, 125, 110, VERTEX_STYLE, NA_COLOR);
            Object quebec = insertVertex(Map.QUEBEC, 210, 100, VERTEX_STYLE, NA_COLOR);
            Object westernUnitedStates = insertVertex(Map.WESTERN_UNITED_STATES, 75, 185, VERTEX_STYLE, NA_COLOR);

            //South America
            Object argentina = insertVertex(Map.ARGENTINA, 145, 540, VERTEX_STYLE, SA_COLOR);
            Object brazil = insertVertex(Map.BRAZIL, 200, 460, VERTEX_STYLE, SA_COLOR);
            Object peru = insertVertex(Map.PERU, 90, 460, VERTEX_STYLE, SA_COLOR);
            Object venezuela = insertVertex(Map.VENEZUELA, 145, 380, VERTEX_STYLE, SA_COLOR);

            //Europe
            Object greatBritain = insertVertex(Map.GREAT_BRITAIN, 340, 120, VERTEX_STYLE, EU_COLOR);
            Object iceland = insertVertex(Map.ICELAND, 360, 35, VERTEX_STYLE, EU_COLOR);
            Object northernEurope = insertVertex(Map.NORTHERN_EUROPE, 430, 135, VERTEX_STYLE, EU_COLOR);
            Object scandinavia = insertVertex(Map.SCANDINAVIA, 450, 55, VERTEX_STYLE_ONE_WORD, EU_COLOR);
            Object southernEurope = insertVertex(Map.SOUTHERN_EUROPE, 430, 225, VERTEX_STYLE, EU_COLOR);
            Object ukraine = insertVertex(Map.UKRAINE, 520, 130, VERTEX_STYLE, EU_COLOR);
            Object westernEurope = insertVertex(Map.WESTERN_EUROPE, 345, 205, VERTEX_STYLE, EU_COLOR);

            //Africa
            Object congo = insertVertex(Map.CONGO, 380, 405, VERTEX_STYLE, AF_COLOR);
            Object eastAfrica = insertVertex(Map.EAST_AFRICA, 460, 415, VERTEX_STYLE, AF_COLOR);
            Object egypt = insertVertex(Map.EGYPT, 430, 340, VERTEX_STYLE, AF_COLOR);
            Object madagascar = insertVertex(Map.MADAGASCAR, 490, 490, VERTEX_STYLE_ONE_WORD, AF_COLOR);
            Object northAfrica = insertVertex(Map.NORTH_AFRICA, 350, 330, VERTEX_STYLE, AF_COLOR);
            Object southAfrica = insertVertex(Map.SOUTH_AFRICA, 410, 490, VERTEX_STYLE, AF_COLOR);

            //Asia
            Object afghanistan = insertVertex(Map.AFGHANISTAN, 630, 110, VERTEX_STYLE_ONE_WORD, AS_COLOR);
            Object china = insertVertex(Map.CHINA, 715, 120, VERTEX_STYLE, AS_COLOR);
            Object india = insertVertex(Map.INDIA, 680, 200, VERTEX_STYLE, AS_COLOR);
            Object irkutsk = insertVertex(Map.IRKUTSK, 820, 100, VERTEX_STYLE, AS_COLOR);
            Object japan = insertVertex(Map.JAPAN, 910, 120, VERTEX_STYLE, AS_COLOR);
            Object kamchatka = insertVertex(Map.KAMCHATKA, 890, 20, VERTEX_STYLE, AS_COLOR);
            Object middleEast = insertVertex(Map.MIDDLE_EAST, 600, 180, VERTEX_STYLE, AS_COLOR);
            Object mongolia = insertVertex(Map.MONGOLIA, 860, 200, VERTEX_STYLE, AS_COLOR);
            Object siam = insertVertex(Map.SIAM, 770, 220, VERTEX_STYLE, AS_COLOR);
            Object siberia = insertVertex(Map.SIBERIA, 715, 40, VERTEX_STYLE, AS_COLOR);
            Object ural = insertVertex(Map.URAL, 630, 30, VERTEX_STYLE, AS_COLOR);
            Object yakutsk = insertVertex(Map.YAKUTSK, 800, 30, VERTEX_STYLE, AS_COLOR);

            //Australia
            Object easternAustralia = insertVertex(Map.EASTERN_AUSTRALIA, 850, 480, VERTEX_STYLE, AU_COLOR);
            Object indonesia = insertVertex(Map.INDONESIA, 780, 360, VERTEX_STYLE, AU_COLOR);
            Object newGuinea = insertVertex(Map.NEW_GUINEA, 870, 390, VERTEX_STYLE, AU_COLOR);
            Object westernAustralia = insertVertex(Map.WESTERN_AUSTRALIA, 760, 450, VERTEX_STYLE, AU_COLOR);

            insertEdge(alaska, northwestTerritory, NA_COLOR);
            insertEdge(alaska, alberta, NA_COLOR);
            insertEdge(alberta, ontario, NA_COLOR);
            insertEdge(alberta, westernUnitedStates, NA_COLOR);
            insertEdge(ontario, northwestTerritory, NA_COLOR);
            insertEdge(greenland, northwestTerritory, NA_COLOR);
            insertEdge(northwestTerritory, alberta, NA_COLOR);
            insertEdge(greenland, ontario, NA_COLOR);
            insertEdge(greenland, quebec, NA_COLOR);
            insertEdge(ontario, easternUnitedStates, NA_COLOR);
            insertEdge(ontario, westernUnitedStates, NA_COLOR);
            insertEdge(ontario, quebec, NA_COLOR);
            insertEdge(quebec, easternUnitedStates, NA_COLOR);
            insertEdge(centralAmerica, easternUnitedStates, NA_COLOR);
            insertEdge(centralAmerica, westernUnitedStates, NA_COLOR);
            insertEdge(easternUnitedStates, westernUnitedStates, NA_COLOR);

            insertEdge(venezuela, centralAmerica, BLACK);
            insertEdge(venezuela, brazil, SA_COLOR);
            insertEdge(venezuela, peru, SA_COLOR);
            insertEdge(argentina, peru, SA_COLOR);
            insertEdge(argentina, brazil, SA_COLOR);
            insertEdge(brazil, peru, SA_COLOR);

            insertEdge(iceland, greenland, BLACK);
            insertEdge(iceland, greatBritain, EU_COLOR);
            insertEdge(iceland, scandinavia, EU_COLOR);
            insertEdge(greatBritain, scandinavia, EU_COLOR);
            insertEdge(greatBritain, northernEurope, EU_COLOR);
            insertEdge(scandinavia, northernEurope, EU_COLOR);
            insertEdge(scandinavia, ukraine, EU_COLOR);
            insertEdge(northernEurope, ukraine, EU_COLOR);
            insertEdge(northernEurope, southernEurope, EU_COLOR);
            insertEdge(northernEurope, westernEurope, EU_COLOR);
            insertEdge(westernEurope, greatBritain, EU_COLOR);
            insertEdge(westernEurope, northernEurope, EU_COLOR);
            insertEdge(westernEurope, southernEurope, EU_COLOR);
            insertEdge(ukraine, southernEurope, EU_COLOR);


            insertEdge(northAfrica, westernEurope, BLACK);
            insertEdge(northAfrica, brazil, BLACK);
            insertEdge(northAfrica, egypt, AF_COLOR);
            insertEdge(northAfrica, eastAfrica, AF_COLOR);
            insertEdge(northAfrica, congo, AF_COLOR);
            insertEdge(egypt, southernEurope, BLACK);
            insertEdge(egypt, eastAfrica, AF_COLOR);
            insertEdge(eastAfrica, congo, AF_COLOR);
            insertEdge(eastAfrica, madagascar, AF_COLOR);
            insertEdge(congo, southAfrica, AF_COLOR);
            insertEdge(southAfrica, madagascar, AF_COLOR);
            insertEdge(southAfrica, eastAfrica, AF_COLOR);


            insertEdge(afghanistan, middleEast, AS_COLOR);
            insertEdge(afghanistan, china, AS_COLOR);
            insertEdge(afghanistan, india, AS_COLOR);
            insertEdge(afghanistan, ural, AS_COLOR);
            insertEdge(afghanistan, ukraine, BLACK);
            insertEdge(china, india, AS_COLOR);
            insertEdge(china, siam, AS_COLOR);
            insertEdge(china, mongolia, AS_COLOR);
            insertEdge(china, siberia, AS_COLOR);
            insertEdge(china, ural, AS_COLOR);
            insertEdge(india, middleEast, AS_COLOR);
            insertEdge(india, siam, AS_COLOR);
            insertEdge(irkutsk, kamchatka, AS_COLOR);
            insertEdge(irkutsk, mongolia, AS_COLOR);
            insertEdge(irkutsk, siberia, AS_COLOR);
            insertEdge(irkutsk, yakutsk, AS_COLOR);
            insertEdge(japan, kamchatka, AS_COLOR);
            insertEdge(japan, mongolia, AS_COLOR);
            insertEdge(kamchatka, yakutsk, AS_COLOR);
            insertEdge(kamchatka, mongolia, AS_COLOR);
            insertEdge(middleEast, ukraine, BLACK);
            insertEdge(middleEast, southernEurope, BLACK);
            insertEdge(middleEast, egypt, BLACK);
            insertEdge(middleEast, eastAfrica, BLACK);
            insertEdge(mongolia, siberia, AS_COLOR);
            insertEdge(siberia, ural, AS_COLOR);
            insertEdge(siberia, yakutsk, AS_COLOR);
            insertEdge(ural, ukraine, BLACK);


            insertEdge(easternAustralia, westernAustralia, AU_COLOR);
            insertEdge(easternAustralia, newGuinea, AU_COLOR);
            insertEdge(indonesia, siam, BLACK);
            insertEdge(indonesia, newGuinea, AU_COLOR);
            insertEdge(indonesia, westernAustralia, AU_COLOR);
            insertEdge(westernAustralia, newGuinea, AU_COLOR);

            //placeholders
            Object placeholder1 = graph.insertVertex(parent, "placeholder1", "", -20, 50, 1, 1, VERTEX_STYLE);
            Object placeholder2 = graph.insertVertex(parent, "placeholder2", "", 970, 45, 1, 1, VERTEX_STYLE);
            insertEdge(alaska, placeholder1, BLACK);
            insertEdge(kamchatka, placeholder2, BLACK);
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);

        return graphComponent;
    }

    /**
     * Helper function for the creation of the insertion of vertices.
     *
     * @param name      the name of the vertex
     * @param xPosition .
     * @param yPosition .
     * @param style     .
     * @param color     .
     * @return the new vertex.
     */
    private Object insertVertex(String name, int xPosition, int yPosition, String style, String color) {
        return graph.insertVertex(graph.getDefaultParent(), name, name, xPosition, yPosition, WIDTH, HEIGHT, style + ";" + color);
    }

    /**
     * Creates an edge between two vertices.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param color   the color of the edge.
     */
    private void insertEdge(Object vertex1, Object vertex2, String color) {
        graph.insertEdge(graph.getDefaultParent(), "", "", vertex1, vertex2, EDGE_STYLE + color);
    }

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

        this.board = createBoard();
        boardPanel.removeAll();
        boardPanel.add(board);
        gameController.addGameBoard(board);
        updateColors(gameModel.getPlayers());
    }

    private void updateColors(ArrayList<Player> players) {
        mxGraph graph = board.getGraph();
        Object[] cells = graph.getChildVertices(graph.getDefaultParent());
        for (Object c : cells) {
            mxCell cell = (mxCell) c;
            String countryName = cell.getId();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).hasCountry(countryName)) {
                    String newColor = getColorForPlayerIndex(i);
                    System.out.println("Successfully found " + countryName + ", player ID = " + i + " with color " + newColor);
                    graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, newColor, new Object[]{cell});
                    //cell.setStyle(cell.getStyle() + ";fillColor=" + newColor);
                }
            }
        }
    }

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

    @Override
    public void handleResetView() {
        for (int i = 0; i < playersInfo.size(); i++) {
            playersInfo.get(i).setText("Reserved for player " + (i + 1));
            playersInfo.get(i).setEnabled(false);
            playersInfo.get(i).setBackground(Color.black);
            playersInfo.get(i).setSelectedTextColor(Color.white);
        }
    }

    @Override
    public void handlePlayerElimination(PlayerEliminatedEvent eliminatedEvent) {
        int index = eliminatedEvent.getPlayerNumber();
        playersInfo.get(index).setText(eliminatedEvent.getEliminated().getName() + " is eliminated");
        playersInfo.get(index).setEnabled(false);
        playersInfo.get(index).setBackground(Color.black);
        playersInfo.get(index).setSelectedTextColor(Color.white);

    }

    @Override
    public void handleGameOver(GameOverEvent gameOverEvent) {
        JOptionPane.showMessageDialog(this, gameOverEvent.getWinner().getName() + " won the Game");
    }

    public void handleStateUpdate(PlayerStateEvent playerState) {
        playersInfo.get(playerState.getOrder()).setText(playerState.getInfo());
    }

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
