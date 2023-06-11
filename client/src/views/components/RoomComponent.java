package views.components;

import models.Game;
import utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RoomComponent {
    public JPanel mainPanel, container, contentPanel;
    public JLabel roomNameLabel, roomSizeLabel, roomImage;
    public Game game;
    public CustomJButton joinButton;


    public RoomComponent(Game game) {
        this.game = game;
    }


    public JPanel getContainer() {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setBackground(new Color(255, 255, 255));
        roomImage = new JLabel();
        roomImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(roomImage);
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setBackground(new Color(255, 255, 255));
        roomNameLabel = new JLabel("Room Name: " + game.getRoomID());
        roomNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        roomNameLabel.setForeground(new Color(0, 93, 218));
        roomNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(roomNameLabel);
        roomSizeLabel = new JLabel("Room Size: " + game.getPlayers().size());
        roomSizeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        roomSizeLabel.setForeground(new Color(0, 93, 218));
        roomSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(roomSizeLabel);
        joinButton = new CustomJButton("Join");
        joinButton.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(joinButton);
        container.add(contentPanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(container, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return mainPanel;
    }

    public CustomJButton getJoinButton() {
        return joinButton;
    }

    public void setIcon(Icon icon) {
        roomImage.setIcon(icon);
    }



}
