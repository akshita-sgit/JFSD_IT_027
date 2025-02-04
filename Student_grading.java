import java.util.*;

// Interface for grading operations
interface GradingOperations {
    void addStudent(String studentID, String name);
    void addGrade(String studentID, double grade);
    void viewGrades(String studentID);
    double calculateAverage(String studentID);
}

// Abstract class representing a student
abstract class Student {
    protected String studentID;
    protected String name;
    protected List<Double> grades;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public List<Double> getGrades() { return grades; }
}

// Concrete class implementing grading operations
class GradingSystem extends Student implements GradingOperations {
    private static Map<String, GradingSystem> students = new HashMap<>();

    public GradingSystem(String studentID, String name) {
        super(studentID, name);
    }

    public void addStudent(String studentID, String name) {
        students.put(studentID, new GradingSystem(studentID, name));
        System.out.println("Student added successfully!");
    }

    public void addGrade(String studentID, double grade) {
        if (students.containsKey(studentID)) {
            students.get(studentID).grades.add(grade);
            System.out.println("Grade added successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    public void viewGrades(String studentID) {
        if (students.containsKey(studentID)) {
            System.out.println("Grades for " + students.get(studentID).name + ": " + students.get(studentID).grades);
        } else {
            System.out.println("Student not found!");
        }
    }

    public double calculateAverage(String studentID) {
        if (students.containsKey(studentID)) {
            List<Double> gradesList = students.get(studentID).grades;
            if (gradesList.isEmpty()) {
                System.out.println("No grades available for this student.");
                return 0.0;
            }
            double sum = 0;
            for (double grade : gradesList) sum += grade;
            return sum / gradesList.size();
        } else {
            System.out.println("Student not found!");
            return 0.0;
        }
    }
}

// Main class with a menu-driven system
class GradingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem gradingSystem = new GradingSystem("", "");

        while (true) {
            System.out.println("\nStudent Grading System:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Grades");
            System.out.println("4. Calculate Average");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    gradingSystem.addStudent(studentID, name);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    gradingSystem.addGrade(studentID, grade);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    gradingSystem.viewGrades(studentID);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    double avg = gradingSystem.calculateAverage(studentID);
                    System.out.println("Average Grade: " + avg);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}