package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.BasicStroke;

public class LineDrawingPanel extends JPanel {
  
  private ArrayList<ArrayList<Point>> listPoints = new ArrayList<ArrayList<Point>>();

  public LineDrawingPanel(ArrayList<ArrayList<Point>> listPoints) {
    super();
    this.listPoints = listPoints;
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.RED);
    g2d.setStroke(new BasicStroke(3));

    for (ArrayList<Point> points : listPoints) {
        int x1 = points.get(0).x;
        int y1 = points.get(0).y;
        int x2 = points.get(1).x;
        int y2 = points.get(1).y;
        g2d.drawLine(x1, y1, x2, y2);
    }
}

  public static void main(String[] args) {
    JFrame frame = new JFrame("Line Drawing Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);


    ArrayList<ArrayList<Point>> listPoints = new ArrayList<ArrayList<Point>>();
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(50, 50));
    points.add(new Point(200, 200));
    listPoints.add(points);

     ArrayList<Point> points1 = new ArrayList<Point>();
    points1.add(new Point(200, 50));
    points1.add(new Point(200, 300));
    listPoints.add(points1);

    LineDrawingPanel panel = new LineDrawingPanel(listPoints);
    frame.add(panel);

    frame.setVisible(true);
  }
}
