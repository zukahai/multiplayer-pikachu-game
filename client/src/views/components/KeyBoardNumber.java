package views.components;

import javax.swing.*;
import javax.swing.border.Border;

import utils.Util;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class KeyBoardNumber {
    public JFrame frame;
    public JPanel mainPanel, container, textFieldPanel;

    public String buttons[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0"};
    public CustomCalculatorTextField textField;
    public RoundButton acceptButton, deleteButton;

    public RoundButton roundButtons[] = new RoundButton[11];

    public KeyBoardNumber() {

        JLabel label = new JLabel("Enter IP Address");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(new Color(0, 93, 218));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        label.setHorizontalAlignment(JLabel.CENTER);
        container = new JPanel();
        container.add(label);
        container.setBackground(Color.WHITE);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new GridLayout(4, 3, 10, 10));
        textField = new CustomCalculatorTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 40));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(Color.WHITE);
        textField.setPreferredSize(new Dimension(400, 30));
        textField.setText(Util.getIPv4());
        textFieldPanel = new JPanel();
        textFieldPanel.setBackground(Color.WHITE);
        textFieldPanel.setLayout(new BorderLayout());

        deleteButton = new RoundButton("DEL");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setBackground(new Color(215, 50, 50));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textField.getText().length() > 0)
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1)
                    );

            }
        });


        container.add(textFieldPanel);
        for (int i = 0; i < buttons.length; i++) {
            RoundButton button = new RoundButton(buttons[i]);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            mainPanel.add(button);
            roundButtons[i] = button;
        }
        acceptButton = new RoundButton("OK");
        acceptButton.setFont(new Font("Arial", Font.BOLD, 20));
        acceptButton.setBackground(new Color(0, 132, 97));
        acceptButton.setForeground(Color.WHITE);
        mainPanel.add(acceptButton);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(mainPanel);


        textFieldPanel.add(textField, BorderLayout.WEST);
        textFieldPanel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.CENTER);
        textFieldPanel.add(deleteButton, BorderLayout.EAST);




        // demo action listener for button
        for (int i = 0; i < roundButtons.length; i++) {
            int finalI = i;
            roundButtons[i].addActionListener(e -> {
                textField.setText(textField.getText() + roundButtons[finalI].getText());
            });
        }
        
    }
    public JPanel getContainer() {
        return container;
    }

    public RoundButton getAccepButton() {
        return acceptButton;
    }

    public JTextField getTextField() {
        return textField;
    }



    public static void main(String[] args) {
        KeyBoardNumber keyBoardNumber = new KeyBoardNumber();
    }
}

class CustomCalculatorTextField extends JTextField {
    private static final int ARC_RADIUS = 10;
    private boolean isFocused = false;

    public CustomCalculatorTextField() {
        setProperties();
        setupFocusListener();
    }

    private void setProperties() {
        setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(ARC_RADIUS, new Color(185, 224, 226), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private void setupFocusListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
            }
        });

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                isFocused = true;
                repaint();
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                isFocused = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(getBackground());
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC_RADIUS, ARC_RADIUS);
        graphics2D.fill(roundRect);

        if (isFocused) {
            graphics2D.setColor(new Color(150, 150, 150));
            Stroke oldStroke = graphics2D.getStroke();
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.draw(roundRect);
            graphics2D.setStroke(oldStroke);
        }

        super.paintComponent(graphics2D);

        graphics2D.dispose();
    }
}

class RoundButton extends JButton {
    private static final int ARC_RADIUS = 10;
    private boolean isClicked = false;
    String text;

    public RoundButton(String text) {
        super(text);
        this.text = text;
        setProperties();
        setupClickListener();
    }

    private void setProperties() {
        setBackground(new Color(185, 224, 226));
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(new Color(0, 69, 121));
        setPreferredSize(new Dimension(80, 80));
        setBorder(null);
        setContentAreaFilled(false);
    }

    private void setupClickListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(new Color(165, 209, 211));
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (text.equals("OK")) {
                    setBackground(new Color(0, 132, 97));
                }
                else if (text.equals("DEL")) {
                    setBackground(new Color(215, 50, 50));
                }
                else {
                    setBackground(new Color(185, 224, 226));
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(getBackground());
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC_RADIUS, ARC_RADIUS);
        graphics2D.fill(roundRect);

        graphics2D.setColor(getForeground());
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int x = (getWidth() - fontMetrics.stringWidth(getText())) / 2;
        int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        graphics2D.drawString(getText(), x, y);

        graphics2D.dispose();
    }
}

class RoundedBorder implements Border {
    private int radius;
    private Color color;
    private int thickness;

    public RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.thickness, this.thickness, this.thickness, this.thickness);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(this.color);
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(x, y, width - 1, height - 1, this.radius, this.radius);
        graphics2D.draw(roundRect);

        graphics2D.dispose();
    }
}
