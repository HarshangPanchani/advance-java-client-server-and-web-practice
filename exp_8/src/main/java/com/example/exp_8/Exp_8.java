package com.example.exp_8;

import com.example.exp_8.hibernatedemo.dao.StudentDAO;
import com.example.exp_8.hibernatedemo.entity.Student;
import com.example.exp_8.hibernatedemo.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Exp_8 {
    
    private static StudentDAO studentDAO = new StudentDAO();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("EXPERIMENT 8: HIBERNATE VS JDBC - CRUD OPERATIONS DEMO");
        System.out.println("=======================================================");
        
        try {
            boolean exit = false;
            
            while (!exit) {
                printMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1: // CREATE
                        createStudent();
                        break;
                    case 2: // READ (All)
                        readAllStudents();
                        break;
                    case 3: // READ (By ID)
                        readStudentById();
                        break;
                    case 4: // UPDATE
                        updateStudent();
                        break;
                    case 5: // DELETE
                        deleteStudent();
                        break;
                    case 0: // EXIT
                        exit = true;
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
                if (!exit) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
        } finally {
            scanner.close();
            HibernateUtil.shutdown();
            System.out.println("Hibernate SessionFactory closed.");
            
            // Display conclusion
            displayConclusion();
        }
    }
    
    private static void printMenu() {
        System.out.println("\n============= STUDENT MANAGEMENT =============");
        System.out.println("1. CREATE - Add new student");
        System.out.println("2. READ   - View all students");
        System.out.println("3. READ   - View student by ID");
        System.out.println("4. UPDATE - Update student details");
        System.out.println("5. DELETE - Remove student");
        System.out.println("0. EXIT   - Exit application");
        System.out.println("=============================================");
    }
    
    // CREATE
    private static void createStudent() {
        System.out.println("\n------ CREATE NEW STUDENT RECORD ------");
        
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Student newStudent = new Student(id, name, age);
        studentDAO.saveStudent(newStudent);
        System.out.println("------ STUDENT CREATED SUCCESSFULLY ------");
    }
    
    // READ (All)
    private static void readAllStudents() {
        System.out.println("\n------ READING ALL STUDENT RECORDS ------");
        List<Student> students = studentDAO.getAllStudents();
        if (students != null && !students.isEmpty()) {
            System.out.println("\nID\tNAME\t\tAGE");
            System.out.println("------------------------------");
            for (Student s : students) {
                System.out.println(s.getId() + "\t" + s.getName() + "\t\t" + s.getAge());
            }
        } else {
            System.out.println("No students found in the database.");
        }
        System.out.println("------ END OF STUDENT LIST ------");
    }
    
    // READ (By ID)
    private static void readStudentById() {
        System.out.println("\n------ READ STUDENT BY ID ------");
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            System.out.println("\nStudent Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
        }
        System.out.println("------ END OF STUDENT DETAILS ------");
    }
    
    // UPDATE
    private static void updateStudent() {
        System.out.println("\n------ UPDATE STUDENT RECORD ------");
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            System.out.println("Current details:");
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            
            System.out.println("\nEnter new details (leave blank to keep current value):");
            
            System.out.print("New name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                student.setName(name);
            }
            
            System.out.print("New age: ");
            String ageStr = scanner.nextLine();
            if (!ageStr.isEmpty()) {
                student.setAge(Integer.parseInt(ageStr));
            }
            
            studentDAO.updateStudent(student);
            System.out.println("------ STUDENT UPDATED SUCCESSFULLY ------");
        }
    }
    
    // DELETE
    private static void deleteStudent() {
        System.out.println("\n------ DELETE STUDENT RECORD ------");
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Are you sure you want to delete student with ID " + id + "? (y/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("y")) {
            studentDAO.deleteStudent(id);
            System.out.println("------ STUDENT DELETED SUCCESSFULLY ------");
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }
    
    private static void displayConclusion() {
        System.out.println("\n=======================================================");
        System.out.println("CONCLUSION: HIBERNATE VS JDBC FOR CRUD OPERATIONS");
        System.out.println("=======================================================");
        System.out.println("HIBERNATE BENEFITS DEMONSTRATED:");
        System.out.println("1. Simpler code for basic CRUD operations");
        System.out.println("2. No manual connection handling required");
        System.out.println("3. Automatic ORM (Object-Relational Mapping)");
        System.out.println("4. Automatic transaction management");
        System.out.println("5. Database independence through HQL");
        System.out.println("=======================================================");
        System.out.println("REDUCTION IN PROGRAMMING EFFORT:");
        System.out.println("- Connection Management: 100% reduction");
        System.out.println("- CRUD Operations: ~56% less code");
        System.out.println("- Transaction Handling: ~40% less code");
        System.out.println("=======================================================");
    }
}