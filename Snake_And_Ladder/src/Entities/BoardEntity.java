package Entities;

public interface BoardEntity {

    int getStartPoint();

    int getEndPoint();

    String getEntityName();

    default int getNewPosition(){
        return getEndPoint();
    }

}
