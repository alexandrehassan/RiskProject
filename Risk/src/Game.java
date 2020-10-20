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
            //To stop to many troops from being assigned to a single country we set a max number of troops on one country
            //The maximum should be at least 4
            int maxTroops = Math.max(BEGINNING_TROOPS[players.size()]/player.getCountrySize() + 2, 4);
            int random;
            for(int assigned = player.getCountrySize(); assigned<BEGINNING_TROOPS[players.size()]; assigned++){
                random = ThreadLocalRandom.current().nextInt(0,player.getCountrySize());
                if(player.getCountries().get(random).getTroops()<maxTroops){
                    player.getCountries().get(random).addTroop(1);
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

        //gets the number of reinforcements the currentPlayer should be able to place at the beginning of the turn
        int extraTroops = 0;
        for(Continent continent: map.getContinents()) {
            if (currentPlayer.getCountries().containsAll(continent.getCountries())) {
                extraTroops += continent.getReinforcements();
            }
        }
        int reinforcements = Math.max(3, currentPlayer.getCountrySize()/3) + extraTroops;


        autoPutReinforcements(reinforcements);

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        return false; //return (remainingPlayers == 1);
    }


    /**
     * This is a method that randomly assigns reinforcement troops.
     * It is used to test the reinforcement turn methods
     * A version of this will be used for the AI players
     *
     * TODO: Add logic so the troops are put on outside countries (countries not in the middle of a players territory)
     */
    private void autoPutReinforcements(int reinforcements){
        //System.out.println(currentPlayer.getName() + " has " + currentPlayer.getCountrySize() + " Countries");
        System.out.println(reinforcements + " reinforcements to set.");
        for(int assigned = 0; assigned < reinforcements; assigned++){
            putReinforcements(currentPlayer.getCountries().get(ThreadLocalRandom.current().nextInt(0,
                    currentPlayer.getCountrySize())), 1);
        }
    }

    /**
     * Adds reinforcements to the selected country
     *
     * @param country the country to have the troop added to
     * @param numberOfTroops the number of troops
     */
    private void putReinforcements(Country country, int numberOfTroops){
        country.addTroop(numberOfTroops);
    }

    /**
     * Method Responsible for reacting to user input and signalling that a turn is finished.
     *
     * TODO: Add Phases in (Reinforcement, Attack, Movement)
     * @param command The command to be executed
     * @return whether or not the turn is finished.
     */
    private boolean processCommand (Command command) {
        if(!command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false; //Turn not finished
        }
        System.out.println(command.getCommandWord().toLowerCase());

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
                defendingCountry += s + " ";
            else
                attackingCountry += s + " ";
        }

        if (attackingCountry.equals("") || defendingCountry.equals("")) {
            System.out.println("Input syntax incorrect");
            return;
        }

        defendingCountry = defendingCountry.substring(0, defendingCountry.length() - 1);
        attackingCountry = attackingCountry.substring(0, attackingCountry.length() - 1);

        if (!attackInputValid(attackingCountry, defendingCountry))
            return;

        performAttack(map.getCountry(attackingCountry), map.getCountry(defendingCountry));
    }

    private boolean attackInputValid (String attacking, String defending) {
        if (attacking == null || defending == null) {
            System.out.println("Input syntax incorrect");
            return false;
        }
        if (!map.countryExists(defending)) {
            System.out.println(defending + " does not exist");
            return false;
        }
        if (!map.countryExists(attacking)) {
            System.out.println(attacking + " does not exist");
            return false;
        }
        if (!currentPlayer.hasCountry(attacking)) {
            System.out.println("Current player does not control " + attacking);
            return false;
        }
        if (currentPlayer.hasCountry(defending)) {
            System.out.println("Current player already controls " + defending);
            return false;
        }
        return true;
    }

    private void performAttack(Country attack, Country defend) {
        if (!attack.getNeighbors().contains(defend)) {
            System.out.println(defend.toString() + " does not border " + attack.toString());
            return;
        }

        if (attack.getTroops() <= 1) {
            System.out.println(attack.toString() + " does not have enough troops to attack (needs more than 1)");
            return;
        }

        int attackWith = troopSelect(1, Math.min(3, attack.getTroops()-1));

        ArrayList<Integer> attackerDice = new ArrayList<>();
        ArrayList<Integer> defenderDice = new ArrayList<>();
        for (int i = 0; i < attackWith; i++)
            attackerDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);
        for (int i = 0; i < Math.min(2, defend.getTroops()); i++)
            defenderDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);

        attackerDice.sort(Collections.reverseOrder());
        defenderDice.sort(Collections.reverseOrder());
        int lostDefenders = 0, lostAttackers = 0;
        for (int i = 0; i < Math.min(attackerDice.size(), defenderDice.size()) ; i++) {
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

        if (defend.getTroops() == 0)
            ownerChange(defend, attack, attackerDice.size() - lostAttackers);
    }

    private void ownerChange(Country defend, Country attack, int minimumMove) {
        for (Player p : players) {
            if (p.getCountries().contains(defend)) {
                p.getCountries().remove(defend);
                p.checkEliminated();
            }
        }

        int toAdd = troopSelect(minimumMove, attack.getTroops() - 1);

        defend.addTroop(toAdd);
        attack.removeTroops(toAdd);
        System.out.println(currentPlayer.getName() + " took " + defend.toString() + " with " + toAdd + " troops.");
        currentPlayer.addCountry(defend);
        currentPlayer.sortCountries();
    }

    private void playMove (Command command) {
        System.out.println("Moving ...");
        command.printCommand();
        System.out.println(command.getCommandDetails());
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
