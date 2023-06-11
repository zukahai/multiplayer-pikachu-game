package views;

import views.components.KeyBoardNumber;

import javax.swing.*;

public class AddressIP {


    public JFrame frame;
    public KeyBoardNumber keyBoardNumber;
    public AddressIP(){
        keyBoardNumber = new KeyBoardNumber();
        frame = new JFrame("Address IP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(keyBoardNumber.getContainer());
        frame.setSize(530, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AddressIP addressIP = new AddressIP();
    }
}
