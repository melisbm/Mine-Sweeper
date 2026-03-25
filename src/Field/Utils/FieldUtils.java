package Field.Utils;

import Cells.Bomb;
import Cells.Cell;
import Field.Field;

public class FieldUtils {

    public static boolean isBombCell(Cell cell){
        return cell instanceof Bomb;
    }

    public static int numberOfBombsOnCell(int row, int col, Field field){

        int columns = field.getColumns();
        int rows = field.getRows();

        Cell[][] fieldCells = field.getFieldCells();

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

    public static int getRandomPosFromLength(int length){
        return (int) (length * Math.random());
    }
}
