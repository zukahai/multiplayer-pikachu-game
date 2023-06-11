package views.components;

import javax.swing.*;
import java.awt.*;

public class JComboboxImageCustom<Icon> extends JComboBox<Icon> {
    public JComboboxImageCustom() {
        super();
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(0, 93, 218));
        this.setBorder(null);
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setRenderer(new JComboboxImageCustomRenderer());

    }

    public void setImages(javax.swing.Icon[] icons) {
        for (javax.swing.Icon icon : icons) {
            this.addItem((Icon) icon);
        }

    }
}
class JComboboxImageCustomRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setIcon((Icon) value);
        return label;
    }
}