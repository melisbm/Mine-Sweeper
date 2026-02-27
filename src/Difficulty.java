public class Difficulty {

    //{ width, height, bombs }
    private final int[] EASY = { 9, 9, 16 };
    private final int[] MEDIUM = { 16, 16, 40 };
    private final int[] EXPERT = { 16, 30, 99 };

    public int[] getSettings(String diff){

        if(diff.equals("easy")){
            return EASY;
        }
        else if(diff.equals("medium")){
            return MEDIUM;
        }
        else if(diff.equals("hard")){
            return EXPERT;
        }
        else{
            throw new IllegalArgumentException("Difficulty can only be easy, medium or hard");
        }
    }
}
