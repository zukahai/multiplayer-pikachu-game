package views.components;

import javax.swing.*;
import java.awt.*;

public class CustomJPasswordField extends JPasswordField {
    public CustomJPasswordField() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(243, 243, 243));
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Arial", Font.PLAIN, 15));
    }
}
