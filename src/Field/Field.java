package Field;

import Game.Difficulty;
import Cells.*;
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

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){

                if( !isBombCell((field[row][col])) ){
                    field[row][col] = new Empty(row, col, this);
                }
            }
        }

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){

                Cell cell = field[row][col];

                if( !isBombCell((cell)) ){
                    ((Empty) (cell)).setAdjacentBombs();
                }
            }
        }
    }

    public void updateField(int row, int col, String action, GameStateManager gameStateManager){

        Cell cell = field[row][col];

        if(action.equals("F")){

            cell.toggleFlagged();
            flaggedCells.add(cell);

            totalFlaggedCells++;

            if(totalFlaggedCells == totalMines){

                for(Cell flaggedCell : flaggedCells){

                    if(!isBombCell(flaggedCell)){
                        break;
                    }

                    gameStateManager.setGameState(GameState.Win);
                }
            }

        }
        else if(action.equals("R")) {

            if (isBombCell(cell)) {
                gameStateManager.setGameState(GameState.Loose);

            } else if (isEmptyCell(cell)) {

                if (((Empty) cell).getAdjacentBombs() == 0){
                    revealEmptiness(cell);
                }
            }

            cell.reveal();
        }
        else{
            throw new IllegalArgumentException("Action can only be reveal or mark down.");
        }
    }

    //todo: refactor
    private void revealEmptiness(Cell startingCell) {

        Cell[] cellRound = { startingCell };

        while(cellRound.length != 0){

            List<Cell> newCellRound = new ArrayList<>();

            for(Cell cell : cellRound){

                int cellRow = cell.getRow();
                int cellCol = cell.getColumn();

                //{ left, right, upper, down }
                int[][] AdjacentCellsCoords = { { cellRow, cellCol - 1 },
                                                { cellRow, cellCol + 1 },
                                                { cellRow - 1, cellCol },
                                                { cellRow + 1, cellCol },
                                                { cellRow - 1, cellCol - 1},
                                                { cellRow + 1, cellCol + 1},
                                                { cellRow - 1, cellCol + 1},
                                                { cellRow + 1, cellCol - 1} };


                for(int[] cellCoords : AdjacentCellsCoords){

                    int adjacentCellRow = cellCoords[0];
                    int adjacentCellCol = cellCoords[1];

                    if(adjacentCellRow < rows && adjacentCellCol < columns && adjacentCellRow >= 0 && adjacentCellCol >= 0){

                        Cell adjacentCell = field[adjacentCellRow][adjacentCellCol];

                        if(isEmptyCell(adjacentCell) && !adjacentCell.isRevealed() && ((Empty) cell).getAdjacentBombs() == 0){

                            adjacentCell.reveal();
                            newCellRound.add(adjacentCell);
                        }
                    }
                }
            }

            cellRound = newCellRound.toArray(new Cell[0]);
        }
    }

    private void placeBombs(){

        int count = 0;
        int cellIndex = 0;

        while(cellIndex < totalMines){

            int row = getRandomPosFromLength(rows);
            int col = getRandomPosFromLength(columns);

            Cell cell = field[row][col];

            if(isBombCell(cell)){
                continue;
            }

            Bomb bomb = new Bomb();
            field[row][col] = bomb;
            count++;

            cellIndex++;
        }

        System.out.println(count);
    }

    //=====Getters=====
    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return columns;
    }

    public Cell[][] getField(){
        return field;
    }
}