import java.util.LinkedList;

/**
 * This class is part of the game of RISK, the term
 * project for SYSC3110 that emulates the original game of RISK
 * <p>
 * This class acts as an interface for the game's frame. It allows
 * the model to update various frames using the methods below
 *
 * @author Team Group - Jonah Gaudet
 * @version 27-10-2020
 */

public interface GameView {

    void handleGameStart(GameStartEvent gameModel);

    void handleStateUpdate(PlayerStateEvent playerState);

    void handlePlayerTurnUpdate(PlayerTurnEvent playerTurn);

    void handleOwnerChange(OwnerChangeEvent ownerChange);

    void handleTurnStateChange(TurnStateEvent turnState);

    void handleResetView();

    void handlePlayerElimination(PlayerEliminatedEvent eliminatedEvent);

    void handleGameOver(GameOverEvent gameOverEvent);

    void handleMessageShow(GameShowEvent gameShowEvent);

    LinkedList<String> getPlayerNames();

    void ShowErrorPopUp(Exception e);
}
