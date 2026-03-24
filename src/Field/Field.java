package Field;

import Game.Difficulty;
import Cells.Bomb;
import Cells.Cell;
import Cells.Empty;

public class Field {
    private int height;
    private int width;

    private int totalMines;

    private Cell[][] fieldCells;

    int maxSpaces;

    public Field(Difficulty diffPick){

        int[] diffSettings = diffPick.settings();

        width = diffSettings[0];
        height = diffSettings[1];
        totalMines = diffSettings[2];

        maxSpaces = (int) Math.floor(Math.log10(Math.abs(height))) + 1;

        fieldCells = new Cell[height][width];
    }

    //=====FIELD=====

    public void resetField(){

        placeBombs();

        for(int row = 0; row < height; row++){

            for(int col = 0; col < width; col++){

                if( !(fieldCells[row][col] instanceof Bomb) ){
                    fieldCells[row][col] = new Empty(numberOfBombsOfCell(row, col));
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

                Cell cell = fieldCells[i][j];

                if(cell.isRevealed()){

                    if(cell instanceof Bomb){
                        sb.append(((Bomb) cell).getCharacter() + "|");
                    }
                    else{
                        sb.append(((Empty) cell).getNumberOfBombsAround() + "|");
                    }
                }
                else if(cell.isFlagged()){
                    sb.append(Cell.flaggedCharacter + "|");
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

        if(action.equals("F")){
            fieldCells[row][col].setFlagged();
        }
        else if(action.equals("R")){

            if(isBombCell(row, col)){
                return true;
            }
            else{
                fieldCells[row][col].setRevealed();
            }
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }

        return false;
    }

    private boolean isBombCell(int row, int col){
        return fieldCells[row][col] instanceof Bomb;
    }

    private int numberOfBombsOfCell(int row, int col){

        int count = 0;

        //top
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < width
                    && row - 1 >= 0
                    && fieldCells[row - 1][col + i] instanceof Bomb){
                count++;
            }
        }

        //middle
        if(col - 1 >= 0 && fieldCells[row][col - 1] instanceof Bomb){
            count++;
        }

        if(col + 1 < width && fieldCells[row][col + 1] instanceof Bomb){
            count++;
        }

        //bottom
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < width
                    && row + 1 < height
                    && fieldCells[row + 1][col + i] instanceof Bomb){

                count++;
            }
        }

        return count;
    }

    private void revealEmptiness(){

    }

    //=====BOMBS=====

    private void placeBombs(){
        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int row = getRandom(height);
            int col = getRandom(width);

            if(isBombCell(row, col)){
                continue;
            }

            fieldCells[row][col] = new Bomb();
            count++;

            cellIndex++;
        }
        System.out.println(count);
    }

    private int getRandom(int num){
        return (int) (num * Math.random());
    }

}
