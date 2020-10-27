public interface GameView {

    void handleGameUpdate(GameEvent gameModel);

    void handleStateUpdate(PlayerStateEvent playerState);

    void handlePlayerTurnUpdate(PlayerTurnEvent playerTurn);

}
