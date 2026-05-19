package Entities;

public class Player {

    private final String id;
    private final String name;
    private int currentPosition;
    private boolean hasWon;

    public Player(String id, String name){
        this.id = id;
        this.name = name;
        this.currentPosition = 0;
        this.hasWon = false;
    }

    public String getId(){ return id;}
    public String getName() { return name;}

    public synchronized int getCurrentPosition(){ return currentPosition; }
    public synchronized void setCurrentPosition(int currentPosition) { this.currentPosition = currentPosition; }

    public synchronized boolean isHasWon() { return hasWon; }
    public synchronized void setHasWon(boolean hasWon) { this.hasWon = hasWon; }

}
