import Component.Dice;
import Component.GameBoard;
import Entities.Ladder;
import Entities.Player;
import Entities.Snake;
import Manager.GameManager;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GameBoard board = new GameBoard(100);

        board.addEntity(new Snake(99, 54));
        board.addEntity(new Snake(70, 31));
        board.addEntity(new Snake(52, 29));
        board.addEntity(new Snake(25, 4));

        board.addEntity(new Ladder(3, 38));
        board.addEntity(new Ladder(11, 42));
        board.addEntity(new Ladder(41, 82));
        board.addEntity(new Ladder(65, 88));

        Dice standardDice = new Dice(1);

        List<Player> participants = Arrays.asList(
                new Player("P1", "Alice"),
                new Player("P2", "Bob"),
                new Player("P3", "Charlie")
        );

        GameManager matchEngine = new GameManager(board, standardDice, participants);

        matchEngine.startGame();
    }
}