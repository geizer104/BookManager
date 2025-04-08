package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIHelper {
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(135, 206, 250));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        // Додавання ефекту наведення
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(102, 178, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(135, 206, 250));
            }
        });

        return button;
    }
}
