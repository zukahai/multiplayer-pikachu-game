package views;

import views.components.KeyBoardNumber;

import javax.swing.*;

public class AddressIP {


    public JFrame frame;
    public KeyBoardNumber keyBoardNumber;
    public AddressIP(){
        keyBoardNumber = new KeyBoardNumber();
        frame = new JFrame("Address IP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(keyBoardNumber.getContainer());
        frame.setSize(530, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public JButton getAccepButton() {
        return keyBoardNumber.getAccepButton();
    }

    public JTextField getTextField() {
        return keyBoardNumber.getTextField();
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void dispose() {
        frame.dispose();
    }

    public static void main(String[] args) {
        AddressIP addressIP = new AddressIP();
    }
}
