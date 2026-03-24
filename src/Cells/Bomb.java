package Cells;

public class Bomb implements Cell {

    private final char CHARACTER = 'B';

    private boolean isFlagged = false;
    private boolean isRevealed = false;

    public Bomb(){
    }

    @Override
    public boolean isFlagged(){
        return isFlagged;
    }

    @Override
    public void setFlagged(){
        isFlagged = true;
    }

    public char getCharacter(){
        return CHARACTER;
    }

    @Override
    public void setRevealed(){
        isRevealed = true;
    }

    @Override
    public boolean isRevealed(){
        return isRevealed;
    }
}
