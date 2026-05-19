package Component;

import Entities.BoardEntity;
import Entities.Cell;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private final int boardSize;
    private final Map<Integer, Cell> cells;

    public GameBoard(int boardSize){
        this.boardSize = boardSize;
        this.cells = new HashMap<>();
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i=1; i <= boardSize; i++){
            cells.put(i, new Cell(i));
        }
    }

    public synchronized void addEntity(BoardEntity entity){
        int start = entity.getStartPoint();
        if(start <= 1 || start >= boardSize){
            throw new IllegalArgumentException("Entity cannot start at boundaries.");
        }

        Cell targetCell = cells.get(start);
        if(targetCell.hasEntity()){
            throw new IllegalStateException("Cell " + start + " already contains an entity.");
        }

        targetCell.setEntity(entity);
    }

    public int getBoardSize() { return boardSize; }
    public synchronized Cell getCell(int position){
        return cells.get(position);
    }

}
