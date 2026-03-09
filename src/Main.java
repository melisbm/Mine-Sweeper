import java.util.*;
import java.lang.*;

public class  Main {
    public static void main(String[] args){

        Game game = new Game();

        game.startGame(Difficulty.EASY);
        game.stopGame();
    }
}
