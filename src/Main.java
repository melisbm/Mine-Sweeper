import Game.*;
import Menu.Menu;

import java.lang.*;

public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();
        Game game = new Game();
        //todo: reveal all bombs on endgame
        //todo: make sure you can only flag down unrevealed cells

        menu.show();
        Difficulty diff = menu.askDiff();
        game.newGame(diff);
    }
}