package Field.Utils;

import Cells.*;
import Field.Field;

public class FieldUtils {

    //Todo: implement it in cell class
    public static boolean isBombCell(Cell cell){
        return cell instanceof Bomb;
    }

    public static boolean isEmptyCell(Cell cell){
        return cell instanceof Empty;
    }

    public static int getRandomPosFromLength(int length){
        return (int) (length * Math.random());
    }
}
