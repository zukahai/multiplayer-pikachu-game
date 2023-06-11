package views;

import models.Game;
import views.components.CustomTextField;
import views.components.JListRendererRoom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class Room {
    private JFrame jFrame;
    private JPanel container, contentPanel, searchPanel;
    private CustomTextField searchField;
    private JLabel searchLabel;
    private JList<Game> roomList;
    private DefaultListModel<Game> listModel;
    private ArrayList<Game> games = new ArrayList<>();

    private ArrayList<Game> gameSearch = new ArrayList<>();

    public Icon icon = getImage("/images/assets/game.jpg", 180, 130);

    private void createRoomList() {
        listModel = new DefaultListModel<>();
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchLabel = new JLabel("Search: ");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        searchLabel.setForeground(new Color(0, 93, 218));
        searchField = new CustomTextField();
        searchField.setFont(new Font("Arial", Font.BOLD, 15));
        searchField.setForeground(new Color(0, 93, 218));
        searchField.setToolTipText("Enter room name");
        searchField.setPlaceholder("Enter room name");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        contentPanel.add(searchPanel);
        roomList = new JList<>(listModel);
        roomList.setCellRenderer(new JListRendererRoom(icon));
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //4 room in a row
        roomList.setVisibleRowCount(-1);
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        contentPanel.add(scrollPane);
        container.add(contentPanel);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.setBackground(new Color(255, 255, 255));

        // Add event listener for roomList (demo)
        roomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = roomList.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    Game game = roomList.getModel().getElementAt(index);
                    JOptionPane.showConfirmDialog(null, "Join room " + game.getRoomID(), "Join room", JOptionPane.DEFAULT_OPTION);

                }
            }
        });

        // Add event listener for searchField (demo)
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String text = searchField.getText();
                if (text.equals("")) {
                    setRoomList(games);
                } else {
                    gameSearch.clear();
                    for (Game game : Room.this.games) {
                        if (String.valueOf(game.getRoomID()).contains(text)) {
                            gameSearch.add(game);
                        }
                    }
                    Vector<Game> gameVector = new Vector<>(gameSearch);
                    roomList.setListData(gameVector);
                }
            }
        });
    }

    public JPanel getContainer() {
        container = new JPanel();
        container.setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        createRoomList();

        container.add(contentPanel, BorderLayout.CENTER);
        return container;
    }


    public void setRoomList(ArrayList<Game> games) {
        this.games = games;
        listModel.clear();
        for (Game game : games) {
            listModel.addElement(game);
            this.roomList.setModel(listModel);
        }
    }

    private void initFrame() {
        jFrame = new JFrame();
        jFrame.setSize(858, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(getContainer());
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Room room = new Room();
            room.initFrame();
            ArrayList<Game> games = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                Game game = new Game();
                game.setRoomID(i + 1000);
                games.add(game);
            }
            room.setRoomList(games);
        });
    }

    public Icon getImage(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}
