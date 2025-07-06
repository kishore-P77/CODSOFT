package codesoft.task2;

import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int[] marks = new int[5]; 
        
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();

            
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid! Enter again (0 to 100): ");
                marks[i] = scanner.nextInt();
            }

            total += marks[i];
        }

        double average = total / 5.0;
        double percentage = (total / 500.0) * 100;
        char grade;

        if (percentage >= 90) grade = 'A';
        else if (percentage >= 75) grade = 'B';
        else if (percentage >= 50) grade = 'C';
        else grade = 'F'; 

        System.out.println("\n--- Result ---");
        System.out.println("Total Marks: " + total + "/500");
        System.out.println("Average Marks: " + average);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
