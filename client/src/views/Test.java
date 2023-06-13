package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
    contentPane.add(panel);


		setContentPane(contentPane);
        
        this.setPreferredSize(new Dimension(450, 300));
        
	}

}