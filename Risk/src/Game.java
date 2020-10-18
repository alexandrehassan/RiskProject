import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Main class for the risk game. Made for the SYSC 3110 Project.
 *
 * @version 17-10-2020
 * @author Team Group
 */

public class Game {
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Parser parser;

    private static final int[] BEGINNING_TROOPS = {50,35,30,25,20};

    /**
     * Default constructor for game.
     */
    public Game() {
        currentPlayer = null;
        players = new ArrayList<>();
        parser = new Parser ();
    }

    public Game(ArrayList<Player> players) {
        currentPlayer = null;
        this.players = players;
        parser = new Parser ();
    }

    /**
     * Generates a game as long as two players are present.
     */
    private void generateGame() {
        //GetMap
        if(players.size() < 2){
            System.out.println("Add players to start a game");
            return;
        }
        //Choose a random player to start the game
        currentPlayer = players.get(ThreadLocalRandom.current().nextInt(0, players.size()));
        ArrayList<Country> map = new Map().getCountries();

        //Randomly Assign countries
        Country randomCountry;
        while(map.size()>0){
            //Find a random Country
            randomCountry = map.get(ThreadLocalRandom.current().nextInt(0,map.size()));
            //Give it to a player
            currentPlayer.addCountry(randomCountry);
            //Remove it from the available countries
            map.remove(randomCountry);
            //Go to the next player
            nextPlayer();
        }

        //Randomly Assign troops to countries
        for (Player player:players) {
            ArrayList<Country> countryArrayList= player.countries;
            //To stop to many troops from being assigned to a single country we set a max number of troops on one country
            //The maximum should be at least 4
            int maxTroops = Math.max(BEGINNING_TROOPS[players.size()]/countryArrayList.size() + 2, 4);
            int random;
            for(int assigned = countryArrayList.size(); assigned<BEGINNING_TROOPS[players.size()]; assigned++){
                random = ThreadLocalRandom.current().nextInt(0,countryArrayList.size());
                if(countryArrayList.get(random).getTroops()<maxTroops){
                    countryArrayList.get(random).addTroop(1);
                    assigned++;
                }
            }
        }
        printState();
    }

    public void playGame () {
        System.out.println("Number of players: " + players.size());
        boolean gameFinished = false;
        while (!gameFinished) {
            if (!currentPlayer.isEliminated())
                gameFinished = playTurn();
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        }
    }

    private boolean playTurn () {
        System.out.println(currentPlayer.getName() + "'s turn:");
        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        return false; //return (remainingPlayers == 1);
    }

    private boolean processCommand (Command command) {
        if(!command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        switch (command.getCommandWord().toLowerCase()) {
            case "attack" -> playAttack();
            case "move" -> playMove();
            case "help" -> printHelp();
            case "end" -> {
                System.out.println("Ending turn...");
                return true; // Turn finished
            }
            default -> System.out.println("Command not recognized...?");
        }
        return false; // Turn not finished
    }

    private void playAttack () {
        System.out.println("Attacking XX with XX, XX, ...");
        //Check if both players are eliminated after turn is over
    }

    private void playMove () {
        System.out.println("Moving XX troops to XX...");
    }

    private void printHelp () {
        System.out.println("Possible user commands: ");
        parser.showCommands();
    }

    /**
     * Adds a player to the game, there can be maximum 6 players.
     * 2 players are needed to start a game
     *
     * @param player the player to add to the game
     * @throws IllegalArgumentException if player is null
     */
    public void addPlayer(Player player){
        if(player ==null){
            throw new IllegalArgumentException("Player can't be Null");
        }
        if(players.size()<6){
            players.add(player);
        }
        else {
            System.out.println("Maximum 6 players.");
        }
    }

    public void nextPlayer(){
        if(players.indexOf(currentPlayer) != players.size() -1){
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }else{
            currentPlayer = players.get(0);
        }
    }

    public void printState(){
        for(Player player: players){
            player.print();
            System.out.println();
            System.out.println();
        }
    }

    public static void  main(String[] args){
        Game test = new Game();
        test.addPlayer(new Player("Player1"));
        test.addPlayer(new Player("Player2"));
        test.addPlayer(new Player("Player3"));
        test.addPlayer(new Player("Player4"));
        test.generateGame();
        test.playGame();
    }
}
