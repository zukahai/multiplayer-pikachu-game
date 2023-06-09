package models;

public class Game {
    private int board[][];

    public Game() {
        this.initBoard();
    }

    public Game(int board[][]) {
        this.board = board;
    }

    void initBoard() {
        int board[][] = new int[9][16];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = (int) (Math.round(Math.random() * 100000)) % 36 + 1;
            }
        }
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    
}
