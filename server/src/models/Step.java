package models;

import java.awt.Point;
import java.io.Serializable;

public class Step implements Serializable {
    private Point position1, position2;
    private int board[][];

    public Step() {
        this.position1 = new Point(0, 0);
        this.position2 = new Point(0, 0);
        this.board = new int[9][16];
    }

    public Step(Point position1, Point position2, int board[][]) {
        this.position1 = position1;
        this.position2 = position2;
        this.board = board;
    }

    public void setPosition1(Point position1) {
        this.position1 = position1;
    }

    public void setPosition2(Point position2) {
        this.position2 = position2;
    }

    public void setBoard(int board[][]) {
        this.board = board;
    }

    public Point getPosition1() {
        return position1;
    }

    public Point getPosition2() {
        return position2;
    }

    public int[][] getBoard() {
        return board;
    }

    public static void main(String[] args) {
        new Step();
    }


}
