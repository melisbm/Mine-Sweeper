package Cells;

public class Empty implements Cell {

    private int numberOfAdjacentBombs;

    private boolean isFlagged = false;
    private boolean isRevealed = false;

    public Empty(int numberOfAdjacentBombs){
        this.numberOfAdjacentBombs = numberOfAdjacentBombs;
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

    public int getAdjacentBombs(){
        return numberOfAdjacentBombs;
    }

}
