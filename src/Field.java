public class Field {
    private int height;
    private int width;

    private int totalMines;

    //0: no bombs around
    //1-8: num of bombs around
    //-1: bomb
    //-2: marked
    private int[][] fieldCells;

    private int[][] bombCoords;

    int maxSpaces;

    public Field(Difficulty diff, String diffPick){

        width = diff.getSettings(diffPick)[0];
        height = diff.getSettings(diffPick)[1];

        totalMines = diff.getSettings(diffPick)[2];

        maxSpaces = (int) Math.floor(Math.log10(Math.abs(height))) + 1;

        fieldCells = new int[height][width];
        bombCoords = new int[totalMines][2];
    }

    //=====FIELD=====

    public void resetField(){

        placeBombs();

        for(int i = 0; i < height; i++){

            for(int j = 0; j < width; j++){

                if(fieldCells[i][j] != -1){
                    fieldCells[i][j] = numberOfBombsOfCell(j, i);
                }
            }
        }
    }

    public String fieldToString(){

        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(maxSpaces + 2));

        for(int i = 0; i < width; i++){
            sb.append((i + 1) + " ");
        }

        sb.append("\n");

        int numSpaces = maxSpaces;
        int count = 0;

        for(int i = 0; i < height; i++){

            if(count++ == 9){
                count = 0;
                numSpaces--;
            }

            sb.append(i + 1 + " ".repeat(numSpaces));
            sb.append("|");

            for(int j = 0; j < width; j++){

                sb.append(fieldCells[i][j] + "|");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public void updateField(int col, int row){
        fieldCells[col][row] = numberOfBombsOfCell(col, row);
    }

    private int numberOfBombsOfCell(int col, int row){

        int count = 0;

        //top
        for(int i = -1; i <= 1; i++){
            if(col + i >= 0 && col + i < width && row - 1 >= 0 && fieldCells[row - 1][col + i] == -1){
                count++;
            }
        }

        //middle
        if(col - 1 >= 0 && fieldCells[row][col - 1] == -1){
            count++;
        }

        if(col + 1 < width && fieldCells[row][col + 1] == -1){
            count++;
        }

        //bottom
        for(int i = -1; i <= 1; i++){
            if(col + i >= 0 && col + i < width && row + 1 < height && fieldCells[row + 1][col + i] == -1){
                count++;
            }
        }

        return count;
    }

    //=====BOMBS=====

    private void placeBombs(){
        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int x = getRandom(width);
            int y = getRandom(height);

            if(fieldCells[y][x] == -1){
                continue;
            }

            bombCoords[cellIndex][0] = x;
            bombCoords[cellIndex][1] = y;

            fieldCells[y][x] = -1;
            count++;

            cellIndex++;
        }
        System.out.println(count);
    }

    private int getRandom(int num){
        return (int) (num * Math.random());
    }

}
