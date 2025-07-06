package codesoft.task5;

import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private String email;

    public Student(String name, int rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toFileString() {
        return name + "," + rollNumber + "," + grade + "," + email;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null;
        return new Student(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
    }

    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade + ", Email: " + email;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(s -> s.getRollNumber() == rollNumber);
        saveToFile();
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) return s;
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void editStudent(int rollNumber, String name, String grade, String email) {
        Student s = searchStudent(rollNumber);
        if (s != null) {
            s.setName(name);
            s.setGrade(grade);
            s.setEmail(email);
            saveToFile();
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s != null) students.add(s);
            }
        } catch (IOException e) {
            
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            if (!input.matches("\\d")) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter roll number: ");
                    int roll = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("Fields cannot be empty.");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, email));
                        System.out.println("Student added.");
                    }
                    break;

                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = Integer.parseInt(scanner.nextLine());
                    sms.removeStudent(rollToRemove);
                    System.out.println("Student removed (if existed).");
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = Integer.parseInt(scanner.nextLine());
                    Student found = sms.searchStudent(rollToSearch);
                    System.out.println((found != null) ? found : "Student not found.");
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter roll number to edit: ");
                    int rollToEdit = Integer.parseInt(scanner.nextLine());
                    Student editStudent = sms.searchStudent(rollToEdit);
                    if (editStudent != null) {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new grade: ");
                        String newGrade = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        sms.editStudent(rollToEdit, newName, newGrade, newEmail);
                        System.out.println("Student info updated.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
