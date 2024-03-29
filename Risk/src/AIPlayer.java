import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Alexandre Hassan
 * @version 2020-11-15
 */
public class AIPlayer extends Player {

    private GameModel model;

    /**
     * Default constructor for the class Player.
     *
     * @param name      the name of the player.
     * @param gameModel the model representing the game being played.
     */
    public AIPlayer(String name, GameModel gameModel) {
        super(name);
        this.model = gameModel;
    }

    @Override
    public void handleError(Exception e) {
        //TODO
    }

    @Override
    public void handleMessage(String message) {
    }

    /**
     * Goes through all three phases of a turn.
     */
    @Override
    public void playTurn() {
        int currentPlayerReinforcements = model.getCurrentPlayerReinforcements();
        autoPutReinforcements(currentPlayerReinforcements);
        autoAttack();
        autoMove();
        model.updateState();
        model.nextPlayer();
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
        if (perimeterCountries.size() != 0) {
            for (int assigned = 0; assigned < reinforcements; ) {
                int index = ThreadLocalRandom.current().nextInt(0, perimeterCountries.size());
                Country country = perimeterCountries.get(index);
                boolean isAlone = country.getNeighbors().stream().noneMatch(this::hasCountry);//checks if player has any of the neighbors of the country,
                // prevents from placing troops on surrounded countries.
                if (isAlone && perimeterCountries.size() > 1) { //Restricts the number of countries player defends.

                    perimeterCountries.remove(country);
                } else {
                    model.placeAIReinforcements(country);
                    assigned++;
                }
            }
        }
    }

    /**
     * Attacks once from every country that has troops >1 and more troops than the country being attacked
     * (this always blitz attacks).
     */
    private void autoAttack() {
        for (Country possibleAttack : getPerimeterCountries()) {
            if (possibleAttack.getTroops() > 1) {
                for (Country possibleDefender : possibleAttack.getNeighbors()) {
                    if (!hasCountry(possibleDefender) && possibleAttack.getTroops() > possibleDefender.getTroops()) {
                        model.playAttack(possibleAttack, possibleDefender, true);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Method that moves troops from deep inside Ally territory to the front line.
     * If no deep countries exists (more than two layers in the players territory) it picks countries that are inside
     * (one layer in) in order to keep the AI's troops closest to the action.
     */
    private void autoMove() {
        ArrayList<Country> innerCountries = getInnerCountries();
        List<Country> innerWithTroops = innerCountries.stream()
                .filter(country -> country.getTroops() > 1).collect(Collectors.toList());
        if (innerWithTroops.size() == 0) return;
        ArrayList<Country> perimeterCountries = getPerimeterCountries();
        List<Country> deepInner = innerWithTroops.stream()
                .filter(inner -> inner.getNeighbors().stream().
                        noneMatch(perimeterCountries::contains)).collect(Collectors.toList());
        Country from;
        if (deepInner.size() != 0) {
            from = deepInner.get(0);
        } else {
            from = innerWithTroops.get(0);
        }

        for (Country perimeterCountry : perimeterCountries) {
            if (pathExists(from, perimeterCountry)) {
                model.moveTroops(from, perimeterCountry, from.getTroops() - 1);
                System.out.println(from + " from to " + perimeterCountry);
                return;
            }
        }
    }

    @Override
    public int troopSelect(int minimum, int maximum) {
        return maximum;
    }
}