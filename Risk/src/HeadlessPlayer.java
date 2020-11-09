import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Alexandre Hassan
 * @version 2020-11-09
 */
public class HeadlessPlayer extends Player{
    /**
     * Default constructor for the class Player.
     *
     * @param name the name of the player.
     */
    private boolean threwException;
    public HeadlessPlayer(String name) {
        super(name);
        threwException = false;
    }

    public int troopSelect(int minimum, int maximum) {
        return ThreadLocalRandom.current().nextInt(minimum, maximum);
    }

    public void message(String message) {
    }

    public void errorHandling(Exception e) {
        threwException = true;
    }
    public void resetError(){
        threwException = false;
    }

    public boolean isThrewException() {
        return threwException;
    }
}
