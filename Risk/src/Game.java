import java.util.ArrayList;
import java.util.Collections;
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
    private Map map;

    private static final int[] BEGINNING_TROOPS = {50,35,30,25,20};

    /**
     * Default constructor for game.
     */
    public Game() {
        currentPlayer = null;
        players = new ArrayList<>();
        parser = new Parser ();
        map = null;
    }

    public Game(ArrayList<Player> players) {
        currentPlayer = null;
        this.players = players;
        parser = new Parser ();
        map = null;
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

        //Assign countries to players (shuffle order)
        this.map = new Map();
        map.shuffleCountries();
        for (Country c : map.getCountries()) {
            currentPlayer.addCountry(c);
            nextPlayer();
        }
        for (Player p : players) {
            p.sortCountries();
        }

        //Randomly Assign troops to countries
        for (Player player:players) {
            //To stop to0 many troops from being assigned to a single country we set a max number of troops on one country
            //The maximum should be at least 4
            int maxTroops = Math.max(BEGINNING_TROOPS[players.size()]/player.countries.size() + 2, 4);
            int random;
            for(int assigned = player.countries.size(); assigned<BEGINNING_TROOPS[players.size()]; assigned++){
                random = ThreadLocalRandom.current().nextInt(0,player.countries.size());
                if(player.countries.get(random).getTroops()<maxTroops){
                    player.countries.get(random).addTroop(1);
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
            try {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return false; //return (remainingPlayers == 1);
    }

    private boolean processCommand (Command command) {
        if(!command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false; //Turn not finished
        }

        switch (command.getCommandWord().toLowerCase()) {
            case "attack" -> playAttack(command);
            case "move" -> playMove(command);
            case "help" -> printHelp();
            case "state" -> printState();
            case "end" -> {
                System.out.println("Ending turn...");
                return true; // Turn finished
            }
            default -> System.out.println("Command not recognized...?");
        }
        return false; // Turn not finished
    }

    private void playAttack (Command command) {
        if (command.getCommandDetails() == null) {
            System.out.println("Input syntax incorrect");
            return;
        }

        System.out.println("Attacking ...");
        String defendingCountry = "", attackingCountry = "";
        boolean reachedFrom = false;
        String[] commandDetails = command.getCommandDetails().split(" ");

        for (String s : commandDetails) {
            if (s.equals("from") || s.equals("with")) {
                reachedFrom = true;
                continue;
            }
            if (!reachedFrom)
                defendingCountry = addToString(defendingCountry, s);
            else
                attackingCountry = addToString(attackingCountry, s);
        }

        try {
            checkAttackInputValid(attackingCountry, defendingCountry);
            performAttack(map.getCountry(attackingCountry), map.getCountry(defendingCountry));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String addToString (String original, String toAdd) {
        if (original.equals("") || original == null)
            return toAdd;
        return original + " " + toAdd;
    }

    private void checkAttackInputValid (String attacking, String defending) {
        if (attacking == null || defending == null) {
            throw new IllegalArgumentException("Input syntax not read correctly");
        }
        if (attacking.equals("") || defending.equals("")) {
            throw new IllegalArgumentException("Input syntax not read correctly");
        }
        map.getCountry(defending);
        map.getCountry(attacking);
    }

    private void checkAttackValid (Country attacking, Country defending) {
        if (!currentPlayer.countries.contains(attacking)) {
            throw new IllegalArgumentException("Current player does not control " + attacking);
        }
        if (currentPlayer.countries.contains(defending)) {
            throw new IllegalArgumentException("Current player already controls " + defending);
        }
        if (!attacking.getNeighbors().contains(defending)) {
            throw new IllegalArgumentException(defending + " does not border " + attacking);
        }
        if (attacking.getTroops() <= 1) {
            throw new IllegalArgumentException(attacking + " does not have enough troops to attack (needs more than 1)");
        }
    }

    private void performAttack(Country attack, Country defend) {
        checkAttackValid(attack, defend);

        int attackWith = troopSelect(1, Math.min(3, attack.getTroops() - 1));

        ArrayList<Integer> attackerDice = new ArrayList<>();
        ArrayList<Integer> defenderDice = new ArrayList<>();
        for (int i = 0; i < attackWith; i++)
            attackerDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);
        for (int i = 0; i < Math.min(2, defend.getTroops()); i++)
            defenderDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);

        attackerDice.sort(Collections.reverseOrder());
        defenderDice.sort(Collections.reverseOrder());
        int lostDefenders = 0, lostAttackers = 0;
        for (int i = 0; i < Math.min(attackerDice.size(), defenderDice.size()); i++) {
            if (attackerDice.get(i) > defenderDice.get(i))
                lostDefenders += 1;
            else
                lostAttackers += 1;
            System.out.println(attackerDice.get(i) + " " + defenderDice.get(i));
        }

        attack.removeTroops(lostAttackers);
        defend.removeTroops(lostDefenders);
        System.out.println(attack.toString() + " lost " + lostAttackers + " troop(s)");
        System.out.println(defend.toString() + " lost " + lostDefenders + " troop(s)");

        if (defend.getTroops() == 0) {
            System.out.println(currentPlayer.getName() + " took " + defend.toString());
            ownerChange(defend, attack, attackerDice.size() - lostAttackers);
        }
    }

    private void ownerChange(Country defend, Country attack, int minimumMove) {
        for (Player p : players) {
            if (p.countries.contains(defend)) {
                p.countries.remove(defend);
                p.checkEliminated();
            }
        }

        int toAdd = troopSelect(minimumMove, attack.getTroops() - 1);

        currentPlayer.addCountry(defend);
        moveTroops(attack, defend, toAdd);
        System.out.println(currentPlayer.getName() + " took " + defend.toString() + " with " + toAdd + " troops.");
        currentPlayer.sortCountries();
    }

    private void playMove (Command command) {
        System.out.println("Moving ...");
        command.printCommand();
        System.out.println(command.getCommandDetails());
    }

    private boolean moveTroopsNoNumber(Country origin, Country destination) {
        return moveTroops(origin, destination, troopSelect(1, origin.getTroops() - 1));
    }

    private boolean moveTroops(Country origin, Country destination, int toMove) {
        if (!currentPlayer.pathExists(origin, destination)) {
            System.out.println("Path does not exist between " + origin.toString() + " " + destination.toString());
            return false;
        }
        origin.removeTroops(toMove);
        destination.addTroop(toMove);
        return true;
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
        if (players.size() == 0) {
            currentPlayer = player;
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
        if (currentPlayer != null)
            System.out.println("Current player is " + currentPlayer.getName());
    }

    private int troopSelect (int minimum, int maximum) {
        if (minimum == maximum)
            return minimum;

        int toSelect = -1;
        while (toSelect < minimum || toSelect > maximum) {
            System.out.println("Number of troops to move (between " + minimum + " and " + maximum + ")");
            toSelect = parser.getNumber();
        }
        return toSelect;
    }

//    public void testPathExists() {
//        for (Player p : players) {
//            System.out.println(p.getName());
//            p.testPathExists();
//        }
//    }

    public static void  main(String[] args){
        Game test = new Game();
        test.addPlayer(new Player("Player1"));
        test.addPlayer(new Player("Player2"));
        test.addPlayer(new Player("Player3"));
        test.addPlayer(new Player("Player4"));
        test.generateGame();
        //test.testPathExists();
        test.playGame();
    }
}
