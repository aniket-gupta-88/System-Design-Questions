package Manager;

import Component.Dice;
import Component.GameBoard;
import Entities.BoardEntity;
import Entities.Cell;
import Entities.GameStatus;
import Entities.Player;

import java.util.*;

public class GameManager {

    private final GameBoard board;
    private final Dice dice;
    private final Queue<Player> turnQueue;
    private final List<Player> leaderboard;
    private GameStatus status;

    public GameManager(GameBoard board, Dice dice, List<Player> players){
        this.board = board;
        this.dice = dice;
        this.turnQueue = new LinkedList<>(players);
        this.leaderboard = new ArrayList<>();
        this.status = GameStatus.NOT_STARTED;
    }

    public synchronized void startGame(){
        if(status != GameStatus.NOT_STARTED){
            throw new IllegalStateException("Game is already initialized or running.");
        }
        this.status = GameStatus.IN_PROGRESS;
        System.out.println("The Snake & Ladder Game has officially commenced!");

        executeGameLoop();
    }

    private void executeGameLoop(){
        while (turnQueue.size() > 1){
            Player currentPlayer = turnQueue.poll();
            if(currentPlayer.isHasWon()) continue;

            int diceValue = dice.roll();
            System.out.println("\n🎲 " + currentPlayer.getName() + " rolled a " + diceValue);

            int oldPosition= currentPlayer.getCurrentPosition();
            int nextPosition = oldPosition + diceValue;

            if(nextPosition > board.getBoardSize()){
                System.out.println("⚠️ " + currentPlayer.getName() + " needs exactly " +
                        (board.getBoardSize() - oldPosition) + " to win. Staying at cell " + oldPosition);
                turnQueue.offer(currentPlayer);
                continue;
            }

            nextPosition = resolvePosition(nextPosition);
            currentPlayer.setCurrentPosition(nextPosition);
            System.out.println("🏃 " + currentPlayer.getName() + " transitioned: " + oldPosition + " ➡️ " + nextPosition);

            if(nextPosition == board.getBoardSize()){
                currentPlayer.setHasWon(true);
                leaderboard.add(currentPlayer);
                System.out.println("🏆 " + currentPlayer.getName() + " HAS REACHED THE FINISH LINE!");
            }
            else{
                turnQueue.offer(currentPlayer);
            }
        }

        if(!turnQueue.isEmpty()){
            Player lastPlayer = turnQueue.poll();
            leaderboard.add(lastPlayer);
        }

        this.status = GameStatus.COMPLETED;
        announceLeaderboard();
    }

    private int resolvePosition(int position){
        Set<Integer> visitedCells = new HashSet<>();
        int currentPos = position;

        while(currentPos < board.getBoardSize()){
            Cell cell = board.getCell(currentPos);
            if(cell == null || !cell.hasEntity()){
                break;
            }

            if(visitedCells.contains(currentPos)){
                System.out.println("🚨 Infinite loop detected at cell " + currentPos + "! Halting chain resolution.");
                break;
            }

            visitedCells.add(currentPos);

            BoardEntity modifier = cell.getEntity();
            System.out.println("💥 Encountered " + modifier.getEntityName() + " at cell " + currentPos);

            currentPos = modifier.getNewPosition();
        }
        return currentPos;
    }

    private void announceLeaderboard(){
        System.out.println("\n --- FINAL RANKINGS --- ");
        for(int i = 0; i < leaderboard.size(); i++){
            System.out.println((i + 1) + ". " + leaderboard.get(i).getName());
        }
    }

    public synchronized GameStatus getStatus() { return status; }
}
