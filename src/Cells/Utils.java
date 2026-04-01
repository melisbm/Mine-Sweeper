package Cells;

import Field.Field;

public class Utils {

//    public static Cell[] getAdjacentCells(){
//
//    }

    //Todo: refactor into multiples methods
    public static int calculateNumberOfAdjacentBombs(Cell cell){

        Field field = cell.getField();

        int row = cell.getRow();
        int col = cell.getColumn();

        int columns = field.getColumns();
        int rows = field.getRows();

        Cell[][] fieldCells = field.getField();

        int count = 0;

        //top
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < columns
                    && row - 1 >= 0
                    && fieldCells[row - 1][col + i] instanceof Bomb){
                count++;
            }
        }

        //middle
        if(col - 1 >= 0 && fieldCells[row][col - 1] instanceof Bomb){
            count++;
        }

        if(col + 1 < columns && fieldCells[row][col + 1] instanceof Bomb){
            count++;
        }

        //bottom
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < columns
                    && row + 1 < rows
                    && fieldCells[row + 1][col + i] instanceof Bomb){

                count++;
            }
        }

        return count;
    }
}
