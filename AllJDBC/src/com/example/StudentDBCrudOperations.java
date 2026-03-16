package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDBCrudOperations {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1️⃣ Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2️⃣ Connect to database
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/acpproject", "vaishu", "Vaishu112004");

        Scanner sc = new Scanner(System.in);
        int operation;

        do {
            // 3️⃣ Display menu
            System.out.println("\nAvailable Operations:");
            System.out.println("1. Create");
            System.out.println("2. Retrieve");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter operation number: ");
            operation = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (operation) {
                case 1:
                    // CREATE
                    System.out.print("Enter first name: ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();

                    String createSql = "INSERT INTO Student (firstName, lastName, age, Grade) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(createSql)) {
                        stmt.setString(1, firstName);
                        stmt.setString(2, lastName);
                        stmt.setInt(3, age);
                        stmt.setString(4, grade);
                        int rows = stmt.executeUpdate();
                        System.out.println(rows > 0 ? "Student added successfully." : "Insert failed.");
                    }
                    break;

                case 2:
                    // RETRIEVE
                    String readSql = "SELECT * FROM Student";
                    try (Statement stmt = connection.createStatement();
                         ResultSet rs = stmt.executeQuery(readSql)) {
                        boolean hasRecords = false;
                        while (rs.next()) {
                            hasRecords = true;
                            int id = rs.getInt("student_id");
                            String fName = rs.getString("firstName");
                            String lName = rs.getString("lastName");
                            int studentAge = rs.getInt("age");
                            String studentGrade = rs.getString("Grade");
                            System.out.println("ID: " + id + ", Name: " + fName + " " + lName +
                                    ", Age: " + studentAge + ", Grade: " + studentGrade);
                        }
                        if (!hasRecords) {
                            System.out.println("No students found.");
                        }
                    }
                    break;

                case 3:
                    // UPDATE
                    System.out.print("Enter student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("1. Update Name");
                    System.out.println("2. Update Age");
                    System.out.println("3. Update Grade");
                    System.out.print("Enter choice: ");
                    int updateChoice = sc.nextInt();
                    sc.nextLine();

                    String updateSql = "";
                    PreparedStatement updateStmt = null;

                    switch (updateChoice) {
                        case 1:
                            System.out.print("Enter new first name: ");
                            String newFirst = sc.nextLine();
                            System.out.print("Enter new last name: ");
                            String newLast = sc.nextLine();
                            updateSql = "UPDATE Student SET firstName = ?, lastName = ? WHERE student_id = ?";
                            updateStmt = connection.prepareStatement(updateSql);
                            updateStmt.setString(1, newFirst);
                            updateStmt.setString(2, newLast);
                            updateStmt.setInt(3, updateId);
                            break;

                        case 2:
                            System.out.print("Enter new age: ");
                            int newAge = sc.nextInt();
                            updateSql = "UPDATE Student SET age = ? WHERE student_id = ?";
                            updateStmt = connection.prepareStatement(updateSql);
                            updateStmt.setInt(1, newAge);
                            updateStmt.setInt(2, updateId);
                            break;

                        case 3:
                            System.out.print("Enter new grade: ");
                            String newGrade = sc.nextLine();
                            updateSql = "UPDATE Student SET Grade = ? WHERE student_id = ?";
                            updateStmt = connection.prepareStatement(updateSql);
                            updateStmt.setString(1, newGrade);
                            updateStmt.setInt(2, updateId);
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }

                    if (updateStmt != null) {
                        int updated = updateStmt.executeUpdate();
                        System.out.println(updated > 0 ? "Student updated successfully." : "Update failed.");
                    }
                    break;

                case 4:
                    // DELETE
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = sc.nextInt();
                    String deleteSql = "DELETE FROM Student WHERE student_id = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(deleteSql)) {
                        stmt.setInt(1, deleteId);
                        int deleted = stmt.executeUpdate();
                        System.out.println(deleted > 0 ? "Student deleted successfully." : "Delete failed.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (operation != 5);

        sc.close();
        connection.close();
    }
}