import model.GameStatus;

public class PlayGame {
    public static void main(String[] args) {
        System.out.println("\n=====> TicTacToe Game \n");
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        GameStatus status = game.startGame();
        System.out.println("\n=====> Game Over:  ");
        switch (status){
            case WIN:
                System.out.println(game.winner.name + ":- won the game");
                break;
            case DRAW:
                System.out.println("It's a Draw");
                break;
            default:
                System.out.println("Game Ends");
                break;
        }
    }
}
