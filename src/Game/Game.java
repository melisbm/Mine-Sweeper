package Game;

import Field.Field;
import Field.MoveResult;
import Console.Console;

import java.util.*;

public class Game {

    private boolean running;
    int totalMines;

    private Field field;

    private Console console;

    public void newGame(Difficulty diffPick){

        console = new Console();

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

            console.print("Row: ");
            int rowPick = console.inputInt() - 1;

            console.print("Column: ");
            int colPick = console.inputInt() - 1;

            console.print("([R] Reveal, [F] Toggle flag): ");
            char actionPick = console.inputChar();

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

            console.print("", 0, 1);
        }
    }

    public void stopGame(){

        console.close();
        running = false;
    }
}
