package application;

import java.util.Scanner;
import queueModule.SimpleArrayQueue;
import queueModule.SimpleLinkedQueue;
import queueModule.SimpleQueue;

public class QueueExercise extends Excercise {
    private int currentPhase = 0;
    private SimpleQueue<String> queue;
    private String implementationType;
    private boolean showWelcome = true;

    public QueueExercise(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void excerciseLogic() {
        switch (currentPhase) {
            case 0:
                selectImplementation();
                break;
            case 1:
                mainMenu();
                break;
            case 2:
                enqueueElement();
                break;
            case 3:
                dequeueElement();
                break;
            case 4:
                peekElement();
                break;
            case 5:
                clearQueue();
                break;
        }
    }

    // Recibe entrada del usuario para elegir entre SimpleArrayQueue o SimpleLinkedQueue
    private void selectImplementation() {
        System.out.println("\n=== Seleccionar Implementación de Cola ===");
        System.out.println("1. SimpleArrayQueue (Basado en Array Circular)");
        System.out.println("2. SimpleLinkedQueue (Basado en Nodos Enlazados)");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                queue = new SimpleArrayQueue<>();
                implementationType = "SimpleArrayQueue";
                currentPhase = 1;
                break;
            case 2:
                queue = new SimpleLinkedQueue<>();
                implementationType = "SimpleLinkedQueue";
                currentPhase = 1;
                break;
            default:
                System.out.println("Opción inválida. Intenta nuevamente.");
                break;
        }
    }
    // Recibe entrada de usuario, muestra menú de operaciones (enqueue, dequeue, peek, clear)   
 private void mainMenu() {
        if (showWelcome) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   Ejercicio de Colas - " + implementationType);
            System.out.println("╚════════════════════════════════════╝");
            showWelcome = false;
        }

        displayQueueStatus();

        System.out.println("\n┌─ Opciones ─────────────────────────┐");
        System.out.println("│ 1. Enqueue (Encolar elemento)");
        System.out.println("│ 2. Dequeue (Desencolar elemento)");
        System.out.println("│ 3. Peek (Ver primero)");
        System.out.println("│ 4. Clear (Limpiar cola)");
        System.out.println("│ 0. Salir");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Selecciona una opción: ");

        Boolean validChoice = false;
        int choice;
        while(!validChoice) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Tiene que ser un numero!");
                continue;
            }

            validChoice = true;

        switch (choice) {
            case 1:
                currentPhase = 2;
                break;
            case 2:
                currentPhase = 3;
                break;
            case 3:
                currentPhase = 4;
                break;
            case 4:
                currentPhase = 5;
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.");
                break;
        }
        }
    }

    private void enqueueElement() {
        System.out.print("\n▶ Ingresa el elemento a encolar: ");
        String element = scanner.nextLine();
        queue.enqueue(element);
        System.out.println("✓ Elemento encolado correctamente.");
        currentPhase = 1;
    }

    private void dequeueElement() {
        if (queue.isEmpty()) {
            System.out.println("\n❌ La cola está vacía.");
            currentPhase = 1;
            return;
        }

        try {
            String dequeued = queue.dequeue();
            System.out.println("\n✓ Elemento desencolado: \"" + dequeued + "\"");
        } catch (IllegalStateException e) {
            System.out.println("\n❌ " + e.getMessage());
        }
        currentPhase = 1;
    }

    private void peekElement() {
        if (queue.isEmpty()) {
            System.out.println("\n❌ La cola está vacía.");
            currentPhase = 1;
            return;
        }

        try {
            String front = queue.peek();
            System.out.println("\n👀 Primero de la cola: \"" + front + "\"");
        } catch (IllegalStateException e) {
            System.out.println("\n❌ " + e.getMessage());
        }
        currentPhase = 1;
    }

    private void clearQueue() {
        if (queue.isEmpty()) {
            System.out.println("\n❌ La cola ya está vacía.");
            currentPhase = 1;
            return;
        }

        System.out.print("\n⚠ ¿Seguro que quieres limpiar la cola? (s/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("s")) {
            queue.clear();
            System.out.println("✓ Cola limpiada.");
        } else {
            System.out.println("Operación cancelada.");
        }
        currentPhase = 1;
    }

    private void displayQueueStatus() {
        System.out.println("\n📋 Estado de la cola:");
        System.out.println("   └─ Tamaño: " + queue.size() + " | Vacía: " + (queue.isEmpty() ? "Sí" : "No"));
    }
}
