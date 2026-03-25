package Cells;

public interface Cell {

    public boolean isFlagged();
    public void toggleFlagged();

    public boolean isRevealed();
    public void reveal();

    public static final char FLAGGED_CHARACTER = 'P';
    public static final char NOT_REVEALED_CHARACTER = 'X';

}
