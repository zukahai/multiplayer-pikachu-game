package models;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Step implements Serializable {
    private Point2D position1, position2;
    private int board[][];

    public Step(Point2D position1, Point2D position2, int board[][]) {
        this.position1 = position1;
        this.position2 = position2;
        this.board = board;
    }

    public void setPosition1(Point2D position1) {
        this.position1 = position1;
    }

    public void setPosition2(Point2D position2) {
        this.position2 = position2;
    }

    public void setBoard(int board[][]) {
        this.board = board;
    }

    public Point2D getPosition1() {
        return position1;
    }

    public Point2D getPosition2() {
        return position2;
    }

    public int[][] getBoard() {
        return board;
    }


}
