import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrengthTrackerApp {

    private static List<Exercise> log = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Predefined movements so the user doesn't have to type the name every time
    private static final String[] MOVEMENTS = {
            "Bicep Curl",
            "Hammer Curl",
            "Overhead Tricep Extension"
    };

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addEntry();
                    break;
                case 2:
                    displayLog();
                    break;
                case 3:
                    System.out.println("Goodbye! Keep lifting.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Strength Training Tracker =====");
        System.out.println("1. Log a new set");
        System.out.println("2. View today's log");
        System.out.println("3. Exit");
    }

    private static void addEntry() {
        System.out.println("\nSelect a movement:");
        for (int i = 0; i < MOVEMENTS.length; i++) {
            System.out.println((i + 1) + ". " + MOVEMENTS[i]);
        }

        int movementChoice = readInt("Enter number: ");
        if (movementChoice < 1 || movementChoice > MOVEMENTS.length) {
            System.out.println("Invalid movement choice.");
            return;
        }

        String name = MOVEMENTS[movementChoice - 1];
        int sets = readInt("Sets: ");
        int reps = readInt("Reps per set: ");
        double weight = readDouble("Weight (kg): ");

        Exercise exercise = new Exercise(name, sets, reps, weight);
        log.add(exercise);

        System.out.println("Logged: " + exercise);
    }

    private static void displayLog() {
        if (log.isEmpty()) {
            System.out.println("\nNo entries logged yet.");
            return;
        }

        System.out.println("\n----- Today's Log -----");
        for (int i = 0; i < log.size(); i++) {
            System.out.println((i + 1) + ". " + log.get(i));
        }
    }

    // Helper to safely read an integer
    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clear the newline
        return value;
    }

    // Helper to safely read a double
    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}