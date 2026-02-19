package sousasinn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddPatientForm {
    public AddPatientForm() {
        // --- Nimbus Look‑and‑Feel for modern UI ---
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        // --- Frame setup ---
        JFrame frame = new JFrame("Add Patient");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(250, 250, 250));
        frame.setLayout(new BorderLayout(10, 10));

        // --- Header ---
        JLabel header = new JLabel("New Patient Details", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(header, BorderLayout.NORTH);

        // --- Form panel ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Patient Information",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);

        // Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField nameField = new JTextField(15);
        nameField.setFont(fieldFont);
        formPanel.add(nameField, gbc);

        // Age
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        formPanel.add(ageLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField ageField = new JTextField(15);
        ageField.setFont(fieldFont);
        formPanel.add(ageField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        formPanel.add(genderLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setFont(fieldFont);
        formPanel.add(genderBox, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // --- Buttons panel ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(frame.getContentPane().getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JButton addButton = new JButton("Add Patient");
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        addButton.setBackground(new Color(40, 167, 69)); // green
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(140,36));
        addButton.setFocusPainted(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setBackground(new Color(108, 117, 125)); // gray
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(100,36));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalStrut(12));
        buttonPanel.add(cancelButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // --- Add action (unchanged) ---
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderBox.getSelectedItem();

            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hms", "root", "1234")) {
                String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, gender);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Patient added successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding patient.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // pack & show
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
