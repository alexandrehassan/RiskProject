import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 * <p>
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * GameShowEvent contains a message to be displayed to the view.
 *
 * @author Team Group - Alexandre Hassan
 * @version 27-10-2020
 */
public class GameShowEvent extends EventObject {

    private final String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameShowEvent(GameModel source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
