package views.components;

import models.User;

import javax.swing.*;
import java.awt.*;

public class JListRenderer implements ListCellRenderer<User> {

    private JPanel container, avatarPanel, detailPanel;
    private JLabel usernameLabel, nameLabel, scoreLabel, avatarLabel;
    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public JListRenderer() {
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255, 182, 193));
        avatarPanel = new JPanel();
        avatarPanel.setLayout(new BorderLayout());
        avatarPanel.setPreferredSize(new Dimension(70, 70));
        avatarPanel.setBackground(Color.WHITE);
        avatarLabel = new JLabel();
        avatarPanel.add(avatarLabel, BorderLayout.CENTER);
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(3, 1));
        detailPanel.setBackground(Color.WHITE);
        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameLabel.setForeground(new Color(0, 93, 218));
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setForeground(new Color(0, 93, 218));
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 12));
        scoreLabel.setForeground(new Color(215, 50, 50));
        detailPanel.add(usernameLabel);
        detailPanel.add(nameLabel);
        detailPanel.add(scoreLabel);
        detailPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        container.add(avatarPanel, BorderLayout.WEST);
        container.add(detailPanel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
        usernameLabel.setText(value.getUsername());
        nameLabel.setText(value.getName());
        scoreLabel.setText(String.valueOf((int) value.getScore()));
        avatarLabel.setIcon(getAvatar(value.getIdAvatar()));
        if (value.getUsername().equals(this.currentUser.getUsername())) {
                detailPanel.setBackground(new Color(136, 255, 160));
                nameLabel.setForeground(new Color(0, 93, 218));
                usernameLabel.setForeground(new Color(0, 93, 218));
                scoreLabel.setForeground(new Color(215, 50, 50));
        } else {
            detailPanel.setBackground(Color.WHITE);
            nameLabel.setForeground(new Color(0, 93, 218));
            usernameLabel.setForeground(new Color(0, 93, 218));
            scoreLabel.setForeground(new Color(215, 50, 50));
        }
        return container;
    }

    public Icon getAvatar(int index) {
        if (index <= 0 || index > 9) {
            index = 1;
        }

        int width = 70;
        int height = 70;
        Image image = new ImageIcon(getClass().getResource("/images/avatas/" + index + ".png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
        return icon;
    }
}
