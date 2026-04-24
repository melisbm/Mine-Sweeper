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

        int difficultyPick = console.inputInt(">");

        while (difficultyPick < 0 || difficultyPick > 3){

            String warningText = "Invalid input, type a number between 1 and 3: ";
            difficultyPick = console.inputInt(warningText);
        }

        console.println();

        Difficulty difficulty = menu.getDifficulty(difficultyPick);
        game.newGame(difficulty);
    }
}