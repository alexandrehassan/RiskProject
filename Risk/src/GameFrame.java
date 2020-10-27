import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameFrame extends JFrame implements GameView{

    public ArrayList<JTextArea> playersInfo;
    public JLabel updateLine;
    public GameFrame (String name) {
        super(name);

        this.playersInfo = new ArrayList<JTextArea>();

        GameModel abm = new GameModel();
        abm.addGameView(this);
        GameController gameController = new GameController(abm);

        JPanel panel1 = (JPanel) this.getContentPane();

        panel1.add(new JLabel("RISK: a multi-player game of world domination"), BorderLayout.PAGE_START);
        this.updateLine = new JLabel("This is the update line");
        panel1.add(updateLine, BorderLayout.PAGE_START);

        JLabel label1 = new JLabel();
        ImageIcon image = new ImageIcon("Risk/images/riskmap.jpg");
        Image newImage = image.getImage().getScaledInstance(750, 500,  java.awt.Image.SCALE_SMOOTH);
        label1.setIcon(new ImageIcon(newImage));
        label1.setBounds(0, 0, 1100, 600);
        panel1.add(label1, BorderLayout.LINE_START);

        JPanel playerInfo = new JPanel();

        GridLayout layout1 = new GridLayout(3,2);
        layout1.setVgap(5);
        layout1.setHgap(5);
        playerInfo.setLayout(layout1);
        for (int i = 0; i < 6; i++) {
            JTextArea textArea = new JTextArea(5, 20);
            textArea.setText("This is box " + Integer.toString(i));
            textArea.setMaximumSize(new Dimension(100,100));
            JScrollPane scrollPane = new JScrollPane(textArea);
            playerInfo.add(scrollPane);
            textArea.setEditable(false);
            playersInfo.add(textArea);
        }
        panel1.add(playerInfo, BorderLayout.LINE_END);

        JPanel buttonOptions = new JPanel();
        GridLayout layout2 = new GridLayout(4,1);
        layout2.setVgap(25);
        layout2.setHgap(25);
        buttonOptions.setLayout(layout2);
        String[] validCommands = {"attack", "state",  "help",  "end"};
        for (int i = 0; i < 4; i++) {
            JButton b = new JButton(validCommands[i]);
            b.addActionListener(gameController);
            b.setActionCommand(b.getText());
            b.setMaximumSize(new Dimension(100,75));
            buttonOptions.add(b);
        }
        panel1.add(buttonOptions, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(gameController);
        startButton.setActionCommand("new");
        panel1.add(startButton, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(1400,575);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Actually start the game:
        //abm.userCreateGame();
        //abm.playGame();
    }

    public void handleGameUpdate(GameEvent gameModel) {

    }

    public void handlePlayerTurnUpdate(PlayerTurnEvent playerTurn) {
        updateLine.setText("It is " + playerTurn.getName() + "'s turn: ");
    }

    public void handleStateUpdate(PlayerStateEvent playerState) {
        playersInfo.get(playerState.getOrder()).setText(playerState.getInfo());
    }

    public static void  main(String[] args){
        File f = new File("Risk/images/ifihadone.jpg");
        System.out.println(f.exists());
        GameFrame gFrame = new GameFrame("The game of RISK");
    }
}
