# Hospital-Management-System
📌 Project Overview

The Hospital Management System is a Java-based desktop application developed using Swing and JDBC to manage hospital operations efficiently.

The system allows administrators to manage patients, doctors, and appointments through a graphical user interface. All data is stored and retrieved using a MySQL database.

This project demonstrates the implementation of Object-Oriented Programming (OOP), GUI development, and database connectivity using JDBC.

🚀 Features
1. Admin Login System
2. Add New Patient
3. View Patient Records
4. View Doctor Details
5. Book Appointment
6. View Appointments
7. Database Connectivity using JDBC
8. Interactive GUI using Swing

🛠️ Technologies Used

1. Java
2. Swing (GUI)
3. MySQL
4. JDBC
5. Object-Oriented Programming

📂 Project Structure

sql
Hospital-Management-System/
│
├── LoginPage.java
├── HManagementGUI.java
├── hmanagement.java
│
├── Patient.java
├── Doctor.java
│
├── AddPatientForm.java
├── ViewPatientForm.java
├── ViewDoctorsForm.java
├── BookAppointmentForm.java
├── ViewAppointmentsForm.java
│
├── DatabaseConnection.java
└── README.md


🗄️ Database Setup

1. Create a database in MySQL:
CREATE DATABASE hospital_management;

2. Use database:
USE hospital_management;

3. Patients Table

  CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(20)
);

4. Doctors Table

CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    specialization VARCHAR(100)
);

5. Appointments Table

CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    appointment_date VARCHAR(20),
    app_time VARCHAR(20),
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

Update database credentials in DatabaseConnection.java

▶️ How to Run

1. Clone the repository
2. Open the project in your IDE
3. Configure MySQL database
4. Run LoginPage.java or hmanagement.java

👩‍💻 Author
Sukanya Kilabanur
