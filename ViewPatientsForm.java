//package sousasinn;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//
//import java.awt.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class ViewPatientsForm {
//    public ViewPatientsForm() {
//        JFrame frame = new JFrame("View Patients");
//        frame.setSize(400, 300);
//        frame.setLayout(new BorderLayout());
//
//        String[] columnNames = {"ID", "Name", "Age", "Gender"};
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//        JTable table = new JTable(model);
//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "1234");
//             Statement statement = connection.createStatement()) {
//            String query = "SELECT * FROM patients";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                String gender = resultSet.getString("gender");
//                model.addRow(new Object[]{id, name, age, gender});
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        frame.add(new JScrollPane(table), BorderLayout.CENTER);
//
//        JButton closeButton = new JButton("Close");
//        closeButton.addActionListener(e -> frame.dispose());
//        frame.add(closeButton, BorderLayout.SOUTH);
//
//        frame.setVisible(true);
//    }
//}



package sousasinn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewPatientsForm {
    public ViewPatientsForm() {
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
        JFrame frame = new JFrame("View Patients");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setLayout(new BorderLayout(10, 10));

        // --- Header ---
        JLabel header = new JLabel("Patients Directory", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(header, BorderLayout.NORTH);

        // --- Table setup ---
        String[] columnNames = {"ID", "Name", "Age", "Gender"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 200, 200));
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            "Registered Patients",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14)
        ));
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- Load data (unchanged) ---
        try (Connection connection = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/hms", "root", "1234");
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT * FROM patients");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                "Error fetching patients' data:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Close button styling ---
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(220, 53, 69));  // Bootstrap “danger” red
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.addActionListener(e -> frame.dispose());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(frame.getContentPane().getBackground());
        southPanel.add(closeButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        // --- Finalize ---
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
