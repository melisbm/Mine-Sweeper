package Field;

import Game.Difficulty;
import Cells.*;
import Game.GameState;
import Game.GameStateManager;

import static Field.Utils.FieldUtils.*;

public class Field {

    private int rows;
    private int columns;

    private int totalMines;

    private Cell[][] field;

    public Field(Difficulty diff){

        rows = diff.getRows();
        columns = diff.getColumns();

        totalMines = diff.getBombs();

        field = new Cell[rows][columns];

        resetField();
    }

    public void resetField(){

        placeBombs();

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){

                if( !isBombCell((field[row][col])) ){
                    field[row][col] = new Empty(row, col, this);
                }
            }
        }

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){

                Cell cell = field[row][col];

                if( !isBombCell((cell)) ){
                    ((Empty) (cell)).setAdjacentBombs();
                }
            }
        }
    }

    public void updateField(int row, int col, String action, GameStateManager gameStateManager){

        Cell cell = field[row][col];

        if(action.equals("F")){
            cell.toggleFlagged();
        }
        else if(action.equals("R")) {

            if (isBombCell(cell)) {
                gameStateManager.setGameState(GameState.Loose);

            } else if (isEmptyCell(cell)) {

                if (((Empty) cell).getAdjacentBombs() == 0){
                    revealEmptiness(row, col);
                }
            }

            cell.reveal();
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }
    }

    private void revealEmptiness(int row, int col){
//        if(numberOfAdjacentBombsOn(row, col, this) ){
//
//        }
    }

    private void placeBombs(){

        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int row = getRandomPosFromLength(rows);
            int col = getRandomPosFromLength(columns);

            Cell cell = field[row][col];

            if(isBombCell(cell)){
                continue;
            }

            field[row][col] = new Bomb();
            count++;

            cellIndex++;
        }

        System.out.println(count);
    }

    //=====Getters=====
    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return columns;
    }

    public Cell[][] getField(){
        return field;
    }
}