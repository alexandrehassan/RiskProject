public interface GameView {

    void handleGameUpdate(GameEvent gameModel);

    void handleStateUpdate(PlayerStateEvent gameModel);
}
