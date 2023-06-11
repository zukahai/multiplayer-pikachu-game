package views.components;

import models.Game;

import javax.swing.*;
import java.awt.*;

public class JListRendererRoom implements ListCellRenderer<Game> {
    private JPanel container, mainPanel;
    private JLabel roomNameLabel, roomSizeLabel, roomImage;
    private CustomJButton joinButton;

    public JListRendererRoom(Icon icon) {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.setBackground(new Color(255, 255, 255));

        roomImage = new JLabel();
        roomImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(roomImage);
        roomImage.setIcon(icon);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setBackground(new Color(255, 255, 255));

        roomNameLabel = new JLabel();
        roomNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        roomNameLabel.setForeground(new Color(0, 93, 218));
        roomNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(roomNameLabel);

        roomSizeLabel = new JLabel();
        roomSizeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        roomSizeLabel.setForeground(new Color(0, 93, 218));
        roomSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(roomSizeLabel);

        joinButton = new CustomJButton("Join");
        joinButton.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(joinButton);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        container.add(contentPanel);
        mainPanel.add(container, BorderLayout.CENTER);

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Game> list, Game value, int index, boolean isSelected, boolean cellHasFocus) {
        roomNameLabel.setText("Room Name: " + value.getRoomID());
        roomSizeLabel.setText("Room Size: " + value.getPlayers().size());
        return mainPanel;
    }
}
