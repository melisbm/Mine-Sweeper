package Cells;

public class Bomb implements Cell {

    public static final char CHARACTER = 'B';

    private boolean isFlagged = false;
    private boolean isRevealed = false;

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
}
