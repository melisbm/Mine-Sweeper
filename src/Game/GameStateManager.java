package Game;

public class GameStateManager {

    private GameState state = GameState.Unknown;

    public void setGameState(GameState state){
        this.state = state;
    }

    public GameState getGameState() {
        return state;
    }
}
