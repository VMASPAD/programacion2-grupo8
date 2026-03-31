import application.Excercise;
import application.ListExercise;
import application.TestExcercise;
import java.util.Scanner;

public class App {
    private boolean running = true;
    private Excercise excercise;
    protected Scanner scanner;

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        App app = new App();
        app.run();
    }

    private void run() {
        scanner = new Scanner(System.in);
        while (running) {
            selectExcercise(scanner);
            if (running) {
                excercise.run();
            }
        }
        scanner.close();
    }

    private void selectExcercise(Scanner scanner) {
        System.out.println("Select excercise or any other number to exit:");
        int excerciseNumber = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        switch (excerciseNumber) {
            case 1:
                excercise = new TestExcercise();
                break;
            case 2:
                excercise = new ListExercise(scanner);
                break;
            default:
                running = false;
                break;
        }
    }
}