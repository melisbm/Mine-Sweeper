package Cells;

public class Empty implements Cell {

    private int numberOfBombsAround;

    private boolean isFlagged = false;
    private boolean isRevealed = false;

    public Empty(){
    }

    public Empty(int numberOfBombsAround){
        this.numberOfBombsAround = numberOfBombsAround;
    }

    @Override
    public void setFlagged(){
        isFlagged = true;
    }

    @Override
    public void setRevealed(){
        isRevealed = true;
    }

    @Override
    public boolean isFlagged(){
        return isFlagged;
    }

    public boolean isRevealed(){
        return isRevealed;
    }

    public int getNumberOfBombsAround(){
        return numberOfBombsAround;
    }

    public void setNumberOfBombsAround(int bombs){
        numberOfBombsAround = bombs;
    }


}
