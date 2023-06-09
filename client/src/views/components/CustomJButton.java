package views.components;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {
    public CustomJButton( String text) {
        super(text);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(0, 157, 141));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setBorderPainted(false);
        this.setFocusPainted(false);


    }

}
