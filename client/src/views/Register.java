package views;

import views.components.CustomJButton;
import views.components.CustomJPasswordField;
import views.components.CustomTextField;
import views.components.JComboboxImageCustom;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Register {
    public JFrame jFrame;
    public JPanel container, inputPanel, buttonPanel;

    public CustomTextField usernameField, nameField;
    public CustomJPasswordField passwordField, confirmPasswordField;
    public JLabel usernameLabel, passwordLabel, nameLabel, forgetLabel, registerLabel, confirmPasswordLabel, chooseAvatarLabel;
    public CustomJButton registerButton;

    public int avatarIds[] = {1, 2, 3, 4, 5};

    public Icon icons[] = {
            getAvatar(1),
            getAvatar(2),
            getAvatar(3),
            getAvatar(4),
            getAvatar(5)
    };

    public JComboboxImageCustom<String> roleCombobox;

    public JPanel getContainer() {
        container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setLayout(new BorderLayout());
        JLabel title = new JLabel("Register");
        JLabel image = new JLabel();
        JPanel titlePanel = new JPanel( new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(image, BorderLayout.SOUTH);
        image.setIcon(getImage( "/images/assets/login.jpg", 150, 150));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        container.add(titlePanel, BorderLayout.NORTH);
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setLayout(new GridLayout(0, 1));
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        confirmPasswordField = new CustomJPasswordField();
        confirmPasswordLabel = new JLabel("Confirm Password");
        nameLabel = new JLabel("Name");
        usernameField = new CustomTextField();
        nameField = new CustomTextField();
        passwordField = new CustomJPasswordField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(confirmPasswordLabel);
        inputPanel.add(confirmPasswordField);
        chooseAvatarLabel = new JLabel("Choose Avatar");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        registerButton = new CustomJButton("Register");
        JPanel forgetPanel = new JPanel();
        forgetPanel.setLayout(new GridLayout());
        forgetPanel.setBackground(Color.WHITE);
        registerLabel = new JLabel("");
        forgetPanel.add(registerLabel);
        forgetLabel = new JLabel("Have an account? Login");
        forgetLabel.setForeground(new Color(255, 28, 54));
        forgetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        forgetPanel.add(forgetLabel);
        inputPanel.add(forgetPanel);
        inputPanel.add(buttonPanel);
        buttonPanel.add(registerButton);
        container.add(inputPanel, BorderLayout.CENTER);
        container.setBackground(Color.WHITE);
        return container;
    }

    public void init() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(getContainer());
        frame.setSize(450, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Icon getImage(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
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

    public static void main(String[] args) {
        Register register = new Register();
        register.init();
    }


}
