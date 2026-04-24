package Menu;

import Console.Console;
import Game.Difficulty;

public class Menu {

    private Console console;

    public Menu(Console console){
        this.console = console;
    }

    public void showDifficultyOptions(){

        console.println("=== Difficulty ===");
        console.println("1) Easy");
        console.println("2) Medium");
        console.println("3) Expert");
    }

    public void showTitle(){
        console.println("===== Mine Sweeper =====");
    }

    public Difficulty getDifficulty(int pick){

        return switch (pick){
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.EXPERT;
            default -> throw new IllegalArgumentException("Invalid number pick. Pick number must be between 1 and 3.");
        };
    }
}
