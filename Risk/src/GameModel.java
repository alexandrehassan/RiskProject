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

public class GameModel {
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Map map;
    private ArrayList<GameView> gameViews;

    private static final int[] BEGINNING_TROOPS = {50,35,30,25,20};

    /**
     * Default constructor for game.
     */
    public GameModel() {
        this.currentPlayer = null;
        this.players = new ArrayList<>();
        this.map = null;
        this.gameViews = new ArrayList<GameView> ();
    }

    /**
     * Constructor for the game with a list of players to participate
     * @param players the players to participate in the game
     */
    public GameModel(ArrayList<Player> players) {
        this.currentPlayer = null;
        this.players = players;
        this.map = null;
        this.gameViews = new ArrayList<GameView> ();
    }

    /**
     * Adds a game view to the model
     * @param gameView GameView to add
     */
    public void addGameView (GameView gameView) {
        gameViews.add(gameView);
    }

    /**
     * Updates all game views
     */
    private void updateGameViewsStart () {
        for (GameView v : gameViews) {
            v.handleGameStart(new GameStartEvent(this, map, players));
        }
    }

    /**
     * Updates all game view
     * @param stateInfo the info to update the view with
     * @param playerOrder the order of the player to update
     */
    private void updateGameViewsState (String stateInfo, int playerOrder) {
        for (GameView v : gameViews) {
            v.handleStateUpdate(new PlayerStateEvent(this, stateInfo, playerOrder));
        }
    }

    /**
     * Updates all game view as to whose turn it is
     * @param playerName the player whose turn it is
     */
    public void updatePlayerTurn (String playerName) {
        for (GameView v : gameViews) {
            v.handlePlayerTurnUpdate(new PlayerTurnEvent(this, playerName, players.indexOf(currentPlayer)));
        }
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
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        if (players.size() < 2) {
            throw new IllegalArgumentException("Cannot have less than 2 players");
        }

        generateGame();
        updateGameViewsStart();
    }

    /**
     * Generates a game as long as two players are present. Assign countries, picks
     * a starting players, assigns troops.
     */
    public void generateGame() {
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
        updateState();
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
        country.addTroop(numberOfTroops);
    }

    /**
     * Begins the attacking process by ensuring the country names are capitalized
     * correctly, then getting the right countries and performing the attack
     * @param attackingCountry the name of the attacking country
     * @param defendingCountry the name of the defending country
     */
    public void playAttack(String attackingCountry, String defendingCountry) {
        try {
            attackingCountry = makeProperCountryName(attackingCountry);
            defendingCountry = makeProperCountryName(defendingCountry);

            performAttack(map.getCountry(attackingCountry), map.getCountry(defendingCountry));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Fixes the capitalization of countries (can input all lowercase, uppercase,
     * whatever the user feels like)
     * @param name name of country whose capitalization is potentially off
     * @return String the name of the same country with proper capitalization
     */
    private String makeProperCountryName (String name) {
        name = name.toLowerCase();
        String countryName = "";
        countryName += Character.toUpperCase(name.charAt(0));
        for (int i = 1; i < name.length(); i++) {
            countryName += (name.charAt(i-1) == ' ') ? Character.toUpperCase(name.charAt(i)) : name.charAt(i);
        }
        return countryName;
    }

    /**
     * Checks that the two countries involved in the attack meet the requirements for
     * a legal RISK battle
     * @param attacking the attacking country
     * @param defending the defending country
     */
    private void checkAttackValid (Country attacking, Country defending) {
        if (!currentPlayer.hasCountry(attacking)) {
            throw new IllegalArgumentException("Current player does not control " + attacking.getName());
        }
        else if (currentPlayer.hasCountry(defending)) {
            throw new IllegalArgumentException("Current player already controls " + defending.getName());
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

        if (defend.getTroops() == 0) {
            ownerChange(defend, attack, attackerDice.size() - lostAttackers);
        }
        updateState();
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
        destination.addTroop(toMove);
        return true;
    }

    /**
     * Prints help / instructions for the players
     */
    public void printHelp () {
        JOptionPane.showMessageDialog(
                null,
                "Game instructions:\n" +
                "To attack, select the attack button and choose a defending and attacking country\n" +
                "To end your turn, select the 'end' button\n" +
                "To manually update the charts on the right, select the 'state' button\n" +
                "To get help, select the 'help' button\n" +
                "The current player is shown in the top left corner");
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
            throw new IllegalArgumentException("Maximum 6 players.");
        }
    }

    /**
     * Changes current player to the next player in the correct order
     */
    public void nextPlayer(){
        if (players.size() == 0)
            return;
        if(players.indexOf(currentPlayer) != players.size() -1){
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }else{
            currentPlayer = players.get(0);
        }
        updatePlayerTurn(currentPlayer.getName());
    }

    /**
     * Displays a message showing that it is the current player's turn
     */
    public void showCurrentPlayer () {
        JOptionPane.showMessageDialog(null, "It is " + currentPlayer.getName() + "'s turn");
    }

    /**
     * Prints the state of the board.
     * Includes player names, country names, and number of troops per country
     */
    public void updateState(){
        for (int i = 0; i < players.size(); i++) {
            updateGameViewsState(players.get(i).getInfo(), i);
        }
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
}
