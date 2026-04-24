import Console.Console;
import Game.*;
import Menu.Menu;

import java.lang.*;

public class Main {
    public static void main(String[] args){

        Console console = new Console();

        Menu menu = new Menu(console);
        Game game = new Game(console);

        menu.showTitle();
        menu.showDifficultyOptions();

        char difficultyPick = console.inputChar(">");
        console.println();

        Difficulty difficulty = menu.getDifficulty(difficultyPick);
        game.newGame(difficulty);
    }
}