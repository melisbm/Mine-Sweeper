import java.util.*;

public class Field {
    int height;
    int width;

    int totalMines;

    char[][] fieldCells = new char[width][height];

    public Field(String diff){
        if(diff.equals("easy")){
            height = 9;
            width = 9;

            totalMines = 10;
        }
        else if(diff.equals("medium")){
            height = 16;
            width = 16;

            totalMines = 40;
        }
        else if(diff.equals("expert")){
            height = 16;
            width = 30;

            totalMines = 99;
        }
    }

    public String fieldToString(String difficulty){
        StringBuilder sb = new StringBuilder();
        return "";
    }

    public void placeBombs(){

        int[][] bombsIndex = new int[totalMines][2];

        int index = 0;

        while(index < totalMines){

            int x = getRandom(height);
            int y = getRandom(width);

            //for loop looking for duplicates

            bombsIndex[index][0] = x;
            bombsIndex[index][1] = y;

            index++;
        }
    }

    private int getRandom(int num){
        int range = ((num - 1) - 0) + 1;
        int random = (int) ((range * Math.random()) + 0);
        return range;
    }

}
