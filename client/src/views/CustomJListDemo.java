package views;

import javax.swing.*;
import java.awt.*;

public class CustomJListDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom JList Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("Item 1");
            listModel.addElement("Item 2");
            listModel.addElement("Item 3");


            JList<String> list = new JList<>(listModel);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(2);
            list.setFixedCellWidth(150);
            list.setFixedCellHeight(50);
            list.setCellRenderer(new CustomListCellRenderer());

            frame.add(new JScrollPane(list));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
            return component;
        }
    }
}
