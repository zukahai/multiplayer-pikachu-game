package views.components;

import models.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

public class UserJListCustom {

    private JScrollPane scrollPane;
    private JPanel container;
    private JList<User> userList;
    private Vector<User> users;


    public UserJListCustom() {
        userList = new JList<>();
        userList.setCellRenderer(new JListRenderer( ));
        scrollPane = new JScrollPane(userList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

    }
    public void setCurrentUser(User user) {
        ((JListRenderer) userList.getCellRenderer()).setCurrentUser(user);
    }


    private void validateUserList() {
        this.users.sort((o1, o2) -> (int) (o2.getScore() - o1.getScore()));
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = new Vector<>(users);
        validateUserList();
        userList.setListData(this.users);
    }
}
