package Menu;

import Console.Console;
import Game.Difficulty;

import java.util.Scanner;

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

    public Difficulty getDifficulty(char pick){

        return switch (pick){
            case '2' -> Difficulty.MEDIUM;
            case '3' -> Difficulty.EXPERT;
            default -> Difficulty.EASY;
        };
    }
}
