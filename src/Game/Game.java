package Game;

import Field.Field;
import Field.Utils.FieldStringUtils;

import java.util.*;

public class Game {

    private Scanner sc;
    private boolean running;
    private GameStateManager gameStateManager;

    private Field field;

    public void newGame(Difficulty diffPick){

        field = new Field(diffPick);

        sc = new Scanner(System.in);

        gameStateManager = new GameStateManager();
        running = true;

        gameLoop();
    }

    public void gameLoop(){

        while(running){

            System.out.println(FieldStringUtils.fieldToString(field));

            System.out.print("\nRow: ");
            int rowPick = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("Column: ");
            int colPick = sc.nextInt() - 1;
            sc.nextLine();

            //todo: add remove flag feature
            System.out.print("([R] Reveal, [F] Toggle flag): ");
            String actionPick = sc.next();

            field.updateField(rowPick, colPick, actionPick, gameStateManager);

            GameState gameState = gameStateManager.getGameState();

            switch(gameState){

                case GameState.Loose:

                    System.out.println(FieldStringUtils.fieldToString(field));
                    System.out.println("You stepped on a mine. You lost!");

                    stopGame();
                    break;

                case GameState.Win:

                    System.out.println(FieldStringUtils.fieldToString(field));
                    System.out.println("You flagged every mine correctly. You win!");

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
