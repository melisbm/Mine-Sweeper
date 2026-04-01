package Cells;

import Field.Field;

//Todo: make character state for each cell
public interface Cell {

    boolean isFlagged();
    void toggleFlagged();

    boolean isRevealed();
    void reveal();

    int getRow();
    int getColumn();

    public static final char FLAGGED_CHARACTER = 'P';
    public static final char NOT_REVEALED_CHARACTER = 'X';

    Field getField();
}