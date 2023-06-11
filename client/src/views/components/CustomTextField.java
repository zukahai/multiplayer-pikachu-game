package views.components;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField  {
    public CustomTextField() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(243, 243, 243));
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Arial", Font.PLAIN, 15));

    }

    public void setPlaceholder(String enterRoomName) {
        this.setText(enterRoomName);
    }
}
