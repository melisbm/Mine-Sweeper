package Game;

import Field.Field;

import java.util.*;

public class Game {

    private Scanner sc;
    private boolean running;
    private GameStateManager gameStateManager;
    int totalMines;

    private Field field;

    public void newGame(Difficulty diffPick){

        field = new Field(diffPick);

        sc = new Scanner(System.in);

        gameStateManager = new GameStateManager();
        totalMines = field.getTotalMines();

        running = true;
        gameLoop();
    }

    public void gameLoop(){

        while(running){

            System.out.printf("Mines: %d | Flags: %d\n", totalMines, field.getTotalFlaggedCells());
            System.out.println(field.getStringField());

            System.out.print("Row: ");
            int rowPick = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("Column: ");
            int colPick = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("([R] Reveal, [F] Toggle flag): ");
            String actionPick = sc.next();

            field.updateField(rowPick, colPick, actionPick, gameStateManager);

            GameState gameState = gameStateManager.getGameState();

            switch(gameState){

                case GameState.Loose:

                    System.out.println("\nYou stepped on a mine. You lost!");
                    System.out.println("\n" + field.getRevealedStringField());

                    stopGame();
                    break;

                case GameState.Win:

                    System.out.println("\n" + field.getStringField());
                    System.out.println("\nYou flagged every mine correctly. You win!");
                    System.out.println("\n" + field.getRevealedStringField());

                    stopGame();
                    break;
            }

            System.out.println();
        }
    }

    public void stopGame(){

        sc.close();
        running = false;
    }
}
