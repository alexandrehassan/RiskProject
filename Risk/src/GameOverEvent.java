import java.util.EventObject;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 *
 * This class's purpose is to serve as an event object modified
 * specifically to suit the purposes of the game of RISK. This
 * GameOverEvent contains a player's name and all info pertaining
 * to the player, such as their countries, troops, etc
 *
 * @version 2020-11-04
 * @author Team Group - Alexandre Hassan
 */

public class GameOverEvent extends EventObject {
    private final Player winner;

    public GameOverEvent (GameModel gameModel, Player winner) {
        super(gameModel);
        this.winner = winner;
    }

    public Player getWinner(){
        return winner;
    }

}
