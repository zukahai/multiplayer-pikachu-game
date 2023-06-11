package views;

import views.components.CustomJButton;
import views.components.CustomJPasswordField;
import views.components.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Login {
    public JFrame jFrame;
    public JPanel container, inputPanel, buttonPanel;

    public CustomTextField usernameField;
    public CustomJPasswordField passwordField;
    public JLabel usernameLabel, passwordLabel, forgetLabel, registerLabel;
    public CustomJButton loginButton ;

    public JPanel getContainer() {
        container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setLayout(new BorderLayout());
        JLabel title = new JLabel("Login");
        JLabel image = new JLabel();
        JPanel titlePanel = new JPanel( new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(image, BorderLayout.SOUTH);
        image.setIcon(getImage( "/images/assets/login.jpg", 200, 200));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        container.add(titlePanel, BorderLayout.NORTH);
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setLayout(new GridLayout(0, 1));
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        usernameField = new CustomTextField();
        passwordField = new CustomJPasswordField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        loginButton = new CustomJButton("Login");
        JPanel forgetPanel = new JPanel();
        forgetPanel.setLayout(new GridLayout());
        forgetPanel.setBackground(Color.WHITE);
        registerLabel = new JLabel("Don't have an account? Sign up");
        registerLabel.setForeground(new Color(0, 93, 218));
        forgetPanel.add(registerLabel);
        forgetLabel = new JLabel("Forget Password?");
        forgetLabel.setForeground(new Color(255, 28, 54));
        forgetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        forgetPanel.add(forgetLabel);
        inputPanel.add(forgetPanel);
        inputPanel.add(buttonPanel);
        buttonPanel.add(loginButton);
        container.add(inputPanel, BorderLayout.CENTER);
        container.setBackground(Color.WHITE);
        return container;
    }

    public void init() {
        // JFrame frame = new JFrame("Login");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame = new JFrame("Login");
        jFrame.setContentPane(getContainer());
        jFrame.setSize(450, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public Icon getImage(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public void setVisible(boolean visible) {
        jFrame.setVisible(visible);
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.init();
    }


}
