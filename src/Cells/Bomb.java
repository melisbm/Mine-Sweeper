package Cells;

import Field.Field;

public class Bomb implements Cell {

    public static final char CHARACTER = 'B';

    private boolean isFlagged = false;
    private boolean isRevealed = false;

    private int row;
    private int column;

    private Field field;

    public Bomb(){
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


}
