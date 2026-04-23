package Field;

import Game.Difficulty;
import Game.GameState;
import Game.GameStateManager;

import java.util.*;

import static Field.Utils.FieldUtils.*;

public class Field {

    private int rows;
    private int columns;

    private int totalMines;

    private Cell[][] field;
    private List<Cell> flaggedCells = new ArrayList<>();

    private int totalFlaggedCells = 0;

    public Field(Difficulty diff){

        rows = diff.getRows();
        columns = diff.getColumns();

        totalMines = diff.getBombs();

        field = new Cell[rows][columns];

        resetField();
    }

    public void resetField(){
        placeBombs();
        fillField();
        loadCells();
    }

    public void updateField(int row, int col, String action, GameStateManager gameStateManager){

        Cell cell = field[row][col];

        if(action.equals("F")){

            cell.toggleFlagged();
            flaggedCells.add(cell);

            totalFlaggedCells++;

            if(totalFlaggedCells == totalMines){

                for(Cell flaggedCell : flaggedCells){

                    if(!flaggedCell.isBomb()){
                        break;
                    }

                    gameStateManager.setGameState(GameState.Win);
                }
            }
        }
        else if(action.equals("R")) {

            if (cell.isBomb()) {
                gameStateManager.setGameState(GameState.Loose);
            } else{

                cell.setCharacterBasedOnAdjacentBombs();

                if (cell.getNumberOfAdjacentBombs() == 0){
                    revealEmptiness(row, col);
                }
            }

            cell.reveal();
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }
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
                            adjacentCell.setCharacterBasedOnAdjacentBombs();
                            nextRound.add(new int[]{adjRow, adjCol});
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

        System.out.println(count);
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
}