import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * @version 17-10-2020
 * @author Team Group - Alexandre Hassan, Jonah Gaudet
 */

public class Game {
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Map map;

    private static final int[] BEGINNING_TROOPS = {50,35,30,25,20};

    /**
     * Default constructor for game.
     */
    public Game() {
        currentPlayer = null;
        players = new ArrayList<>();
        map = null;
    }

    /**
     * Constructor for the game with a list of players to participate
     * @param players the players to participate in the game
     */
    public Game(ArrayList<Player> players) {
        currentPlayer = null;
        this.players = players;
        map = null;
    }

    /**
     * User can add between 2 and 6 players (inclusive) to a game. They can
     * then generate a full game and begin playing immediately
     */
    public void userCreateGame () {
        boolean finished = false;
        while (!finished && players.size() < 6) {
            try {
                String newName = (String) JOptionPane.showInputDialog(
                        null,
                        "New player name (input 'done' to continue): ",
                        "Player name: ",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                if (newName.toLowerCase().equals("done")) {
                    finished = true;
                } else {
                    addPlayer(new Player(newName));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        if (players.size() < 2) {
            throw new IllegalArgumentException("Cannot have less than 2 players");
        }

        generateGame();
        playGame();
    }

    /**
     * Generates a game as long as two players are present. Assign countries, picks
     * a starting players, assigns troops.
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

        //Assign countries randomly
        ArrayList<String> countryKeysArrayList= map.getShuffledKeys();
        for (String key : countryKeysArrayList) {
            currentPlayer.addCountry(map.getCountry(key));
            nextPlayer();
        }

        //Sort countries and Randomly Assign troops to countries
        for(Player player: players){
            player.sortCountries();
            player.assignBeginningTroops(BEGINNING_TROOPS[players.size()-2]);
        }
        printState();
    }

    /**
     * Plays a game of RISK. Ends if and only if no players remain. Calls turns for each player
     * in order and adjust the current player accordingly
     */
    public void playGame () {
        System.out.println("Number of players: " + players.size());
        boolean gameFinished = false;
        while (!gameFinished) {
            if (!currentPlayer.isEliminated())
                gameFinished = playTurn();
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        }
    }

    /**
     * Plays a turn using currentPlayer. Gets command from the parser and reacts accordingly
     * @return true if and only if one player remains
     */
    private boolean playTurn () {
        System.out.println(currentPlayer.getName() + "'s turn:");
        JOptionPane.showMessageDialog(null, currentPlayer.getName() + "'s turn:");
        boolean finished = false;

        getReinforcements();

        //Game loop
        while (!finished) {
            try {
                String command = (String) JOptionPane.showInputDialog(
                        null,
                        "Insert command (attack, state, end, help): ",
                        currentPlayer.getName() + "'s turn. Command please: ",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                finished = processCommand(command);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        String endGame = (String) JOptionPane.showInputDialog(
                null,
                "End game ? ('Y' to end): ",
                "Eng game question",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        if (endGame.equals("Y"))
            return true;
        return (getRemainingPlayers() == 1);
    }

    /**
     * Gets the number of reinforcements to add for the current player and
     * assigns them randomly to one or more of their countries
     */
    private void getReinforcements () {
        //gets the number of reinforcements the currentPlayer should be able to place at the beginning of the turn
        int extraTroops = 0;
        for(Continent continent: map.getContinents()) {
            if (currentPlayer.hasCountries(continent.getCountries())) {
                extraTroops += continent.getReinforcements();
            }
        }
        int reinforcements = Math.max(3, currentPlayer.NumberOfCountries()/3) + extraTroops;


        autoPutReinforcements(reinforcements);
    }

    /**
     * Gets the number of remaining (not eliminated) players
     * @return int amount of remaining players
     */
    private int getRemainingPlayers () {
        int counter = 0;
        for (Player p : players)
            if (!p.isEliminated()) {
                counter += 1;
            }
        return counter;
    }

    /**
     * Method to add reinforcement to a player's countries automatically,
     * will always add on countries on the exterior of a player's territory.
     *
     * Early version of what will be used for AI players.
     *
     * @param reinforcements the number of troops to place.
     */
    private void autoPutReinforcements(int reinforcements){
        System.out.println(reinforcements + " reinforcements to set.");
        ArrayList<Country> perimeterCountries = currentPlayer.getPerimeterCountries();
        for(int assigned = 0; assigned < reinforcements; assigned++){
            int randomIndex = ThreadLocalRandom.current().nextInt(0, perimeterCountries.size());
            putReinforcements(perimeterCountries.get(randomIndex),1);
        }
    }

    /**
     * Adds reinforcements to the selected country
     *
     * @param country the country to have the troop added to
     * @param numberOfTroops the number of troops
     */
    private void putReinforcements(Country country, int numberOfTroops){
        country.addTroop(numberOfTroops, true);
    }


    /**
     * Processes the command from the player using one of the commands words below
     * @param command from the player via the terminal
     * @return boolean true if the turn is finished, false if not
     */
    private boolean processCommand (String command) {
        switch (command.split(" ")[0].toLowerCase()) {
            case "attack" -> playAttack();
            case "help" -> printHelp();
            case "state" -> printState();
            case "end" -> {
                System.out.println("Ending turn...");
                return true; // Turn finished
            }
            default ->  {
                System.out.println("Command not recognized...?");
                JOptionPane.showMessageDialog(null, "Command not recognized...?");
            }
        }
        return false; // Turn not finished
    }

    /**
     * Checks to see if the input from the command is valid, if so proceeds with the attack.
     * Command should be of the form 'attack <defender> from/with <attacker>'
     */
    private void playAttack () {
        try {
            String defendingCountry = (String) JOptionPane.showInputDialog(
                    null,
                    "Defending country: ",
                    "Country name: ",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
            String attackingCountry = (String) JOptionPane.showInputDialog(
                    null,
                    "Attacking country: ",
                    "Country name: ",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");

            performAttack(map.getCountry(attackingCountry), map.getCountry(defendingCountry));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /**
     * Returns a string such that the two input strings are separated by a space and
     * no leading or trailing spaces exist
     * @param original String to be added to
     * @param toAdd String to add to original
     * @return String the sum to the two inputs
     */
    private String addToString (String original, String toAdd) {
        if (original.equals("") || original == null)
            return toAdd;
        return original + " " + toAdd;
    }

    /**
     * Checks that the two countries involved in the attack meet the requirements for
     * a legal RISK battle
     * @param attacking the attacking country
     * @param defending the defending country
     */
    private void checkAttackValid (Country attacking, Country defending) {
        if (!currentPlayer.hasCountry(attacking)) {
            throw new IllegalArgumentException("Current player does not control " + attacking);
        }
        else if (currentPlayer.hasCountry(defending)) {
            throw new IllegalArgumentException("Current player already controls " + defending);
        }
        else if (!attacking.hasNeighbor(defending)) {
            throw new IllegalArgumentException(defending.getName() + " does not border " + attacking.getName());
        }
        else if (attacking.getTroops() <= 1) {
            throw new IllegalArgumentException(attacking.getName() + " does not have enough troops to attack (needs more than 1)");
        }
    }

    /**
     * Performs the attack given two countries (one attacking / defending)
     * @param attack the attacking country
     * @param defend the defending country
     */
    private void performAttack(Country attack, Country defend) {
        checkAttackValid(attack, defend);

        String attackerName = attack.getName();
        String defenderName = defend.getName();
        int attackTroops = attack.getTroops();
        int defendTroops = defend.getTroops();

        String message = attackerName + " has " + attackTroops + " troops\n" + defenderName + " has " + defendTroops + " troops";
        System.out.println(message);
        JOptionPane.showMessageDialog(null, message);

        int attackWith = troopSelect(1, Math.min(3, attackTroops - 1));
        int defendWith = Math.min(2, defendTroops);
        ArrayList<Integer> attackerDice = new ArrayList<>();
        ArrayList<Integer> defenderDice = new ArrayList<>();
        for (int i = 0; i < attackWith; i++)
            attackerDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);
        for (int i = 0; i < defendWith; i++)
            defenderDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);

        attackerDice.sort(Collections.reverseOrder());
        defenderDice.sort(Collections.reverseOrder());
        message = "Attacker rolls: " + Arrays.toString(attackerDice.toArray()) + "\nDefender rolls: " + Arrays.toString(defenderDice.toArray());
        System.out.println(message);
        JOptionPane.showMessageDialog(null, message);
        int lostDefenders = 0, lostAttackers = 0;
        for (int i = 0; i < Math.min(attackerDice.size(), defenderDice.size()); i++) {
            if (attackerDice.get(i) > defenderDice.get(i)) {
                lostDefenders += 1;
            }
            else {
                lostAttackers += 1;
            }
        }

        attack.removeTroops(lostAttackers);
        defend.removeTroops(lostDefenders);
        message = attackerName + " lost " + lostAttackers + " troop(s), " + (attackTroops - lostAttackers) + " troops remain\n" + defenderName + " lost " + lostDefenders + " troop(s), " + (defendTroops - lostDefenders) + " troops remain";
        JOptionPane.showMessageDialog(null, message);
        System.out.println(message);

        if (defend.getTroops() == 0) {
            ownerChange(defend, attack, attackerDice.size() - lostAttackers);
        }
    }

    /**
     * Changes the owner of a country and assigns troops
     * @param defend the country whose ownership is being transferred
     * @param attack the country who must provide necessary troops
     */
    private void ownerChange(Country defend, Country attack, int minimumMove) {
        for (Player p : players) {
            if (p.hasCountry(defend)) {
                p.lost(defend);
                p.checkEliminated();
            }
        }

        int toAdd = troopSelect(minimumMove, attack.getTroops() - 1);

        currentPlayer.addCountry(defend);
        moveTroops(attack, defend, toAdd);
        String message = currentPlayer.getName() + " took " + defend.getName() + " with " + toAdd + " troops.";
        System.out.println(message);
        JOptionPane.showMessageDialog(null, message);
        currentPlayer.sortCountries();
    }

    /**
     * Moves troops from one country to the other
     * @param origin where the troops will leave from
     * @param destination where the troops will go to
     * @return whether the move was successful
     */
    private boolean moveTroopsNoNumber(Country origin, Country destination) {
        return moveTroops(origin, destination, troopSelect(1, origin.getTroops() - 1));
    }

    /**
     * Moves troops from one country to the other
     * @param origin where the troops will leave from
     * @param destination where the troops will go to
     * @param toMove the number of troops to move
     * @return whether the move was successful
     */
    private boolean moveTroops(Country origin, Country destination, int toMove) {
        if (!currentPlayer.pathExists(origin, destination)) {
            System.out.println("Path does not exist between " + origin.getName() + " " + destination.getName());
            return false;
        }
        origin.removeTroops(toMove);
        destination.addTroop(toMove, true);
        return true;
    }

    /**
     * Prints help / instructions for the players
     */
    private void printHelp () {
        JOptionPane.showMessageDialog(
                null,
                "Commands: attack, state, end, help: ");
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

    /**
     * Changes current player to the next player in the correct order
     */
    public void nextPlayer(){
        if(players.indexOf(currentPlayer) != players.size() -1){
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }else{
            currentPlayer = players.get(0);
        }
    }

    /**
     * Prints the state of the board.
     * Includes player names, country names, and number of troops per country
     */
    public void printState(){
        for(Player player: players){
            player.print();
            System.out.println();
            System.out.println();
        }
        if (currentPlayer != null)
            JOptionPane.showMessageDialog(
                    null,
                    "Current player is " + currentPlayer.getName()  );
    }


    /**
     * Selects a number between the minimum and maximum using the parser
     * @param minimum the minimum value
     * @param maximum the maximum value
     * @return the value chosen by the user
    */
    private int troopSelect (int minimum, int maximum) {
        if (minimum == maximum)
            return minimum;

        int toSelect = -1;
        while (toSelect < minimum || toSelect > maximum) {
            toSelect = Integer.parseInt ((String) JOptionPane.showInputDialog(
                    null,
                    "Number of troops (between " + minimum + " and " + maximum + "): ",
                    "Get troops",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "") );
        }
        return toSelect;
    }

    public static void  main(String[] args){
        Game test = new Game();
        test.userCreateGame();
        /*test.addPlayer(new Player("Player1"));
        test.addPlayer(new Player("Player2"));
        test.addPlayer(new Player("Player3"));
        test.addPlayer(new Player("Player4"));
        test.generateGame();
        test.testPathExists();
        test.playGame();*/
    }
}
