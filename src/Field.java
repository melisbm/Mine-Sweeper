public class Field {
    private int height;
    private int width;

    private int totalMines;

    private char[][] fieldCells;
    private int[][] bombCoords;

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

        fieldCells = new char[height][width];
        bombCoords = new int[totalMines][2];
    }

    //=====FIELD=====

    public void resetField(){

        placeBombs();

        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                if(fieldCells[i][j] != 'B'){
                    fieldCells[i][j] = ' ';
                }
            }
        }
    }

    public String fieldToString(){

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < height; i++){

            sb.append("|");

            for(int j = 0; j < width; j++){
                sb.append(fieldCells[i][j] + "|");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    //=====BOMBS=====

    private void placeBombs(){
        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int x = getRandom(width);
            int y = getRandom(height);

            if(fieldCells[y][x] == 'B'){
                continue;
            }

            bombCoords[cellIndex][0] = x;
            bombCoords[cellIndex][1] = y;

            fieldCells[y][x] = 'B';
            count++;

            cellIndex++;
        }
        System.out.println(count);
    }

    private int getRandom(int num){
        return (int) (num * Math.random());
    }

}
