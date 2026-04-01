import Game.*;
import java.lang.*;

public class Main {
    public static void main(String[] args){

        Game game = new Game();

        game.newGame(Difficulty.EASY);
        game.stopGame();
    }
}
