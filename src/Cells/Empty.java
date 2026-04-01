package Cells;

import Field.Field;

import static Cells.Utils.calculateNumberOfAdjacentBombs;

public class Empty implements Cell {

    public static char NO_ADJACENT_BOMBS_CELL_CHARACTER = ' ';
    private int numberOfAdjacentBombs;

    private boolean isFlagged = false;
    private boolean isRevealed = false;

    private int row;
    private int column;

    private Field field;

    public Empty(int row, int col, Field field){
        this.row = row;
        column = col;
        this.field = field;
    }

    @Override
    public boolean isFlagged(){
        return isFlagged;
    }

    @Override
    public void toggleFlagged(){
        isFlagged = !isFlagged;
    }

    @Override
    public void reveal(){
        isRevealed = true;
    }

    @Override
    public boolean isRevealed(){
        return isRevealed;
    }

    public char getCellCharacter(){

        if(numberOfAdjacentBombs == 0){
            return NO_ADJACENT_BOMBS_CELL_CHARACTER;
        }

        return (char) (numberOfAdjacentBombs + 48);
    }

    @Override
    public int getRow(){
        return row;
    }

    @Override
    public int getColumn(){
        return column;
    }

    @Override
    public Field getField() {
        return field;
    }

    //Todo: method on field to apply adjacent bombs
    public int getAdjacentBombs(){
        return calculateNumberOfAdjacentBombs(this);
    }

    public void setAdjacentBombs(){
        this.numberOfAdjacentBombs = calculateNumberOfAdjacentBombs(this);
    }
}
