package com.example.registration.app;

import com.example.registration.entity.Student;
import com.example.registration.entity.Course;
import com.example.registration.entity.Registration;
import com.example.registration.exception.DuplicateRegistrationException;
import com.example.registration.exception.EntityNotFoundException;
import com.example.registration.service.StudentService;
import com.example.registration.service.CourseService;
import com.example.registration.service.RegistrationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final RegistrationService registrationService = new RegistrationService();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Course Registration System ===");

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> addCourse();
                    case 4 -> viewCourses();
                    case 5 -> registerStudentToCourse();
                    case 6 -> viewRegistrationsByStudent();
                    case 7 -> viewRegistrationsByCourse();
                    case 8 -> deleteRegistration();
                    case 9 -> exit = true;
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (EntityNotFoundException | DuplicateRegistrationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Exiting system. Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Add Course");
        System.out.println("4. View Courses");
        System.out.println("5. Register Student to Course");
        System.out.println("6. View Registrations by Student");
        System.out.println("7. View Registrations by Course");
        System.out.println("8. Delete Registration");
        System.out.println("9. Exit");
    }

    private static void addStudent() {
        System.out.println("=== Add Student ===");
        String name = readString("Enter student name: ");
        String email = readString("Enter student email: ");
        LocalDate enrollmentDate = LocalDate.now();

        Student student = new Student(name, email, enrollmentDate);
        studentService.addStudent(student);
        System.out.println("Student added successfully! ID: " + student.getId());
    }

    private static void viewStudents() {
        System.out.println("=== Students List ===");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void addCourse() {
        System.out.println("=== Add Course ===");
        String title = readString("Enter course title: ");
        String description = readString("Enter course description: ");
        int credits = readInt("Enter credits: ");

        Course course = new Course(title, description, credits);
        courseService.addCourse(course);
        System.out.println("Course added successfully! ID: " + course.getId());
    }

    private static void viewCourses() {
        System.out.println("=== Courses List ===");
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private static void registerStudentToCourse() {
        System.out.println("=== Register Student to Course ===");
        long studentId = readLong("Enter student ID: ");
        long courseId = readLong("Enter course ID: ");

        registrationService.registerStudentToCourse(studentId, courseId);
        System.out.println("Student registered successfully!");
    }

    private static void viewRegistrationsByStudent() {
        System.out.println("=== Registrations by Student ===");
        long studentId = readLong("Enter student ID: ");
        List<Registration> registrations = registrationService.getRegistrationsByStudent(studentId);
        if (registrations.isEmpty()) {
            System.out.println("No registrations found for this student.");
        } else {
            registrations.forEach(System.out::println);
        }
    }

    private static void viewRegistrationsByCourse() {
        System.out.println("=== Registrations by Course ===");
        long courseId = readLong("Enter course ID: ");
        List<Registration> registrations = registrationService.getRegistrationsByCourse(courseId);
        if (registrations.isEmpty()) {
            System.out.println("No registrations found for this course.");
        } else {
            registrations.forEach(System.out::println);
        }
    }

    private static void deleteRegistration() {
        System.out.println("=== Delete Registration ===");
        long registrationId = readLong("Enter registration ID: ");
        registrationService.deleteRegistration(registrationId);
        System.out.println("Registration deleted successfully!");
    }

    // Helper methods
    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }

    private static long readLong(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            scanner.next();
            System.out.print(prompt);
        }
        long val = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return val;
    }
}
