package Field;

public class Cell {

    public static final char FLAGGED_CHARACTER = 'P';
    public static final char NOT_REVEALED_CHARACTER = 'X';
    public static final char BOMB_CHARACTER = 'Q';
    public static char NO_ADJACENT_BOMBS_CELL_CHARACTER = ' ';

    private char currentCharacter;

    private boolean isFlagged = false;
    private boolean isRevealed = false;
    private boolean isBomb = false;

    private int numberOfAdjacentBombs;

    public Cell(boolean isBomb){

        if (isBomb){
            this.isBomb = true;
        }

        currentCharacter = NOT_REVEALED_CHARACTER;
    }

    //=====GETTERS=====

    public boolean isRevealed(){
        return isRevealed;
    }
    public boolean isFlagged(){
        return isFlagged;
    }
    public boolean isBomb(){
        return isBomb;
    }

    public char getCharacter(){
        return currentCharacter;
    }

    public int getNumberOfAdjacentBombs(){
        return numberOfAdjacentBombs;
    }

    //=====SETTERS=====

    public void reveal(){

        if(isBomb){
            currentCharacter = BOMB_CHARACTER;
        }
        else{
            setCharacterBasedOnAdjacentBombs();
        }

        isRevealed = true;
    }
    public void toggleFlagged(){

        if (!isRevealed){

            isFlagged = !isFlagged;
            currentCharacter = (isFlagged) ? FLAGGED_CHARACTER : NOT_REVEALED_CHARACTER;
        }
    }

    public void setNumberOfAdjacentBombs(int row, int col, Field field){
        this.numberOfAdjacentBombs = calculateNumberOfAdjacentBombs(row, col, field);
    }

    private void setCharacterBasedOnAdjacentBombs(){

        currentCharacter = (numberOfAdjacentBombs == 0)
                ? NO_ADJACENT_BOMBS_CELL_CHARACTER
                : (char) (numberOfAdjacentBombs + 48);
    }

    private static int calculateNumberOfAdjacentBombs(int row, int col, Field field){

        int columns = field.getColumns();
        int rows = field.getRows();

        Cell[][] fieldCells = field.getField();

        int count = 0;

        //top
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < columns
                    && row - 1 >= 0
                    && fieldCells[row - 1][col + i].isBomb()){
                count++;
            }
        }

        //middle
        if(col - 1 >= 0 && fieldCells[row][col - 1].isBomb()){
            count++;
        }

        if(col + 1 < columns && fieldCells[row][col + 1].isBomb()){
            count++;
        }

        //bottom
        for(int i = -1; i <= 1; i++){

            if(col + i >= 0
                    && col + i < columns
                    && row + 1 < rows
                    && fieldCells[row + 1][col + i].isBomb()){

                count++;
            }
        }

        return count;
    }
}
