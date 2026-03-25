package Field;

import Game.Difficulty;
import Cells.*;
import Field.Utils.*;

public class Field {
    private int rows;
    private int columns;

    private int totalMines;

    private Cell[][] fieldCells;

    int maxSpaces;

    public Field(Difficulty diff){

        rows = diff.getRows();
        columns = diff.getColumns();

        totalMines = diff.getBombs();

        fieldCells = new Cell[rows][columns];

        resetField();
    }

    public void resetField(){

        placeBombs();

        for(int row = 0; row < rows; row++){

            for(int col = 0; col < columns; col++){

                if( !(fieldCells[row][col] instanceof Bomb) ){
                    fieldCells[row][col] = new Empty(FieldUtils.numberOfBombsOnCell(row, col, this));
                }
            }
        }
    }

    public boolean updateField(int row, int col, String action){

        if(action.equals("F")){
            fieldCells[row][col].toggleFlagged();
        }
        else if(action.equals("R")){

            if(FieldUtils.isBombCell(fieldCells[row][col])){
                return true;
            }
            else{
                fieldCells[row][col].reveal();
            }
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }

        return false;
    }

    private void revealEmptiness(){

    }

    private void placeBombs(){


        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int row = FieldUtils.getRandomPosFromLength(rows);
            int col = FieldUtils.getRandomPosFromLength(columns);

            Cell cell = fieldCells[row][col];

            if(FieldUtils.isBombCell(cell)){
                continue;
            }

            fieldCells[row][col] = new Bomb();
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

    public Cell[][] getFieldCells(){
        return fieldCells;
    }
}
