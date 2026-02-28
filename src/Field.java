public class Field {
    private int height;
    private int width;

    private int totalMines;

    //[0-8]: num of bombs around
    //-1: bomb
    private int[][] fieldCells;

    private boolean[][] markedCells;
    private boolean[][] revealedCells;

    private int[][] bombCoords;

    int maxSpaces;

    public Field(String diffPick){

        int[] diffSettings = Difficulty.getSettings(diffPick);

        width = diffSettings[0];
        height = diffSettings[1];
        totalMines = diffSettings[2];

        maxSpaces = (int) Math.floor(Math.log10(Math.abs(height))) + 1;

        fieldCells = new int[height][width];

        markedCells = new boolean[height][width];
        revealedCells = new boolean[height][width];

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
                if(revealedCells[i][j]){
                    sb.append(fieldCells[i][j] + "|");
                }
                else if(markedCells[i][j]){
                    sb.append('?' + "|");
                }
                else{
                    sb.append('X' + "|");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean updateField(int row, int col, String action){

        if(action.equals("M")){
            markedCells[row][col] = true;
        }
        else if(action.equals("R")){

            if(isBombCell(row, col)){
                return true;
            }
            else{
                revealedCells[row][col] = true;
            }
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }

        return false;
    }

    private boolean isBombCell(int row, int col){
        return fieldCells[row][col] == -1;
    }

    private int numberOfBombsOfCell(int row, int col){

        int count = 0;

        //top
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < width
                    && row - 1 >= 0
                    && fieldCells[row - 1][col + i] == -1){
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

            if(col + i >= 0
                    && col + i < width
                    && row + 1 < height
                    && fieldCells[row + 1][col + i] == -1){

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

            int col = getRandom(width);
            int row = getRandom(height);

            if(fieldCells[row][col] == -1){
                continue;
            }

            bombCoords[cellIndex][0] = col;
            bombCoords[cellIndex][1] = row;

            fieldCells[row][col] = -1;
            count++;

            cellIndex++;
        }
        System.out.println(count);
    }

    private int getRandom(int num){
        return (int) (num * Math.random());
    }

}
