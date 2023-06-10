package utils;

import java.awt.Point;

public class PikachuAlgorithm {
    public static boolean checkStep(int board[][], Point position1, Point position2) {
        if (position1.y > position2.y) {
            Point temp = position1;
            position1 = position2;
            position2 = temp;
        }
        int row1 = position1.x;
        int col1 = position1.y;
        int row2 = position2.x;
        int col2 = position2.y;
        if (board[row1][col1] == 0 || board[row2][col2] == 0) 
            return false;
        if (row1 == row2 && col1 == col2)
            return false;
        if (board[row1][col1] != board[row2][col2])
            return false;

        if (PikachuAlgorithm.checkSideBySide(board, row1, col1, row2, col2))
            return true;
        if (PikachuAlgorithm.checkInLine(board, row1, col1, row2, col2))
            return true;
        if (PikachuAlgorithm.checkL(board, row1, col1, row2, col2))
            return true;
        return false;
    }

    public static boolean checkSideBySide(int board[][], int row1, int col1, int row2, int col2) {
        return (Math.abs(row1 - row2) + Math.abs(col1 - col2) == 1);
    }

    public static boolean checkInLine(int board[][], int row1, int col1, int row2, int col2) {
        if (row1 != row2 && col1 != col2)
            return false;
        if (row1 == row2) {
            int min = Math.min(col1, col2);
            int max = Math.max(col1, col2);
            return PikachuAlgorithm.checkZeroInline(board, row1, min + 1, row2, max - 1);
        } else {
            int min = Math.min(row1, row2);
            int max = Math.max(row1, row2);
            return PikachuAlgorithm.checkZeroInline(board, min + 1, col1, max - 1, col2);
        }
    }

    public static boolean checkL(int board[][], int row1, int col1, int row2, int col2) {
        if (col1 == col2 || row1 == row2)
            return false;
        boolean flag = true;
        if  (row1 < row2) {
            //  L
            if (PikachuAlgorithm.checkZeroInline(board, row1 + 1, col1, row2, col1) &&
            PikachuAlgorithm.checkZeroInline(board, row2, col1, row2, col2 - 1))
                return true;
            //  _
            //  |
            if (PikachuAlgorithm.checkZeroInline(board, row1, col1 + 1, row1, col2) &&
            PikachuAlgorithm.checkZeroInline(board, row1, col2, row2 - 1, col2))
                return true;
        } else {
           //  _
           // |
           if (PikachuAlgorithm.checkZeroInline(board, row2, col1, row2, col2 - 1) &&
           PikachuAlgorithm.checkZeroInline(board, row2, col1, row1 - 1, col1))
               return true;
            //  _|
            if (PikachuAlgorithm.checkZeroInline(board, row1, col2, row1, col1 + 1) &&
            PikachuAlgorithm.checkZeroInline(board, row1, col2, row2 + 1, col2))
                return true;
        }
        return false;
    }

    public static boolean checkZeroInline(int board[][], int row1, int col1, int row2, int col2) {
        if (row1 == row2) {
            int min = Math.min(col1, col2);
            int max = Math.max(col1, col2);
            for (int i = min; i <= max; i++) {
                if (board[row1][i] != 0)
                    return false;
            }
        } else {
            int min = Math.min(row1, row2);
            int max = Math.max(row1, row2);
            for (int i = min; i <= max; i++) {
                if (board[i][col1] != 0)
                    return false;
            }
        }
        return true;
    }
}
