package sousasinn;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPage {
    public static void main(String[] args) {
        new LoginPage();
    }

    public LoginPage() {
        JFrame frame = new JFrame("🏥 Hospital Management System - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI Emoji", Font.BOLD, 14);

        JLabel usernameLabel = new JLabel("👤 Username:");
        usernameLabel.setFont(emojiFont);
        JTextField usernameField = new JTextField();
        usernameField.setFont(fieldFont);
        usernameField.setPreferredSize(new Dimension(200, 28));

        JLabel passwordLabel = new JLabel("🔒 Password:");
        passwordLabel.setFont(emojiFont);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(new Dimension(200, 28));

        JButton loginButton = new JButton("🔓 Login");
        JButton cancelButton = new JButton("❎ Cancel");

        loginButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        // Add username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(usernameLabel, gbc);

        // Add username field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, gbc);

        // Add password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, gbc);

        // Add password field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, gbc);

        // Add login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(loginButton, gbc);

        // Add cancel button
        gbc.gridx = 1;
        panel.add(cancelButton, gbc);

        frame.add(panel, BorderLayout.CENTER);

        // Action listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "✅ Login Successful!");
                    frame.dispose();
                    new HManagementGUI();
                } else {
                    JOptionPane.showMessageDialog(frame, "❌ Invalid Username or Password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "❗ Database error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
