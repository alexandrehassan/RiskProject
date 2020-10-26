import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameFrame extends JFrame implements GameView{

    public GameFrame (String name) {
        super(name);

        GameModel abm = new GameModel();
        abm.addGameView(this);
        GameController gameController = new GameController(abm);

        JPanel panel1 = (JPanel) this.getContentPane();

        panel1.add(new JLabel("This is where the game would go:"), BorderLayout.PAGE_START);

        JLabel label1 = new JLabel();
        ImageIcon image = new ImageIcon("Risk/images/ifihadone.jpg");
        File f = new File("Risk/images/ifihadone.jpg");
        System.out.println(f.exists());
        Image newImage = image.getImage().getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
        label1.setIcon(new ImageIcon(newImage));
        label1.setBounds(0, 0, 1000, 500);
        panel1.add(label1, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(gameController);
        startButton.setActionCommand("new");
        panel1.add(startButton, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Actually start the game:
        //abm.userCreateGame();
        //abm.playGame();
    }

    public void handleAddressBookUpdate(GameEvent gameModel) {
        System.out.print("Handled bitches");
    }

    public static void  main(String[] args){
        File f = new File("Risk/images/ifihadone.jpg");
        System.out.println(f.exists());
        GameFrame gFrame = new GameFrame("The game of RISK");

        //GameModel test = new GameModel();
        //test.userCreateGame();
        /*test.addPlayer(new Player("Player1"));
        test.addPlayer(new Player("Player2"));
        test.addPlayer(new Player("Player3"));
        test.addPlayer(new Player("Player4"));*/
        //test.generateGame();
        //test.testPathExists();
        //test.playGame();
    }
}
