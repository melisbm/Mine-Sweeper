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

            System.out.print("Column: ");
            int colPick = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("([R]eveal, [F]lag down): ");
            String actionPick = sc.next();

            field.updateField(rowPick, colPick, actionPick, gameStateManager);

            System.out.println();
        }
    }

    public void stopGame(){
        sc.close();
        running = false;
    }
}
