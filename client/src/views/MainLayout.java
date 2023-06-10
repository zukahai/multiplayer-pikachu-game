package views;

import models.User;
import views.components.JListRenderer;
import views.components.UserJListCustom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class MainLayout {
    public JFrame jFrame;
    public JPanel container, leftPanel, rightPanel;
    public UserJListCustom userJListCustom;
    public JPanel getContainer() {
        container = new JPanel();
        container.setLayout(new GridLayout(1, 2));
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, 600));
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(600, 600));
        userJListCustom = new UserJListCustom();
        leftPanel.add(userJListCustom.getScrollPane(), BorderLayout.CENTER);
        container.add(leftPanel);
        container.add(rightPanel);
        container.add(new JPanel());
        return container;
    }



    public void init() {
        jFrame = new JFrame("Main Layout");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(getContainer());
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MainLayout mainLayout = new MainLayout();
        mainLayout.init();
        ArrayList<User> users = new  ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(1, "nvnam", "Nguyễn Văn Nam", "user1", 1000, 1));
            users.add(new User(2, "haizuka", "Nguyễn Văn Hải", "user2", 1000, 2));
            users.add(new User(3, "huyhoang", "Nguyễn Huy Hoàng", "user3", 1000, 3));
            users.add(new User(4, "pvlong", "Phạm Văn Long", "user4", 1000, 4));
            users.add(new User(5, "chaubuoi", "Nguyễn Châu Buồi", "user5", 1000, 5));
        }
        mainLayout.userJListCustom.setUsers(users);
    }
}

