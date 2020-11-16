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

    /**
     * Handles the start of the game
     * @param gameModel a GameStartEvent
     */
    void handleGameStart(GameStartEvent gameModel);

    /**
     * Updates the state a player by their order
     * @param playerState a PlayerStateEvent
     */
    void handleStateUpdate(PlayerStateEvent playerState);

    /**
     * Updates the player who's turn it it, changes GUI accordingly
     * @param playerTurn a PlayerTurnEvent
     */
    void handlePlayerTurnUpdate(PlayerTurnEvent playerTurn);

    /**
     * Updates the owner (a player) of a country with a certain ID
     * @param ownerChange a OwnerChangeEvent
     */
    void handleOwnerChange(OwnerChangeEvent ownerChange);

    /**
     * Updates the state of the current player's turn, updates the label
     * @param turnState a TurnStateEvent
     */
    void handleTurnStateChange(TurnStateEvent turnState);

    /**
     * Handles the complete reset of the view concerning the player info text areas
     */
    void handleResetView();

    /**
     * Handles the elimination of a player
     * @param eliminatedEvent a PlayerEliminatedEvent
     */
    void handlePlayerElimination(PlayerEliminatedEvent eliminatedEvent);

    /**
     * Handles the end of a game
     * @param gameOverEvent a GameOverEvent
     */
    void handleGameOver(GameOverEvent gameOverEvent);

    void reset();
}
