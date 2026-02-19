//package sousasinn;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class HManagementGUI {
//    public HManagementGUI() {
//        JFrame frame = new JFrame("Hospital Management System - Main Menu");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        frame.setLayout(new BorderLayout());
//
//        JLabel title = new JLabel("WeHealth", JLabel.CENTER);
//        title.setFont(new Font("Arial", Font.BOLD, 20));
//        frame.add(title, BorderLayout.NORTH);
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(5, 1, 5, 5));
//
//        JButton addPatientButton = new JButton("Add Patient");
//        JButton viewPatientsButton = new JButton("Patient Details");
//        JButton viewDoctorsButton = new JButton("Doctor Details");
//        JButton bookAppointmentButton = new JButton("Book Appointment");
//        JButton viewAppointmentsButton = new JButton("View Appointments");
//        JButton exitButton = new JButton("Exit");
//        
//        addPatientButton.setBackground(Color.PINK);
//        viewPatientsButton.setBackground(Color.PINK);
//        viewDoctorsButton.setBackground(Color.PINK);
//        bookAppointmentButton.setBackground(Color.PINK);
//        viewAppointmentsButton.setBackground(Color.PINK);
//        exitButton.setBackground(Color.PINK);
//
//        buttonPanel.add(addPatientButton);
//        buttonPanel.add(viewPatientsButton);
//        buttonPanel.add(viewDoctorsButton);
//        buttonPanel.add(bookAppointmentButton);
//        buttonPanel.add(viewAppointmentsButton);
//        buttonPanel.add(exitButton);
//
//        frame.add(buttonPanel, BorderLayout.CENTER);
//
//        // Button Listeners
//        addPatientButton.addActionListener(e -> new AddPatientForm());
//        viewPatientsButton.addActionListener(e -> new ViewPatientsForm());
//        viewDoctorsButton.addActionListener(e -> new ViewDoctorsForm());
//        bookAppointmentButton.addActionListener(e -> new BookAppointmentForm());
//        viewAppointmentsButton.addActionListener(e-> new ViewAppointmentsForm());
//        exitButton.addActionListener(e -> System.exit(0));
//
//        frame.setVisible(true);
//    }
//}



//package sousasinn;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class HManagementGUI {
//    public HManagementGUI() {
//        // --- Nimbus Look‑and‑Feel for a modern UI ---
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception ignored) {}
//
//        // --- Frame setup ---
//        JFrame frame = new JFrame("WeHealth – Hospital Management System");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().setBackground(new Color(245, 245, 245));
//        frame.setLayout(new BorderLayout(10, 10));
//
//        // --- Title/Header ---
//        JLabel title = new JLabel("WeHealth", SwingConstants.CENTER);
//        title.setFont(new Font("SansSerif", Font.BOLD, 24));
//        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
//        frame.add(title, BorderLayout.NORTH);
//
//        // --- Button panel ---
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(frame.getContentPane().getBackground());
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
//        buttonPanel.setLayout(new GridLayout(3, 2, 15, 15));
//
//        Font btnFont = new Font("SansSerif", Font.BOLD, 16);
//        Dimension btnSize = new Dimension(160, 40);
//        Color btnColor = new Color(40, 167, 69);    // green
//        Color hoverColor = new Color(33, 136, 56);  // darker green
//
//        JButton addPatientButton       = createStyledButton("Add Patient", btnFont, btnColor, hoverColor, btnSize);
//        JButton viewPatientsButton     = createStyledButton("Patient Details", btnFont, btnColor, hoverColor, btnSize);
//        JButton viewDoctorsButton      = createStyledButton("Doctor Details", btnFont, btnColor, hoverColor, btnSize);
//        JButton bookAppointmentButton  = createStyledButton("Book Appointment", btnFont, btnColor, hoverColor, btnSize);
//        JButton viewAppointmentsButton = createStyledButton("View Appointments", btnFont, btnColor, hoverColor, btnSize);
//        JButton exitButton             = createStyledButton("Exit", btnFont, new Color(220,53,69), new Color(180,45,55), btnSize);
//
//        buttonPanel.add(addPatientButton);
//        buttonPanel.add(viewPatientsButton);
//        buttonPanel.add(viewDoctorsButton);
//        buttonPanel.add(bookAppointmentButton);
//        buttonPanel.add(viewAppointmentsButton);
//        buttonPanel.add(exitButton);
//
//        frame.add(buttonPanel, BorderLayout.CENTER);
//
//        // --- Button listeners (unchanged) ---
//        addPatientButton.addActionListener(e -> new AddPatientForm());
//        viewPatientsButton.addActionListener(e -> new ViewPatientsForm());
//        viewDoctorsButton.addActionListener(e -> new ViewDoctorsForm());
//        bookAppointmentButton.addActionListener(e -> new BookAppointmentForm());
//        viewAppointmentsButton.addActionListener(e -> new ViewAppointmentsForm());
//        exitButton.addActionListener(e -> System.exit(0));
//
//        // --- Finalize ---
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    // Helper to create a styled JButton with hover effect
//    private JButton createStyledButton(String text, Font font, Color bg, Color hoverBg, Dimension size) {
//        JButton btn = new JButton(text);
//        btn.setFont(font);
//        btn.setBackground(bg);
//        btn.setForeground(Color.WHITE);
//        btn.setFocusPainted(false);
//        btn.setPreferredSize(size);
//        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        // Hover effect
//        btn.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                btn.setBackground(hoverBg);
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                btn.setBackground(bg);
//            }
//        });
//        return btn;
//    }
//}


package sousasinn;

import javax.swing.*;
import java.awt.*;

public class HManagementGUI {
    public HManagementGUI() {
        // Nimbus Look‑and‑Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        JFrame frame = new JFrame("WeHealth – Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setLayout(new BorderLayout(10, 10));

        // Header
        JLabel title = new JLabel("WeHealth", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        frame.add(title, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(frame.getContentPane().getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        buttonPanel.setLayout(new GridLayout(3, 2, 15, 15));

        Font btnFont = new Font("SansSerif", Font.BOLD, 16);
        Dimension btnSize = new Dimension(180, 40);
        Color btnColor = new Color(40, 167, 69);
        Color hoverColor = new Color(33, 136, 56);
        JButton addPatientButton       = createStyledButton("✚ Add Patient",         btnFont, btnColor, hoverColor, btnSize);
        JButton viewPatientsButton     = createStyledButton("✎ Patient Details",     btnFont, btnColor, hoverColor, btnSize);
        JButton viewDoctorsButton = createStyledButton(
        	    "👨‍⚕️ Doctor Details",
        	    btnFont, btnColor, hoverColor, btnSize
        	);
        JButton bookAppointmentButton  = createStyledButton("✆ Book Appointment",    btnFont, btnColor, hoverColor, btnSize);
//        JButton viewAppointmentsButton = createStyledButton("↻ View Appointments",   btnFont, btnColor, hoverColor, btnSize);
        JButton viewAppointmentsButton = createStyledButton("⌛ View Appointments", btnFont, btnColor, hoverColor, btnSize);

        JButton exitButton             = createStyledButton("✖ Exit",                btnFont, new Color(220,53,69), new Color(180,45,55), btnSize);

        buttonPanel.add(addPatientButton);
        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(viewDoctorsButton);
        buttonPanel.add(bookAppointmentButton);
        buttonPanel.add(viewAppointmentsButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Listeners
        addPatientButton.addActionListener(e -> new AddPatientForm());
        viewPatientsButton.addActionListener(e -> new ViewPatientsForm());
        viewDoctorsButton.addActionListener(e -> new ViewDoctorsForm());
        bookAppointmentButton.addActionListener(e -> new BookAppointmentForm());
        viewAppointmentsButton.addActionListener(e -> new ViewAppointmentsForm());
        exitButton.addActionListener(e -> System.exit(0));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color bg, Color hoverBg, Dimension size) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(size);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(hoverBg); }
            public void mouseExited(java.awt.event.MouseEvent evt)  { btn.setBackground(bg);      }
        });
        return btn;
    }
}

