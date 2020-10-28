import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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
 * @author Team Group - Jonah Gaudet
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
        placeholderBoard.setBounds(0, 0, 1100, 600);
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
    private void createBoard (Map map, ArrayList<Player> players) {
        //get the board
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

        createBoard(map, players);
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
