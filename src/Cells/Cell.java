package Cells;

public interface Cell {

    public boolean isFlagged();
    public void setFlagged();

    public boolean isRevealed();
    public void setRevealed();

    public static char flaggedCharacter = 'P';

}
