public class Field {
    int height;
    int width;

    int totalMines;

    char[][] fieldCells = new char[width][height];

    public Field(String difficulty){
        if(difficulty.equals("easy")){
            height = 9;
            width = 9;

            totalMines = 10;
        }
        else if(difficulty.equals("medium")){
            height = 16;
            width = 16;

            totalMines = 40;
        }
        else if(difficulty.equals("expert")){
            height = 16;
            width = 30;

            totalMines = 99;
        }
    }

    public String fieldToString(String difficulty){
        return "";
    }

}
