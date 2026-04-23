package Field.Utils;

import Cells.*;
import Field.Field;

public class FieldUtils {

    public static int getRandomPosFromLength(int length){
        return (int) (length * Math.random());
    }
}
