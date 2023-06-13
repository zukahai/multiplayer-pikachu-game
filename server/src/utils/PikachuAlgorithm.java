package utils;

import java.awt.Point;
import java.util.ArrayList;

import configs.Configs;

public class PikachuAlgorithm {
    public static int rowMax = 9;
    public static int colMax = 16;
    public static ArrayList<Point> pointsTemp = new ArrayList<>();
    public static ArrayList<Point> pointsTemp2 = new ArrayList<>();
    public static ArrayList<Point> points = new ArrayList<>();

    public static boolean checkStep(int board[][], Point position1, Point position2) {
        PikachuAlgorithm.pointsTemp = new ArrayList<>();
        pointsTemp.add(position1);
        pointsTemp.add(position2);
        if (position1.y > position2.y) {
            Point temp = position1;
            position1 = position2;
            position2 = temp;
        }
        int row1 = position1.x;
        int col1 = position1.y;
        int row2 = position2.x;
        int col2 = position2.y;
        if (board[row1][col1] > Configs.LEVEL || board[row2][col2] > Configs.LEVEL)
            return false;
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
        if (PikachuAlgorithm.checkUZ(board, row1, col1, row2, col2))
            return true;
        return false;
    }

    public static boolean checkSideBySide(int board[][], int row1, int col1, int row2, int col2) {
        PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
        return (Math.abs(row1 - row2) + Math.abs(col1 - col2) == 1);
    }

    public static boolean checkInLine(int board[][], int row1, int col1, int row2, int col2) {
        PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
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
        PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
        if (col1 == col2 || row1 == row2)
            return false;
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

    public static boolean checkUZ(int board[][], int row1, int col1, int row2, int col2) {
        int result = 1000;
        for (int rowIndex = -1; rowIndex <= PikachuAlgorithm.rowMax; rowIndex++) {
            PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
            if (PikachuAlgorithm.checkZeroInline(board, rowIndex, col1, rowIndex, col2)) {
                boolean flag1 = false, flag2 = false;
                if (rowIndex < row1) {
                    if (PikachuAlgorithm.checkZeroInline(board, rowIndex, col1, row1 - 1, col1))
                        flag1 = true;
                } else {
                    if (PikachuAlgorithm.checkZeroInline(board, rowIndex, col1, row1 + 1, col1))
                        flag1 = true;
                }

                if (rowIndex < row2) {
                    if (PikachuAlgorithm.checkZeroInline(board, rowIndex, col2, row2 - 1, col2))
                        flag2 = true;
                } else {
                    if (PikachuAlgorithm.checkZeroInline(board, rowIndex, col2, row2 + 1, col2))
                        flag2 = true;
                }
                if (flag1 && flag2) {
                    if (Math.abs(rowIndex - (row1 + row2) / 2) < result) {
                        pointsTemp2 = new ArrayList<>(points);
                        result = rowIndex;
                    }
                }
            }
        }
        if (result != 1000) {
            points = new ArrayList<>(pointsTemp2);
            return true;
        }

        for (int colIndex = -1; colIndex <= PikachuAlgorithm.colMax; colIndex++) {
            PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
            if (PikachuAlgorithm.checkZeroInline(board, row1, colIndex, row2, colIndex)) {
                boolean flag1 = false, flag2 = false;
                if (colIndex < col1) {
                    if (PikachuAlgorithm.checkZeroInline(board, row1, colIndex, row1, col1 - 1))
                        flag1 = true;
                } else {
                    if (PikachuAlgorithm.checkZeroInline(board, row1, colIndex, row1, col1 + 1))
                        flag1 = true;
                }

                if (colIndex < col2) {
                    if (PikachuAlgorithm.checkZeroInline(board, row2, colIndex, row2, col2 - 1))
                        flag2 = true;
                } else {
                    if (PikachuAlgorithm.checkZeroInline(board, row2, colIndex, row2, col2 + 1))
                        flag2 = true;
                }
                if (flag1 && flag2) {
                    if (Math.abs(colIndex - (col1 + col2) / 2) < result) {
                        pointsTemp2 = new ArrayList<>(points);
                        result = colIndex;
                    }
                }
            }
        }
        if (result != 1000) {
            points = new ArrayList<>(pointsTemp2);
            return true;
        }
        return false;
    }

    public static boolean checkZeroInline(int board[][], int row1, int col1, int row2, int col2) {
        if (row1 == row2) {
            int min = Math.min(col1, col2);
            int max = Math.max(col1, col2);
            for (int i = min; i <= max; i++)
                if (PikachuAlgorithm.checkLocation(row1, i)) {
                    if (board[row1][i] != 0) {
                        PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
                        return false;
                    }
                    PikachuAlgorithm.points.add(new Point(row1, i));
                }
        } else {
            int min = Math.min(row1, row2);
            int max = Math.max(row1, row2);
            for (int i = min; i <= max; i++)
                if (PikachuAlgorithm.checkLocation(i, col1)){
                    if (board[i][col1] != 0) {
                        PikachuAlgorithm.points = new ArrayList<>(pointsTemp);
                        return false;
                    }
                    PikachuAlgorithm.points.add(new Point(i, col1));
                }
        }
        return true;
    }

    public static boolean checkLocation(int row, int col) {
        if (row < 0 || row >= PikachuAlgorithm.rowMax || col < 0 || col >= PikachuAlgorithm.colMax)
            return false;
        return true;
    }
}
