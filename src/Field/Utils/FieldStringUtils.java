package Field.Utils;

import Cells.*;
import Field.Field;

public class FieldStringUtils {

    public static java.lang.String fieldToString(Field field){

        int columns = field.getRows();
        int rows = field.getColumns();

        int maxSpaces = (int) Math.floor(Math.log10(Math.abs(rows))) + 1;

        Cell[][] fieldCells = field.getFieldCells();

        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(maxSpaces + 2));

        for(int col = 0; col < columns; col++){
            sb.append((col + 1) + " ");
        }

        sb.append("\n");

        int numSpaces = maxSpaces;
        int count = 0;

        for(int row = 0; row < rows; row++){

            if(count++ == 9){
                count = 0;
                numSpaces--;
            }

            sb.append(row + 1 + " ".repeat(numSpaces));
            sb.append("|");

            for(int col = 0; col < columns; col++){

                Cell cell = fieldCells[row][col];

                if(cell.isRevealed()){

                    if(cell instanceof Bomb){
                        sb.append(Bomb.CHARACTER + "|");
                    }
                    else{
                        sb.append(((Empty) cell).getCellCharacter() + "|");
                    }
                }
                else if(cell.isFlagged()){
                    sb.append(Cell.FLAGGED_CHARACTER + "|");
                }
                else{
                    sb.append(Cell.NOT_REVEALED_CHARACTER + "|");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
