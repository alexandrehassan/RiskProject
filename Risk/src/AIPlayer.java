import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexandre Hassan
 * @version 2020-11-15
 */
public class AIPlayer extends Player{

    private boolean hadError;
    private StringBuilder turnMessages;
    private GameModel model;

    /**
     * Default constructor for the class Player.
     *
     * @param name the name of the player.
     * @param gameModel
     */
    public AIPlayer(String name, GameModel gameModel) {
        super(name);
        this.turnMessages = new StringBuilder();
        this.model = gameModel;
    }

    @Override
    public void handleError(Exception e) {
        hadError = true;
    }

    @Override
    public void handleMessage(String message) {
        turnMessages.append(message).append("\n");
    }

    public void playTurn(int currentPlayerReinforcements){
        autoPutReinforcements(currentPlayerReinforcements);
        autoAttack();
        model.updateState();
        //System.out.println(turnMessages);
        turnMessages.setLength(0);
    }

    private void autoAttack() {
        for(Country possibleAttack: getPerimeterCountries()){
            if(possibleAttack.getTroops()>1){
                for(Country possibleDefender: possibleAttack.getNeighbors()){
                    if(!hasCountry(possibleDefender)){
                        model.playAttack(possibleAttack,possibleDefender,true);
                        break;
                    }
                }
            }
        }
    }

    public String getTurnMessages(){
        return turnMessages.toString();
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
        ArrayList<Country> perimeterCountries = getPerimeterCountries();
        HashMap<String, Integer> addedReinforcements = new HashMap<String, Integer>();
        if (perimeterCountries.size() != 0) {
            for (int assigned = 0; assigned < reinforcements; assigned++) {
                int index = ThreadLocalRandom.current().nextInt(0, perimeterCountries.size());
                Country country = perimeterCountries.get(index);
                country.addTroop(1);
                String name = country.getName();
                if (addedReinforcements.containsKey(name)) {
                    addedReinforcements.put(name, addedReinforcements.get(name) + 1);
                }
                else {
                    addedReinforcements.put(country.getName(), 1);
                }
            }
            for (Object key : addedReinforcements.keySet()) {
                turnMessages.append("\nAdded " + addedReinforcements.get(key) + " reinforcements to " + key + ".");
            }
        }
    }

    @Override
    public int troopSelect(int minimum, int maximum) {
        return maximum;
    }
}