
package sousasinn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAppointmentsForm {
    public ViewAppointmentsForm() {
        // --- Set Nimbus Look and Feel for a modern UI ---
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // if Nimbus is not available, fall back to default
        }

        // --- Frame setup ---
        JFrame frame = new JFrame("View Appointments");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245)); // very light gray
        frame.setLayout(new BorderLayout(10, 10));
        
        

        // --- Table setup ---
        String[] columnNames = {"ID", "Patient ID", "Doctor ID", "Date", "Time"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setGridColor(new Color(200, 200, 200));
        table.setFillsViewportHeight(true);
        
     // show the grid lines between cells
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(new Color(200, 200, 200));

        // add an outer border around the table
        table.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));


        // wrap in scroll pane with titled border
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)), 
            "Appointments List",
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14)
        ));
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- Load data (unchanged) ---
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM appointments");
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(frame, "No appointments found.", 
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                do {
                    model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("appointment_date"),
                        resultSet.getTime("app_time")
                    });
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, 
                "Error fetching appointments data:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }

     // --- Close button styling ---
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(220, 53, 69));  // Bootstrap “danger” red
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        // fixed the typo here: use 30 instead of "thirty"
        closeButton.setPreferredSize(new Dimension(100, 30));  
        closeButton.addActionListener(e -> frame.dispose());

        // wrap button in panel to add padding
        JPanel southPanel = new JPanel();
        southPanel.setBackground(frame.getContentPane().getBackground());
        southPanel.add(closeButton);
        frame.add(southPanel, BorderLayout.SOUTH);
        

        frame.setVisible(true);
    }
}



