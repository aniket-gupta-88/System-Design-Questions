package Entities;

public class Snake implements BoardEntity{

    private final int head;
    private final int tail;

    public Snake(int head, int tail){
        if(head <= tail){
            throw new IllegalArgumentException("Snake head must be higher than its tail.");
        }
        this.head = head;
        this.tail = tail;
    }

    @Override
    public int getStartPoint() {
        return head;
    }

    @Override
    public int getEndPoint() {
        return tail;
    }

    @Override
    public String getEntityName() {
        return "Snake";
    }
}
