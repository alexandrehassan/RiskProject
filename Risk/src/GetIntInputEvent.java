import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 * <p>
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * GetIntInputEvent contains a message to be displayed to prompt
 * the user for an int input.
 *
 * @author Team Group - Alexandre Hassan
 * @version 27-10-2020
 */
public class GetIntInputEvent extends EventObject {


    private String message;
    private String title;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GetIntInputEvent(GameModel source, String message, String title) {
        super(source);
        this.message = message;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
