package views;

import models.User;
import views.components.UserJListCustom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import configs.Configs;

import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Objects;

public class BoadGameGUI extends JFrame {

	
	private JPanel contentPane;
	public JPanel boardPanel;;
	public JLabel room_id, leaveRoomLabel, runLabel;
	public int Nrow = 9;
	public int Ncol = 16;
	public JButton buttons[][] = new JButton[this.Nrow][this.Ncol];
	public int board[][] = new int[this.Nrow][this.Ncol];
	public UserJListCustom userJListCustom;
	public String text = "0123456789";
	public int indexRunLabel = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoadGameGUI frame = new BoadGameGUI();

					frame.setVisible(true);
					ArrayList<User> users = new  ArrayList<>();
					for (int i = 0; i < 10; i++) {
						users.add(new User(1, "nvnam", "Nguyễn Văn Nam", "user1", i+1000, 1));
						users.add(new User(2, "haizuka", "Nguyễn Văn Hải", "user2", 1000, 2));
						users.add(new User(3, "huyhoang", "Nguyễn Huy Hoàng", "user3", 1000, 3));
						users.add(new User(4, "pvlong", "Phạm Văn Long", "user4", 1000, 4));
						users.add(new User(5, "chauanh", "Nguyễn Châu Anh", "user5", 1000, 5));
					}
					users.add(new User(6, "Nguyễn Văn Nam", "namz", "user1", 100, 1));
					frame.userJListCustom.setCurrentUser(users.get(users.size()-1));
					frame.userJListCustom.setUsers(users);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoadGameGUI() {
		setBounds(100, 100, 1039, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		boardPanel = new JPanel();
		boardPanel.setBackground(new Color(255, 182, 193));
		boardPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		boardPanel.setLayout(new GridLayout(9, 16));
		for (int i = 0; i < Nrow; i++)
			for (int j = 0; j < Ncol; j++) {
				int index = ((int) Math.round(Math.random() * 999999)) % 36 + 1;
				this.board[i][j] = index;
				buttons[i][j] = new JButton();
				buttons[i][j].setIcon(getIcon(index));
				buttons[i][j].setBorder(null);
				buttons[i][j].setActionCommand(i + " " + j);
				boardPanel.add(buttons[i][j]);
			}
		boardPanel.setBounds(272, 34, 709, 462);
		contentPane.add(boardPanel);
		JPanel ranking = new JPanel();
		ranking.setBackground(new Color(255, 182, 193));
		ranking.setBorder(new LineBorder(new Color(0, 0, 0)));
		ranking.setBounds(20, 34, 230, 462);
		ranking.setLayout(new BorderLayout());
		JLabel lblRanking = new JLabel("RANKING");
		lblRanking.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRanking.setHorizontalAlignment(JLabel.CENTER);
		ranking.add(lblRanking, BorderLayout.NORTH);
		userJListCustom = new UserJListCustom();
		ranking.add(userJListCustom.getScrollPane(), BorderLayout.CENTER);
		contentPane.add(ranking);
		room_id = new JLabel("Room ID: 03032000");
		room_id.setFont(new Font("Tahoma", Font.BOLD, 15));
		room_id.setBounds(32, 521, 234, 31);
		contentPane.add(room_id);

		leaveRoomLabel = new JLabel();
		leaveRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		leaveRoomLabel.setBounds(940, 510, 40, 40);
		leaveRoomLabel.setIcon(getImage( "/images/assets/exit.png", 40, 40));
		contentPane.add(leaveRoomLabel);

		runLabel = new JLabel("abc");
		runLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		runLabel.setBounds(272, 510, 640, 40);
		runLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0, 0, 0)));
		contentPane.add(runLabel);

		ranking.setBorder(null);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		this.setRunLabel("Xin chào HaiZuka, điểm của bạn là 1234. Cảm ơn bạn đã chơi trò chơi Pikachu Mutilplayer");

		Timer timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				indexRunLabel++;
				if (indexRunLabel > text.length())
					indexRunLabel = 0;
				String text = initText(indexRunLabel);
				runLabel.setText(text);
			}
		});
		timer.start();

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
		panel.setBounds(0, 0, 500, 500);
		contentPane.add(panel);


	}

	public String initText(int index) {
		String textTemp = this.text + " ";
		String textNew = textTemp.substring(index, textTemp.length());
		while(textNew.length() < Configs.TEXT_LENGTH)
			textNew = textNew + textTemp;
		return textNew;
	}

	public void setBoardFormArray(int board[][]) {
		this.board = board.clone();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] != 0) 
					this.buttons[i][j].setIcon(getIcon(board[i][j]));
				else {
					this.buttons[i][j].setIcon(null);
					this.buttons[i][j].setBackground(new Color(105, 105, 105));
				}
					
		}
	}

	public void setRoomIDLable(int id) {
		room_id.setText("Room ID: " + id);
		setTitle("Pikachu - Room " + id);
	}
	
	public Icon getIcon(int index) {
		int width = 52;
		int height = 56;
		Image image = new ImageIcon(getClass().getResource("/images/pokemon/pieces" + index + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
	public Icon getAvata(int index) {
		int width = 50;
		int height = 50;
		Image image = new ImageIcon(getClass().getResource("/images/avatas/" + index + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
	public Icon getImage(String path, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
		Image image = imageIcon.getImage();
		Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(newImage);
	}

	public void setRunLabel(String text) {
		this.text = text;
	}

	public int [][] getBoard() {
		return board;
	}
}