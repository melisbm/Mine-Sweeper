package Field.Utils;

import Field.Field;
import Field.Cell;

public class FieldStringUtils {

    public static java.lang.String fieldToString(Field field){

        int columns = field.getColumns();
        int rows = field.getRows();

        int maxSpaces = (int) Math.floor(Math.log10(Math.abs(rows))) + 1;

        Cell[][] fieldCells = field.getField();

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

                sb.append(cell.getCharacter() + "|");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}