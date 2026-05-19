package Entities;

public class Cell {

    private final int position;
    private BoardEntity entity;

    public Cell(int position){
        this.position = position;
    }

    public int getPosition() { return position; }

    public synchronized BoardEntity getEntity() { return this.entity; }
    public synchronized void setEntity(BoardEntity entity) { this.entity = entity; }

    public synchronized boolean hasEntity() { return this.entity != null; }
}
