package Game;

import Field.Field;
import Field.MoveResult;
import Console.Console;

import java.util.*;

public class Game {

    private boolean running;
    private int totalMines;

    private Field field;

    private Console console;

    public Game(Console console){
        this.console = console;
    }

    public void newGame(Difficulty diffPick){

        int fieldRows = diffPick.getRows();
        int fieldColumns = diffPick.getColumns();
        int fieldMines = diffPick.getBombs();

        field = new Field(fieldRows, fieldColumns, fieldMines);
        totalMines = field.getTotalMines();

        running = true;
        gameLoop();
    }

    public void gameLoop(){

        while(running){

            String infoText = String.format("Mines: %d | Flags: %d", totalMines, field.getTotalFlaggedCells());
            console.print(infoText, 0, 1);
            console.print(field.getStringField());

            int rowPick = console.inputInt("Row: ") - 1;
            int colPick = console.inputInt("Column: ") - 1;

            char actionPick = console.inputChar("([R] Reveal, [F] Toggle flag): ");

            MoveResult moveResult = field.updateField(rowPick, colPick, actionPick);

            switch(moveResult){

                case MoveResult.LOOSE:

                    console.print("You stepped on a mine. You lost!", 1, 1);
                    console.print(field.getRevealedStringField(), 1, 1);

                    stopGame();
                    break;

                case MoveResult.WIN:

                    console.print(field.getStringField(), 1, 1);
                    console.print("You flagged every mine correctly. You win!",1, 1);
                    console.print(field.getRevealedStringField(), 1, 1);

                    stopGame();
                    break;
            }

            console.print("\n");
        }
    }

    public void stopGame(){

        console.close();
        running = false;
    }
}
