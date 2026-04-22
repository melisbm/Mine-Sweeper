package Menu;

import Game.Difficulty;

import java.util.Scanner;

public class Menu {

    public Menu(){}

    public void show(){

        System.out.println("===== Mine Sweeper =====");
        System.out.println("=== Difficulty ===");
        System.out.println("1) Easy");
        System.out.println("2) Medium");
        System.out.println("3) Expert");
    }

    public Difficulty askDiff(){
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        char pick = sc.next().charAt(0);
        sc.nextLine();

        return switch (pick){
            case '2' -> Difficulty.MEDIUM;
            case '3' -> Difficulty.EXPERT;
            default -> Difficulty.EASY;
        };
    }
}
