import application.Excercise;
import application.ListExercise;
import application.ListImplementationExercise;
import application.QueueExercise;
import application.SetExercise;
import application.StackExercise;
import application.TestExcercise;
import java.util.Scanner;

public class App {
    private boolean running = true;
    private Excercise excercise;
    protected Scanner scanner;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    // Inicia el ciclo principal: muestra menú, ejecuta ejercicio seleccionado, hasta que el usuario salga
    private void run() {
        scanner = new Scanner(System.in);
        while (running) {
            displayMainMenu();
            if (running) {
                excercise.run();
            }
        }
        scanner.close();
    }

    // Recibe entrada del usuario, muestra opciones de ejercicios, instancia el ejercicio seleccionado
    private void displayMainMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║          MENÚ PRINCIPAL              ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Test Básico                       ║");
        System.out.println("║ 2. Ejercicio con ArrayList           ║");
        System.out.println("║ 3. Ejercicio con SimpleList          ║");
        System.out.println("║ 4. Ejercicio con SimpleStack         ║");
        System.out.println("║ 5. Ejercicio con SimpleQueue         ║");
        System.out.println("║ 6. Ejercicio con SimpleSet           ║");
        System.out.println("║ 0. Salir                             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("▶ Selecciona una opción: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                excercise = new TestExcercise();
                break;
            case 2:
                excercise = new ListExercise(scanner);
                break;
            case 3:
                excercise = new ListImplementationExercise(scanner);
                break;
            case 4:
                excercise = new StackExercise(scanner);
                break;
            case 5:
                excercise = new QueueExercise(scanner);
                break;
            case 6:
                excercise = new SetExercise(scanner);
                break;
            case 0:
                System.out.println("\n👋 ¡Hasta luego!");
                running = false;
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.\n");
                displayMainMenu();
                break;
        }
    }
}