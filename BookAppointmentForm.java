package sousasinn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.*;

public class BookAppointmentForm {
    public BookAppointmentForm() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        JFrame frame = new JFrame("Book Appointment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.getContentPane().setBackground(new Color(250, 250, 250));
        frame.setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Schedule New Appointment", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Appointment Details",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);

        // Patient ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel patientLabel = new JLabel("Patient ID:");
        patientLabel.setFont(labelFont);
        formPanel.add(patientLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField patientIdField = new JTextField(15);
        patientIdField.setFont(fieldFont);
        formPanel.add(patientIdField, gbc);

        // Doctor ID
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel doctorLabel = new JLabel("Doctor ID:");
        doctorLabel.setFont(labelFont);
        formPanel.add(doctorLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField doctorIdField = new JTextField(15);
        doctorIdField.setFont(fieldFont);
        formPanel.add(doctorIdField, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setFont(labelFont);
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField dateField = new JTextField(15);
        dateField.setFont(fieldFont);
        formPanel.add(dateField, gbc);

        // Time
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setFont(labelFont);
        formPanel.add(timeLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField startTimeField = new JTextField(15);
        startTimeField.setFont(fieldFont);
        formPanel.add(startTimeField, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(frame.getContentPane().getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JButton bookButton = new JButton("Book Appointment");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookButton.setBackground(new Color(40, 167, 69));
        bookButton.setForeground(Color.WHITE);
        bookButton.setPreferredSize(new Dimension(180, 36));
        bookButton.setFocusPainted(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(120, 36));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(bookButton);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(cancelButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Book button action
        bookButton.addActionListener(e -> {
            try {
                int patientId = Integer.parseInt(patientIdField.getText().trim());
                int doctorId = Integer.parseInt(doctorIdField.getText().trim());
                String date = dateField.getText().trim();
                String time = startTimeField.getText().trim();

                try (Connection connection = DatabaseConnection.getConnection()) {
                    String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, app_time) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setInt(1, patientId);
                    ps.setInt(2, doctorId);
                    ps.setString(3, date);
                    ps.setString(4, time);
                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(frame, "Appointment booked successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error booking appointment. Please check inputs.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid numeric IDs.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame,
                        "Database error: " + ex.getMessage(),
                        "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Use pack instead of fixed size for better layout
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

