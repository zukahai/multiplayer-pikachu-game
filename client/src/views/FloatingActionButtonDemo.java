package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloatingActionButtonDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Floating Action Button Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLayout(new BorderLayout());

            JPanel fabPanel = new JPanel();
            fabPanel.setLayout(new BoxLayout(fabPanel, BoxLayout.LINE_AXIS));
            fabPanel.setOpaque(false);

            // Đặt Box.Filler để đẩy nút lên góc dưới bên phải
            fabPanel.add(Box.createHorizontalGlue());

            FloatingActionButton fabButton = new FloatingActionButton();
            fabButton.setBackgroundColor(Color.BLUE);
            fabButton.setIcon("+");
            fabButton.setForeground(Color.WHITE);
            fabButton.addActionListener(e -> {
                // Xử lý sự kiện khi FAB được nhấp
                System.out.println("Floating Action Button clicked");
            });

            fabPanel.add(fabButton);
            frame.add(fabPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }

    static class FloatingActionButton extends JButton {
        private Color backgroundColor;
        private String icon;

        public void setBackgroundColor(Color color) {
            this.backgroundColor = color;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Vẽ background
            g2.setColor(backgroundColor);
            g2.fillOval(0, 0, getWidth(), getHeight());

            // Vẽ icon
            g2.setColor(getForeground());
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(icon)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2.drawString(icon, x, y);

            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(56, 56);
        }
    }
}
