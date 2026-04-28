import application.ConsumerApplicationExercise;
import application.Exercise;
import application.InventoryExercise;
import application.ListExercise;
import application.ListImplementationExercise;
import application.QueueExercise;
import application.SetExercise;
import application.StackExercise;
import application.TestExercise;
import java.util.Scanner;

public class App {
    private boolean running = true;
    private Exercise exercise;
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
                exercise.run();
            }
        }
        scanner.close();
    }

    private int readSafeInteger() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Entrada inválida. Ingresa un número: ");
            }
        }
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
        System.out.println("║ 7. TP6: Aplicación Atención Consumidor║");
        System.out.println("║ 8. TP7: Sistema de Inventario        ║");
        System.out.println("║ 0. Salir                             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("▶ Selecciona una opción: ");
        
        int choice = readSafeInteger();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                exercise = new TestExercise();
                break;
            case 2:
                exercise = new ListExercise(scanner);
                break;
            case 3:
                exercise = new ListImplementationExercise(scanner);
                break;
            case 4:
                exercise = new StackExercise(scanner);
                break;
            case 5:
                exercise = new QueueExercise(scanner);
                break;
            case 6:
                exercise = new SetExercise(scanner);
                break;
            case 7:
                exercise = new ConsumerApplicationExercise(scanner);
                break;
            case 8:
                exercise = new InventoryExercise(scanner);
                break;
            case 0:
                System.out.println("\n¡Programa Terminado!");
                running = false;
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.\n");
                displayMainMenu();
                break;
        }
    }
}