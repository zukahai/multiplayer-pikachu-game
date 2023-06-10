package views;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;

public class BoadGameGUI extends JFrame {

	
	private JPanel contentPane;
	public JPanel boardPanel;
	private JPanel rank1, rank2, rank3, rank4, rank5;
	private JLabel name1, name2, name3, name4, name5;
	private JLabel score1, score2, score3, score4, score5;
	private JButton avata1, avata2, avata3, avata4, avata5;
	private JLabel room_id;
	public int Nrow = 9;
	public int Ncol = 16;
	public JButton buttons[][] = new JButton[this.Nrow][this.Ncol];
	public int board[][] = new int[this.Nrow][this.Ncol];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoadGameGUI frame = new BoadGameGUI();
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
	public BoadGameGUI() {
		setBounds(100, 100, 1039, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		boardPanel = new JPanel();
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
		ranking.setBounds(31, 34, 201, 462);
		contentPane.add(ranking);
		ranking.setLayout(null);
		Border blackline = BorderFactory.createTitledBorder("Rank");
		ranking.setBorder(blackline);
		
		
		rank1 = new JPanel();
		rank1.setBackground(new Color(255, 0, 0));
		rank1.setBorder(new LineBorder(new Color(0, 0, 0)));
		rank1.setBounds(10, 17, 181, 72);
		ranking.add(rank1);
		rank1.setLayout(null);
		
		avata1 = new JButton("");
		avata1.setBounds(10, 11, 52, 50);
		rank1.add(avata1);
		
		name1 = new JLabel("HaiZuka");
		name1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		name1.setBounds(72, 15, 99, 14);
		rank1.add(name1);
		
		score1 = new JLabel("1234");
		score1.setFont(new Font("Tahoma", Font.BOLD, 15));
		score1.setBounds(72, 40, 99, 14);
		rank1.add(score1);
		
		rank2 = new JPanel();
		rank2.setBackground(new Color(0, 255, 0));
		rank2.setLayout(null);
		rank2.setBorder(new LineBorder(new Color(0, 0, 0)));
		rank2.setBounds(10, 106, 181, 72);
		ranking.add(rank2);
		
		avata2 = new JButton("");
		avata2.setBounds(10, 11, 52, 50);
		rank2.add(avata2);
		
		name2 = new JLabel("LinhZukon");
		name2.setAlignmentY(1.0f);
		name2.setBounds(72, 15, 99, 14);
		rank2.add(name2);
		
		score2 = new JLabel("928");
		score2.setFont(new Font("Tahoma", Font.BOLD, 15));
		score2.setBounds(72, 40, 99, 14);
		rank2.add(score2);
		
		rank3 = new JPanel();
		rank3.setBackground(new Color(255, 255, 0));
		rank3.setLayout(null);
		rank3.setBorder(new LineBorder(new Color(0, 0, 0)));
		rank3.setBounds(10, 195, 181, 72);
		ranking.add(rank3);
		
		avata3 = new JButton("");
		avata3.setBounds(10, 11, 52, 50);
		rank3.add(avata3);
		
		name3 = new JLabel("NamZ");
		name3.setAlignmentY(1.0f);
		name3.setBounds(72, 15, 99, 14);
		rank3.add(name3);
		
		score3 = new JLabel("827");
		score3.setFont(new Font("Tahoma", Font.BOLD, 15));
		score3.setBounds(72, 40, 99, 14);
		rank3.add(score3);
		
		rank4 = new JPanel();
		rank4.setBackground(new Color(72, 209, 204));
		rank4.setLayout(null);
		rank4.setBorder(new LineBorder(new Color(0, 0, 0)));
		rank4.setBounds(10, 284, 181, 72);
		ranking.add(rank4);
		
		avata4 = new JButton("");
		avata4.setBounds(10, 11, 52, 50);
		rank4.add(avata4);
		
		name4 = new JLabel("GonPhan");
		name4.setAlignmentY(1.0f);
		name4.setBounds(72, 15, 99, 14);
		rank4.add(name4);
		
		score4 = new JLabel("802");
		score4.setFont(new Font("Tahoma", Font.BOLD, 15));
		score4.setBounds(72, 40, 99, 14);
		rank4.add(score4);
		
		rank5 = new JPanel();
		rank5.setBackground(new Color(72, 209, 204));
		rank5.setLayout(null);
		rank5.setBorder(new LineBorder(new Color(0, 0, 0)));
		rank5.setBounds(10, 373, 181, 72);
		ranking.add(rank5);
		
		avata5 = new JButton("");
		avata5.setBounds(10, 11, 52, 50);
		rank5.add(avata5);
		
		name5 = new JLabel("TuCoNy");
		name5.setAlignmentY(1.0f);
		name5.setBounds(72, 15, 99, 14);
		rank5.add(name5);
		
		score5 = new JLabel("793");
		score5.setFont(new Font("Tahoma", Font.BOLD, 15));
		score5.setBounds(72, 40, 99, 14);
		rank5.add(score5);
		
		room_id = new JLabel("Room ID: 03032000");
		room_id.setFont(new Font("Tahoma", Font.BOLD, 15));
		room_id.setBounds(32, 521, 234, 31);
		contentPane.add(room_id);
		
		this.updateRanking();
		
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
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
		this.setTitle("Pikachu - Room " + id);
	}
	
	public void updateRanking() {
		avata1.setIcon(getAvata(1));
		avata2.setIcon(getAvata(2));
		avata3.setIcon(getAvata(3));
		avata4.setIcon(getAvata(4));
		avata5.setIcon(getAvata(5));
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

	public int [][] getBoard() {
		return board;
	}
}