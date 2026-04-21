import Game.*;
import java.lang.*;

public class Main {
    public static void main(String[] args){

        Game game = new Game();
        //todo: reveal all bombs on endgame
        //todo: make sure you can only flag down unrevealed cells
        game.newGame(Difficulty.EASY);
    }
}