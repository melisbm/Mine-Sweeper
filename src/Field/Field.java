package Field;

import java.util.*;

public class Field {

    private int rows;
    private int columns;

    private int totalMines;

    private Cell[][] field;
    private List<Cell> flaggedCells = new ArrayList<>();

    private String stringField;
    private String revealedStringField;

    public Field(int rows, int columns, int totalMines){

        this.rows = rows;
        this.columns = columns;
        this.totalMines = totalMines;

        field = new Cell[rows][columns];

        resetField();
    }

    public void resetField(){
        placeBombs();
        fillField();
        loadCells();
        stringField = this.toString();
        revealedStringField = this.calculateRevealedStringField();
    }

    public MoveResult updateField(int row, int col, String action){

        Cell cell = field[row][col];

        if(action.equals("F")){

            if ( cell.isFlagged() ) flaggedCells.remove(cell);
            else flaggedCells.add(cell);

            cell.toggleFlagged();

            if(flaggedCells.size() == totalMines){

                boolean allCorrect = true;
                for (Cell flaggedCell : flaggedCells) {
                    if (!flaggedCell.isBomb()) {
                        allCorrect = false;
                        break;
                    }
                }
                if (allCorrect) return MoveResult.WIN;
            }
        }
        else if(action.equals("R")) {

            if (cell.isBomb()) {
                return MoveResult.LOOSE;
            }
            else if (cell.getNumberOfAdjacentBombs() == 0){
                revealEmptiness(row, col);
            }

            cell.reveal();
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }

        stringField = this.toString();

        return MoveResult.NONE;
    }

    private void revealEmptiness(int startRow, int startCol) {

        List<int[]> currentRound = new ArrayList<>();
        currentRound.add(new int[]{startRow, startCol});

        while (!currentRound.isEmpty()) {

            List<int[]> nextRound = new ArrayList<>();

            for (int[] coords : currentRound) {

                int cellRow = coords[0];
                int cellCol = coords[1];

                int[][] adjacent = { { cellRow, cellCol - 1 },
                                     { cellRow, cellCol + 1 },
                                     { cellRow - 1, cellCol },
                                     { cellRow + 1, cellCol },
                                     { cellRow - 1, cellCol - 1 },
                                     { cellRow + 1, cellCol + 1 },
                                     { cellRow - 1, cellCol + 1 },
                                     { cellRow + 1, cellCol - 1 } };

                for (int[] adj : adjacent) {

                    int adjRow = adj[0];
                    int adjCol = adj[1];

                    if (adjRow >= 0 && adjRow < rows && adjCol >= 0 && adjCol < columns) {

                        Cell adjacentCell = field[adjRow][adjCol];

                        if (!adjacentCell.isBomb() && !adjacentCell.isRevealed()) {

                            adjacentCell.reveal();

                            if(adjacentCell.getNumberOfAdjacentBombs() == 0){
                                nextRound.add(new int[]{adjRow, adjCol});
                            }
                        }
                    }
                }
            }

            currentRound = nextRound;
        }
    }

    private void loadCells(){

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){

                Cell cell = field[row][col];

                cell.setNumberOfAdjacentBombs(row, col, this);
            }
        }
    }

    private void placeBombs(){

        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int row = getRandomPosFromLength(rows);
            int col = getRandomPosFromLength(columns);

            Cell cell = field[row][col];

            if(cell != null && cell.isBomb()){
                continue;
            }

            Cell bomb = new Cell(true);
            field[row][col] = bomb;
            count++;

            cellIndex++;
        }
    }

    public static int getRandomPosFromLength(int length){
        return (int) (length * Math.random());
    }

    private void fillField(){

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){

                Cell cell = field[row][col];

                if(cell == null){
                    field[row][col] = new Cell(false);
                }
            }
        }
    }

    public String toString(){

        int maxSpaces = (int) Math.floor(Math.log10(Math.abs(rows))) + 1;

        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(maxSpaces + 2));

        for(int col = 0; col < columns; col++){
            sb.append((col + 1) + " ");
        }

        sb.append("\n");

        int numSpaces = maxSpaces;
        int count = 0;

        for(int row = 0; row < rows; row++){

            if(count++ == 9){
                count = 0;
                numSpaces--;
            }

            sb.append(row + 1 + " ".repeat(numSpaces));
            sb.append("|");

            for(int col = 0; col < columns; col++){

                Cell cell = field[row][col];

                sb.append(cell.getCharacter() + "|");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    private String calculateRevealedStringField(){

        int maxSpaces = (int) Math.floor(Math.log10(Math.abs(rows))) + 1;

        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(maxSpaces + 2));

        for(int col = 0; col < columns; col++){
            sb.append((col + 1) + " ");
        }

        sb.append("\n");

        int numSpaces = maxSpaces;
        int count = 0;

        for(int row = 0; row < rows; row++){

            if(count++ == 9){
                count = 0;
                numSpaces--;
            }

            sb.append(row + 1 + " ".repeat(numSpaces));
            sb.append("|");

            for(int col = 0; col < columns; col++){

                Cell cell = field[row][col];

                if (cell.isBomb()){
                    sb.append(Cell.BOMB_CHARACTER + "|");
                }
                else{
                    int numberOfAdjacentBombs = cell.getNumberOfAdjacentBombs();
                    sb.append( ((numberOfAdjacentBombs == 0)
                            ? Cell.NO_ADJACENT_BOMBS_CELL_CHARACTER
                            : (char) (numberOfAdjacentBombs + 48)) + "|");
                }
            }

            if(row != rows - 1){
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    //=====Getters=====
    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return rows;
    }

    public Cell[][] getField(){
        return field;
    }

    public String getStringField() {
        return stringField;
    }

    public String getRevealedStringField() {
        return revealedStringField;
    }

    public int getTotalMines(){
        return totalMines;
    }

    public int getTotalFlaggedCells(){
        return flaggedCells.size();
    }
}