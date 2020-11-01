import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This class contains all the elements of the board's GUI, such as
 * the board itself, buttons, etc. It implements GameView, and can be
 * updated accordingly using the latter's abstract methods called from
 * the Game Model.
 *
 * To add: a board that we can interact with (each country has a node) and
 * whose action command is simply the country's name. Also need to clean up
 * the layout, because right now it looks bAD.
 *
 * @version 27-10-2020
 * @author Team Group - Jonah Gaudet, Baillie Noell
 */

public class GameFrame extends JFrame implements GameView{

    public ArrayList<JTextArea> playersInfo;
    public JLabel updateLine;
    public ArrayList<JButton> buttons;
    public JPanel boardPanel;
    public GameFrame (String name) {
        super(name);
        this.buttons = new ArrayList<JButton>();
        this.playersInfo = new ArrayList<JTextArea>();

        GameModel abm = new GameModel();
        abm.addGameView(this);
        GameController gameController = new GameController(abm);

        JPanel mainPanel = (JPanel) this.getContentPane();

        this.updateLine = new JLabel("RISK: a multi-player game of world domination");

        JPanel middlePane = new JPanel();
        middlePane.setLayout(new GridBagLayout());

        //Put the board in this panel (feel free to get rid of the image, just a placeholder)
        this.boardPanel = new JPanel();


        //Adds the image of the risk map
        JLabel placeholderBoard = new JLabel();
        ImageIcon image = new ImageIcon("Risk/images/riskmap.jpg");
        Image newImage = image.getImage().getScaledInstance(750, 500,  java.awt.Image.SCALE_SMOOTH);
        placeholderBoard.setIcon(new ImageIcon(newImage));
        placeholderBoard.setBounds(0, 0, 1000, 650);
        boardPanel.add(placeholderBoard);

        //Adds the boxes with the owned countries in them (right side)
        JPanel playerInfo = new JPanel();
        GridLayout layout1 = new GridLayout(3,2);
        layout1.setVgap(5);
        layout1.setHgap(5);
        playerInfo.setLayout(layout1);
        for (int i = 0; i < layout1.getRows() * layout1.getColumns(); i++) {
            JTextArea textArea = new JTextArea(5, 20);
            textArea.setText("Reserved for player " + Integer.toString(i+1));
            JScrollPane scrollPane = new JScrollPane(textArea);
            playerInfo.add(scrollPane);
            textArea.setEditable(false);
            playersInfo.add(textArea);
        }

        //Add the command buttons
        JPanel buttonOptions = new JPanel();
        GridLayout layout2 = new GridLayout(1,4);
        layout2.setVgap(25);
        layout2.setHgap(25);
        buttonOptions.setLayout(layout2);
        String[] validCommands = {"attack", "state",  "help",  "end"};
        for (int i = 0; i < 4; i++) {
            JButton b = new JButton(validCommands[i]);
            b.addActionListener(gameController);
            b.setActionCommand(b.getText());
            b.setEnabled(false);
            buttons.add(b);
            buttonOptions.add(b);
        }

        //Button to start the game
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(gameController);
        startButton.setActionCommand("new");

        middlePane.add(buttonOptions, getConstraints(0, 0, 2, 1, GridBagConstraints.HORIZONTAL));
        middlePane.add(boardPanel, getConstraints(0, 1, 2, 2, GridBagConstraints.HORIZONTAL));
        middlePane.add(playerInfo, getConstraints(2, 0, 1, 3, GridBagConstraints.VERTICAL));

        mainPanel.add(updateLine, BorderLayout.PAGE_START);
        mainPanel.add(middlePane, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(1300,650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Should return the board (just voided now so no errors), created
     * in this method:
     */
    private mxGraphComponent createBoard () {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try
        {
            //North America
            Object alaska = graph.insertVertex(parent, Map.ALASKA, Map.ALASKA, 20, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object alberta = graph.insertVertex(parent, Map.ALBERTA, Map.ALBERTA, 45, 105, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object centralAmerica = graph.insertVertex(parent, Map.CENTRAL_AMERICA, Map.CENTRAL_AMERICA, 125, 260, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object easternUnitedStates = graph.insertVertex(parent, Map.EASTERN_UNITED_STATES, Map.EASTERN_UNITED_STATES, 175, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object greenland = graph.insertVertex(parent, Map.GREENLAND, Map.GREENLAND, 250, 10, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object northwestTerritory = graph.insertVertex(parent, Map.NORTHWEST_TERRITORY, Map.NORTHWEST_TERRITORY, 120, 25, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ontario = graph.insertVertex(parent, Map.ONTARIO, Map.ONTARIO, 125, 110, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object quebec = graph.insertVertex(parent, Map.QUEBEC, Map.QUEBEC, 210, 100, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernUnitedStates = graph.insertVertex(parent, Map.WESTERN_UNITED_STATES, Map.WESTERN_UNITED_STATES, 75, 185, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "", "", alaska, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "", "", alaska, alberta, "endArrow=false");
            graph.insertEdge(parent, "", "", alberta, ontario, "endArrow=false");
            graph.insertEdge(parent, "", "", alberta, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "", "", greenland, northwestTerritory, "endArrow=false");
            graph.insertEdge(parent, "", "", northwestTerritory, alberta, "endArrow=false");
            graph.insertEdge(parent, "", "", greenland, ontario, "endArrow=false");
            graph.insertEdge(parent, "", "", greenland, quebec, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", ontario, quebec, "endArrow=false");
            graph.insertEdge(parent, "", "", quebec, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", centralAmerica, easternUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", centralAmerica, westernUnitedStates, "endArrow=false");
            graph.insertEdge(parent, "", "", easternUnitedStates, westernUnitedStates, "endArrow=false");

            //South America
            Object argentina = graph.insertVertex(parent, Map.ARGENTINA, Map.ARGENTINA, 145, 540, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object brazil = graph.insertVertex(parent, Map.BRAZIL, Map.BRAZIL, 200, 460, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object peru = graph.insertVertex(parent, Map.PERU, Map.PERU, 90, 460, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object venezuela = graph.insertVertex(parent, Map.VENEZUELA, Map.VENEZUELA, 145, 380, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",venezuela, centralAmerica, "endArrow=false");
            graph.insertEdge(parent, "","",venezuela, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",venezuela, peru, "endArrow=false");
            graph.insertEdge(parent, "","",argentina, peru, "endArrow=false");
            graph.insertEdge(parent, "","",argentina, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",brazil, peru, "endArrow=false");

            //Europe
            Object greatBritain = graph.insertVertex(parent, Map.GREAT_BRITAIN, Map.GREAT_BRITAIN, 340, 120, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object iceland = graph.insertVertex(parent, Map.ICELAND, Map.ICELAND, 360, 35, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object northernEurope = graph.insertVertex(parent, Map.NORTHERN_EUROPE, Map.NORTHERN_EUROPE, 430, 135, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object scandinavia = graph.insertVertex(parent, Map.SCANDINAVIA, Map.SCANDINAVIA, 450, 55, 65, 65, "shape=ellipse");
            Object southernEurope = graph.insertVertex(parent, Map.SOUTHERN_EUROPE, Map.SOUTHERN_EUROPE, 430, 225, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ukraine = graph.insertVertex(parent, Map.UKRAINE, Map.UKRAINE, 520, 130, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernEurope = graph.insertVertex(parent, Map.WESTERN_EUROPE, Map.WESTERN_EUROPE, 345, 205, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",iceland, greenland, "endArrow=false");
            graph.insertEdge(parent, "","",iceland, greatBritain, "endArrow=false");
            graph.insertEdge(parent, "","",iceland, scandinavia, "endArrow=false");
            graph.insertEdge(parent, "","",greatBritain, scandinavia, "endArrow=false");
            graph.insertEdge(parent, "","",greatBritain, northernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",scandinavia, northernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",scandinavia, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",northernEurope, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",northernEurope, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",northernEurope, westernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",westernEurope, greatBritain, "endArrow=false");
            graph.insertEdge(parent, "","",westernEurope,northernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",westernEurope, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",ukraine, southernEurope, "endArrow=false");

            //Africa
            Object congo = graph.insertVertex(parent, Map.CONGO, Map.CONGO, 380, 405, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object eastAfrica = graph.insertVertex(parent, Map.EAST_AFRICA, Map.EAST_AFRICA, 460, 415, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object egypt = graph.insertVertex(parent, Map.EGYPT, Map.EGYPT, 430, 340, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object madagascar = graph.insertVertex(parent, Map.MADAGASCAR, Map.MADAGASCAR, 490, 490, 65, 65, "shape=ellipse");
            Object northAfrica = graph.insertVertex(parent, Map.NORTH_AFRICA, Map.NORTH_AFRICA, 350, 330, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object southAfrica = graph.insertVertex(parent, Map.SOUTH_AFRICA, Map.SOUTH_AFRICA, 410, 490, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",northAfrica, westernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, brazil, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, egypt, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, eastAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",northAfrica, congo, "endArrow=false");
            graph.insertEdge(parent, "","",egypt, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",egypt, eastAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",eastAfrica, congo, "endArrow=false");
            graph.insertEdge(parent, "","",eastAfrica, madagascar, "endArrow=false");
            graph.insertEdge(parent, "","",congo, southAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",southAfrica, madagascar, "endArrow=false");
            graph.insertEdge(parent, "","",southAfrica, eastAfrica, "endArrow=false");

            //Asia
            Object afghanistan = graph.insertVertex(parent, Map.AFGHANISTAN, Map.AFGHANISTAN, 630, 110, 65, 65, "shape=ellipse");
            Object china = graph.insertVertex(parent, Map.CHINA, Map.CHINA, 715, 120, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object india = graph.insertVertex(parent, Map.INDIA, Map.INDIA, 680, 200, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object irkutsk = graph.insertVertex(parent, Map.IRKUTSK, Map.IRKUTSK, 820, 100, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object japan = graph.insertVertex(parent, Map.JAPAN, Map.JAPAN, 910, 120, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object kamchatka = graph.insertVertex(parent, Map.KAMCHATKA, Map.KAMCHATKA, 890, 20, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object middleEast = graph.insertVertex(parent, Map.MIDDLE_EAST, Map.MIDDLE_EAST, 600, 180, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object mongolia = graph.insertVertex(parent, Map.MONGOLIA, Map.MONGOLIA, 860, 200, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object siam = graph.insertVertex(parent, Map.SIAM, Map.SIAM, 770, 220, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object siberia = graph.insertVertex(parent, Map.SIBERIA, Map.SIBERIA, 715, 40, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object ural = graph.insertVertex(parent, Map.URAL, Map.URAL, 630, 30, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object yakutsk = graph.insertVertex(parent, Map.YAKUTSK, Map.YAKUTSK, 800, 30, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",afghanistan, middleEast, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, china, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, india, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, ural, "endArrow=false");
            graph.insertEdge(parent, "","",afghanistan, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",china, india, "endArrow=false");
            graph.insertEdge(parent, "","",china, siam, "endArrow=false");
            graph.insertEdge(parent, "","",china, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",china, siberia, "endArrow=false");
            graph.insertEdge(parent, "","",china, ural, "endArrow=false");
            graph.insertEdge(parent, "","",india, middleEast, "endArrow=false");
            graph.insertEdge(parent, "","",india, siam, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, kamchatka, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, siberia, "endArrow=false");
            graph.insertEdge(parent, "","",irkutsk, yakutsk, "endArrow=false");
            graph.insertEdge(parent, "","",japan, kamchatka, "endArrow=false");
            graph.insertEdge(parent, "","",japan, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",kamchatka, yakutsk, "endArrow=false");
            graph.insertEdge(parent, "","",kamchatka, mongolia, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, ukraine, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, southernEurope, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, egypt, "endArrow=false");
            graph.insertEdge(parent, "","",middleEast, eastAfrica, "endArrow=false");
            graph.insertEdge(parent, "","",mongolia, siberia, "endArrow=false");
            graph.insertEdge(parent, "","",siberia, ural, "endArrow=false");
            graph.insertEdge(parent, "","",siberia, yakutsk, "endArrow=false");

            //Australia
            Object easternAustralia = graph.insertVertex(parent, Map.EASTERN_AUSTRALIA, Map.EASTERN_AUSTRALIA, 850, 480, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object indonesia = graph.insertVertex(parent, Map.INDONESIA, Map.INDONESIA, 780, 360, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object newGuinea = graph.insertVertex(parent, Map.NEW_GUINEA, Map.NEW_GUINEA, 870, 390, 65, 65, "shape=ellipse;whiteSpace=wrap");
            Object westernAustralia = graph.insertVertex(parent, Map.WESTERN_AUSTRALIA, Map.WESTERN_AUSTRALIA, 760, 450, 65, 65, "shape=ellipse;whiteSpace=wrap");

            graph.insertEdge(parent, "","",easternAustralia, westernAustralia, "endArrow=false");
            graph.insertEdge(parent, "","",easternAustralia, newGuinea, "endArrow=false");
            graph.insertEdge(parent, "","",indonesia, siam, "endArrow=false");
            graph.insertEdge(parent, "","",indonesia, newGuinea, "endArrow=false");
            graph.insertEdge(parent, "","",indonesia, westernAustralia, "endArrow=false");
            graph.insertEdge(parent, "","",westernAustralia, newGuinea, "endArrow=false");

            //placeholders
            Object placeholder1 = graph.insertVertex(parent, "", "", -20, 50, 1, 1, "shape=ellipse;whiteSpace=wrap");
            Object placeholder2 = graph.insertVertex(parent, "", "", 970, 45, 1, 1, "shape=ellipse;whiteSpace=wrap");
            graph.insertEdge(parent, "","",alaska, placeholder1, "endArrow=false");
            graph.insertEdge(parent, "","",kamchatka, placeholder2, "endArrow=false");
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);

        return graphComponent;
    }

    public void handleGameStart(GameStartEvent gameModel) {
        ArrayList<Player> players = gameModel.getPlayers();
        Map map = gameModel.getMap();

        for (int i = players.size(); i < playersInfo.size(); i++) {
            JTextArea temp = playersInfo.get(i);
            temp.setEnabled(false);
            temp.setBackground(Color.black);
            temp.setSelectedTextColor(Color.white);
        }

        for (JButton b : buttons) {
            b.setEnabled(true);
        }

        createBoard();
    }

    public GridBagConstraints getConstraints (int gridx, int gridy, int gridwidth, int gridheight, int fill) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.fill = fill;
        c.insets = new Insets(5,5,5,5);
        return c;
    }

    public void handlePlayerTurnUpdate(PlayerTurnEvent playerTurn) {
        updateLine.setText("It is " + playerTurn.getName() + "'s turn: ");
        for (int i = 0; i < playersInfo.size(); i++) {
            if (i == playerTurn.getOrder()) {
                playersInfo.get(i).setBorder(BorderFactory.createLineBorder(Color.green));
                playersInfo.get(i).setBackground(Color.white);
            }
            else if (playersInfo.get(i).isEnabled()){
                playersInfo.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
                playersInfo.get(i).setBackground(Color.lightGray);
            }
        }
    }

    public void handleStateUpdate(PlayerStateEvent playerState) {
        playersInfo.get(playerState.getOrder()).setText(playerState.getInfo());
    }

    public static void  main(String[] args){
        File f = new File("Risk/images/images.jpg");
        System.out.println(f.exists());
        GameFrame gFrame = new GameFrame("The game of RISK");
    }
}
