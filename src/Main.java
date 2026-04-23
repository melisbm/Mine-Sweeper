import Game.*;
import Menu.Menu;

import java.lang.*;

public class Main {
    public static void main(String[] args){

        Menu menu = new Menu();
        Game game = new Game();

        menu.show();
        Difficulty diff = menu.askDiff();
        System.out.println();
        game.newGame(diff);
    }
}