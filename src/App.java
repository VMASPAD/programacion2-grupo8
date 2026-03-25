import java.util.Scanner;

public class App {
    private boolean running = true;
    private Excercise excercise;
    protected Scanner scanner;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App app = new App();
        app.run();
    }
    private void run() {
        scanner = new Scanner(System.in);
        while (running) {
            selectExcercise(scanner);
            excercise.run();
        }
    }
    private void selectExcercise(Scanner scanner){
        System.out.println("Select excercise or any to exit");
        int excerciseNumber = scanner.nextInt();
        switch (excerciseNumber) {
            case 1:
                excercise = new TestExcercise();
            case 2:
                exercise = new ListExercise(scanner);
            case 3:
                running = false;
        }
    }
}
