package views.components;

import models.User;

import javax.swing.*;
import java.awt.*;

public class JListRenderer  implements ListCellRenderer<User> {

    private JPanel container, avatarPanel, detailPanel;
    private JLabel usernameLabel, nameLabel, scoreLabel, avatarLabel;


    public JListRenderer() {
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

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
        nameLabel = new JLabel();
        scoreLabel = new JLabel();
        detailPanel.add(usernameLabel);
        detailPanel.add(nameLabel);
        detailPanel.add(scoreLabel);
        detailPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        container.add(avatarPanel, BorderLayout.WEST);
        container.add(detailPanel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


    }


    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
        usernameLabel.setText(value.getUsername());
        nameLabel.setText(value.getName());
        scoreLabel.setText(String.valueOf(value.getScore()));
        avatarLabel.setIcon(getAvatar(value.getId_avatar()));
        if (isSelected) {
            container.setBorder(BorderFactory.createLineBorder(new Color(0, 157, 141), 2));
        } else {
            container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
