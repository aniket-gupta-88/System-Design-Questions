package Entities;

public class Ladder implements BoardEntity{

    private final int base;
    private final int top;

    public Ladder(int base, int top){
        if(base >= top){
            throw new IllegalArgumentException("Ladder top must be strictly higher than its base.");
        }
        this.base = base;
        this.top = top;
    }

    @Override
    public int getStartPoint() {
        return base;
    }

    @Override
    public int getEndPoint() {
        return top;
    }

    @Override
    public String getEntityName() {
        return "Ladder";
    }
}
