import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * @author Team Group - Alexandre Hassan, Jonah Gaudet
 * @version 17-10-2020
 */

public class GameModel {
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private int currentPlayerReinforcements;
    private Map map;
    private final ArrayList<GameView> gameViews;

    private static final int[] BEGINNING_TROOPS = {50, 35, 30, 25, 20};

    /**
     * Default constructor for game.
     */
    public GameModel() {
        this.currentPlayer = null;
        this.players = new ArrayList<>();
        this.map = null;
        this.gameViews = new ArrayList<>();
    }

    /**
     * Adds a game view to the model
     *
     * @param gameView GameView to add
     */
    public void addGameView(GameView gameView) {
        gameViews.add(gameView);
    }

    /**
     * Updates all game views
     */
    private void updateGameViewsStart() {
        for (GameView v : gameViews) {
            v.handleGameStart(new GameStartEvent(this, map, players));
        }
    }

    private void resetView() {
        for (GameView v : gameViews) {
            v.handleResetView();
        }
    }

    public void updateGameViewsTurnState(String newState) {
        for (GameView v : gameViews) {
            v.handleTurnStateChange(new TurnStateEvent(this, newState));
        }
    }

    /**
     * Updates all game view
     *
     * @param stateInfo   the info to update the view with
     * @param playerOrder the order of the player to update
     */
    private void updateGameViewsState(String stateInfo, int playerOrder) {
        for (GameView v : gameViews) {
            v.handleStateUpdate(new PlayerStateEvent(this, stateInfo, playerOrder));
        }
    }

    /**
     * Updates all game view
     *
     * @param countryName the country to update the view for
     * @param playerOrder the order of the player to update
     */
    private void updateGameViewsOwnerChange(String countryName, int playerOrder) {
        for (GameView v : gameViews) {
            v.handleOwnerChange(new OwnerChangeEvent(this, countryName, playerOrder));
        }
    }

    /**
     * Updates all game view as to whose turn it is
     *
     * @param playerName the player whose turn it is
     */
    public void updatePlayerTurn(String playerName) {
        for (GameView v : gameViews) {
            v.handlePlayerTurnUpdate(new PlayerTurnEvent(this, playerName, players.indexOf(currentPlayer)));
        }
    }

    /**
     * User can add between 2 and 6 players (inclusive) to a game. They can
     * then generate a full game and begin playing immediately
     */
    public void userCreateGame() {

        try {

            ArrayList<JTextField> playerInput = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                playerInput.add(new JTextField());
            }

            Object[] message = {
                    "Player 1", playerInput.get(0),
                    "Player 2", playerInput.get(1),
                    "Player 3", playerInput.get(2),
                    "Player 4", playerInput.get(3),
                    "Player 5", playerInput.get(4),
                    "Player 6", playerInput.get(5),
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Add players", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                LinkedList<Player> currentPlayers = new LinkedList<>();
                String playerName;
                for (JTextField jTextField : playerInput) {
                    playerName = jTextField.getText().trim();
                    if (!playerName.equals("")) {
                        currentPlayers.add(new Player(playerName));
                    }
                }

                if (currentPlayers.size() < 2) {
                    throw new IllegalArgumentException("Cannot have less than 2 players");
                } else {
                    players.clear();
                    for (Player player : currentPlayers) {
                        addPlayer(player);
                    }
                }
                resetView();
                generateGame();
                updateGameViewsStart();
                updateState();

            } else {
                System.out.println("Buddy creation failed");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Generates a game as long as two players are present. Assign countries, picks
     * a starting players, assigns troops.
     */
    public void generateGame() {
        //GetMap
        if (players.size() < 2) {
            System.out.println("Add players to start a game");
            return;
        }
        //Choose a random player to start the game
        currentPlayer = players.get(ThreadLocalRandom.current().nextInt(0, players.size()));

        //Assign countries to players (shuffle order)
        this.map = new Map();

        //Assign countries randomly
        ArrayList<String> countryKeysArrayList = map.getShuffledKeys();
        for (String key : countryKeysArrayList) {
            currentPlayer.addCountry(map.getCountry(key));
            nextPlayer(false);
        }

        //Sort countries and Randomly Assign troops to countries
        for (Player player : players) {
            player.sortCountries();
            player.assignBeginningTroops(BEGINNING_TROOPS[players.size() - 2]);
        }
    }

    /**
     * Gets the number of reinforcements to add for the current player and
     * assigns them randomly to one or more of their countries
     */
    public int getReinforcements() {
        //gets the number of reinforcements the currentPlayer should be able to place at the beginning of the turn
        int extraTroops = 0;
        for (Continent continent : map.getContinents()) {
            if (currentPlayer.hasCountries(continent.getCountries())) {
                extraTroops += continent.getReinforcements();
            }
        }
        return Math.max(3, currentPlayer.numberOfCountries() / 3) + extraTroops;

        //autoPutReinforcements(reinforcements);
    }

    /**
     * Gets the number of remaining (not eliminated) players
     *
     * @return int amount of remaining players
     */
    private int getRemainingPlayers() {
        int counter = 0;
        for (Player p : players)
            if (!p.isEliminated()) counter++;
        return counter;
    }

    /**
     * Method to add reinforcement to a player's countries automatically,
     * will always add on countries on the exterior of a player's territory.
     * <p>
     * Early version of what will be used for AI players.
     *
     * @param reinforcements the number of troops to place.
     */
    private void autoPutReinforcements(int reinforcements) {
        ArrayList<Country> perimeterCountries = currentPlayer.getPerimeterCountries();
        for (int assigned = 0; assigned < reinforcements; assigned++) {
            perimeterCountries.get(ThreadLocalRandom.current().nextInt(0, perimeterCountries.size())).addTroop(1);
        }
    }

    public void putReinforcements(String country, int toAdd) {
        if (currentPlayer.hasCountry(map.getCountry(country))) {
            map.getCountry(country).addTroop(toAdd);
            updateState();
        } else {
            throw new IllegalArgumentException("The player does not own this country");
        }
    }

    /**
     * Begins the attacking process by ensuring the country names are capitalized
     * correctly, then getting the right countries and performing the attack
     *
     * @param attackingCountry the name of the attacking country
     * @param defendingCountry the name of the defending country
     */
    public void playAttack(String attackingCountry, String defendingCountry, boolean blitzAttack) {
        try {
            attackingCountry = makeProperCountryName(attackingCountry);
            defendingCountry = makeProperCountryName(defendingCountry);

            if (!blitzAttack) {
                performAttack(map.getCountry(attackingCountry), map.getCountry(defendingCountry));
            } else {
                performBlitzAttack(map.getCountry(attackingCountry), map.getCountry(defendingCountry));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Fixes the capitalization of countries (can input all lowercase, uppercase,
     * whatever the user feels like)
     *
     * @param name name of country whose capitalization is potentially off
     * @return String the name of the same country with proper capitalization
     */
    private String makeProperCountryName(String name) {
        name = name.toLowerCase();
        StringBuilder countryName = new StringBuilder(Character.toString(Character.toUpperCase(name.charAt(0))));
        for (int i = 1; i < name.length(); i++) {
            countryName.append((name.charAt(i - 1) == ' ') ? Character.toUpperCase(name.charAt(i)) : name.charAt(i));
        }
        return countryName.toString();
    }

    /**
     * Checks that the two countries involved in the attack meet the requirements for
     * a legal RISK battle
     *
     * @param attacking the attacking country
     * @param defending the defending country
     * @throws IllegalArgumentException if the attack is not valid.
     */
    private void checkAttackValid(Country attacking, Country defending) {
        if (!currentPlayer.hasCountry(attacking))
            throw new IllegalArgumentException("Current player does not control " + attacking.getName());
        if (currentPlayer.hasCountry(defending))
            throw new IllegalArgumentException("Current player already controls " + defending.getName());
        if (!attacking.hasNeighbor(defending))
            throw new IllegalArgumentException(defending.getName() + " does not border " + attacking.getName());
        if (attacking.getTroops() <= 1)
            throw new IllegalArgumentException(attacking.getName() + " does not have enough troops to attack (needs more than 1)");
    }

    public void performBlitzAttack(Country attack, Country defend) {
        checkAttackValid(attack, defend);
        int lostAttackers = 0;
        int lostDefenders = 0;
        while (attack.getTroops() > 1 && defend.getTroops() > 0) {
            ArrayList<Integer> attackerDice = new ArrayList<>();
            ArrayList<Integer> defenderDice = new ArrayList<>();
            for (int i = 0; i < Math.min(3, attack.getTroops() - 1); i++)
                attackerDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);
            for (int i = 0; i < Math.min(2, defend.getTroops()); i++)
                defenderDice.add(ThreadLocalRandom.current().nextInt(0, 6) + 1);

            attackerDice.sort(Collections.reverseOrder());
            defenderDice.sort(Collections.reverseOrder());

            for (int i = 0; i < defenderDice.size(); i++) {
                if (attackerDice.get(i) > defenderDice.get(i))
                    lostAttackers += 1;
                else
                    lostDefenders += 1;
            }
            attack.removeTroops(lostAttackers);
            defend.removeTroops(lostDefenders);
        }
        JOptionPane.showMessageDialog(null,
                "Attacker lost " + lostAttackers + " troop(s), " +
                        "defender lost " + lostDefenders + " troop(s).");
        if (defend.getTroops() == 0) {
            JOptionPane.showMessageDialog(null,
                    attack.getName() + " takes " + defend.getName());
            ownerChange(defend, attack, troopSelect(1, attack.getTroops() - 1));
        }
        updateState();
    }

    /**
     * Performs the attack given two countries (one attacking / defending)
     *
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
            } else {
                lostAttackers += 1;
            }
        }

        attack.removeTroops(lostAttackers);
        defend.removeTroops(lostDefenders);
        message = attackerName + " lost " + lostAttackers + " troop(s), " + (attackTroops - lostAttackers) + " troops remain\n" + defenderName + " lost " + lostDefenders + " troop(s), " + (defendTroops - lostDefenders) + " troops remain";
        JOptionPane.showMessageDialog(null, message);

        if (defend.getTroops() == 0) ownerChange(defend, attack, attackerDice.size() - lostAttackers);
        updateState();
    }

    /**
     * Changes the owner of a country and assigns troops
     *
     * @param defend the country whose ownership is being transferred
     * @param attack the country who must provide necessary troops
     */
    private void ownerChange(Country defend, Country attack, int minimumMove) {
        for (Player p : players) {
            if (p.hasCountry(defend)) {
                p.lost(defend);
                if (p.isEliminated()) handlePlayerElimination(p);
            }
        }

        int toAdd = troopSelect(minimumMove, attack.getTroops() - 1);

        currentPlayer.addCountry(defend);
        moveTroops(attack, defend, toAdd);
        String message = currentPlayer.getName() + " took " + defend.getName() + " with " + toAdd + " troops.";
        JOptionPane.showMessageDialog(null, message);
        updateGameViewsOwnerChange(defend.getName(), players.indexOf(currentPlayer));
        currentPlayer.sortCountries();
    }

    /**
     * Updates the game views to show that player p is eliminated.
     *
     * @param p the player who is eliminated.
     */
    private void handlePlayerElimination(Player p) {
        for (GameView v : gameViews) {
            v.handlePlayerElimination(new PlayerEliminatedEvent(this, p, players.indexOf(p)));
        }
    }

    /**
     * Moves troops from one country to the other
     *
     * @param origin      where the troops will leave from
     * @param destination where the troops will go to
     * @return whether the move was successful
     */
    public boolean moveTroops(Country origin, Country destination, int toMove) {
        if (!currentPlayer.pathExists(origin, destination)) {
            System.out.println("Path does not exist between " + origin.getName() + " " + destination.getName());
            return false;
        }

        origin.removeTroops(toMove);
        destination.addTroop(toMove);
        return true;
    }

    /**
     * Moves troops from one country to the other
     *
     * @param origin      where the troops will leave from
     * @param destination where the troops will go to
     * @return whether the move was successful
     */
    public boolean moveTroops(String origin, String destination) {
        Country originCountry = map.getCountry(origin);
        Country destinationCountry = map.getCountry(destination);
        if (!currentPlayer.pathExists(originCountry, destinationCountry)) {
            System.out.println("Path does not exist between " + originCountry.getName() + " " + destinationCountry.getName());
            return false;
        }
        if (originCountry.getTroops() == 1) {
            System.out.println(origin + " does not have enough troops to spare");
            return false;
        }

        int toMove = troopSelect(1, originCountry.getTroops() - 1);

        originCountry.removeTroops(toMove);
        destinationCountry.addTroop(toMove);
        return true;
    }

    /**
     * Returns true if the players owns a country, false if not
     *
     * @return if the current player owns the country
     */
    public boolean playerOwns(String country) {
        return currentPlayer.hasCountry(country);
    }

    /**
     * Prints help / instructions for the players
     */
    public void printHelp() {
        JOptionPane.showMessageDialog(
                null,
                "Game instructions: \n" +
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
     * @throws IllegalArgumentException if there are already 6 players.
     */
    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player can't be Null");
        }
        if (players.size() == 0) {
            currentPlayer = player;
        }
        if (players.size() < 6) {
            players.add(player);
        } else {
            throw new IllegalArgumentException("Maximum 6 players.");
        }
    }

    /**
     * Changes current player to the next player in the correct order until the next player is not eliminated.
     */
    public void nextPlayer(boolean gameStarted) {
        if (players.size() == 0) return;
        if (players.indexOf(currentPlayer) != players.size() - 1) {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        } else {
            currentPlayer = players.get(0);
        }
        if(gameStarted){
            int alive = 0;
            for (Player player : players) {
                if (!player.isEliminated()) alive++;
            }
            if (alive < 2 ) handleGameOver();
            if (currentPlayer.isEliminated()) nextPlayer(gameStarted);
        }

        currentPlayerReinforcements = getReinforcements();
        updatePlayerTurn(currentPlayer.getName());
    }

    /**
     * Updates all game views to display the fact currentPlayer has won.
     */
    private void handleGameOver() {
        for (GameView v : gameViews) {
            v.handleGameOver(new GameOverEvent(this, currentPlayer));
        }
    }

    public int getCurrentPlayerReinforcements() {
        return currentPlayerReinforcements;
    }

    public void placeCurrentPlayerReinforcements(String clickedCountry, int toRemove) {
        if (toRemove > currentPlayerReinforcements) {
            currentPlayerReinforcements = 0;
            System.out.println("Cannot remove more reinforcements than you currently have");
        }
        currentPlayerReinforcements -= toRemove;
        putReinforcements(clickedCountry, toRemove);
        updateGameViewsTurnState("reinforcement");
    }

    /**
     * Displays a message showing that it is the current player's turn
     */
    public void showCurrentPlayer() {
        JOptionPane.showMessageDialog(null, "It is " + currentPlayer.getName() + "'s turn");
    }

    /**
     * Prints the state of the board.
     * Includes player names, country names, and number of troops per country
     */
    public void updateState() {
        for (int i = 0; i < players.size(); i++) {
            updateGameViewsState(players.get(i).getInfo(), i);
        }
    }

    /**
     * Selects a number between the minimum and maximum using the parser
     *
     * @param minimum the minimum value
     * @param maximum the maximum value
     * @return the value chosen by the user
     */
    public int troopSelect(int minimum, int maximum) {
        if (minimum == maximum)
            return minimum;

        int toSelect = -1;
        while (toSelect < minimum || toSelect > maximum) {
            toSelect = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Number of troops (between " + minimum + " and " + maximum + "): ",
                    "Get troops",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    ""));
        }
        return toSelect;
    }

}
