package application;

import java.util.Scanner;
import stackModule.SimpleArrayStack;
import stackModule.SimpleLinkedStack;
import stackModule.SimpleStack;

public class StackExercise extends Excercise {
    private int currentPhase = 0;
    private SimpleStack<String> stack;
    private String implementationType;
    private boolean showWelcome = true;

    public StackExercise(Scanner scanner) {
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
                pushElement();
                break;
            case 3:
                popElement();
                break;
            case 4:
                peekElement();
                break;
            case 5:
                clearStack();
                break;
        }
    }

    // Recibe entrada del usuario para elegir entre SimpleArrayStack o SimpleLinkedStack
    private void selectImplementation() {
        System.out.println("\n=== Seleccionar Implementación de Pila ===");
        System.out.println("1. SimpleArrayStack (Basado en Array Dinámico)");
        System.out.println("2. SimpleLinkedStack (Basado en Nodos Enlazados)");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                stack = new SimpleArrayStack<>();
                implementationType = "SimpleArrayStack";
                currentPhase = 1;
                break;
            case 2:
                stack = new SimpleLinkedStack<>();
                implementationType = "SimpleLinkedStack";
                currentPhase = 1;
                break;
            default:
                System.out.println("Opción inválida. Intenta nuevamente.");
                break;
        }
    }
    // Recibe entrada de usuario, muestra menú de operaciones (push, pop, peek, clear)   
     private void mainMenu() {
        if (showWelcome) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   Ejercicio de Pilas - " + implementationType);
            System.out.println("╚════════════════════════════════════╝");
            showWelcome = false;
        }

        displayStackStatus();

        System.out.println("\n┌─ Opciones ─────────────────────────┐");
        System.out.println("│ 1. Push (Apilar elemento)");
        System.out.println("│ 2. Pop (Desapilar elemento)");
        System.out.println("│ 3. Peek (Ver tope)");
        System.out.println("│ 4. Clear (Limpiar pila)");
        System.out.println("│ 0. Salir");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Selecciona una opción: ");


        Boolean validChoice = false;
        while(!validChoice) {
            int choice;
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
    

    private void pushElement() {
        System.out.print("\n▶ Ingresa el elemento a apilar: ");
        String element = scanner.nextLine();
        stack.push(element);
        System.out.println("✓ Elemento apilado correctamente.");
        currentPhase = 1;
    }

    private void popElement() {
        if (stack.isEmpty()) {
            System.out.println("\n❌ La pila está vacía.");
            currentPhase = 1;
            return;
        }

        try {
            String popped = stack.pop();
            System.out.println("\n✓ Elemento desapilado: \"" + popped + "\"");
        } catch (IllegalStateException e) {
            System.out.println("\n❌ " + e.getMessage());
        }
        currentPhase = 1;
    }

    private void peekElement() {
        if (stack.isEmpty()) {
            System.out.println("\n❌ La pila está vacía.");
            currentPhase = 1;
            return;
        }

        try {
            String top = stack.peek();
            System.out.println("\n📌 Tope de la pila: \"" + top + "\"");
        } catch (IllegalStateException e) {
            System.out.println("\n❌ " + e.getMessage());
        }
        currentPhase = 1;
    }

    private void clearStack() {
        if (stack.isEmpty()) {
            System.out.println("\n❌ La pila ya está vacía.");
            currentPhase = 1;
            return;
        }

        System.out.print("\n⚠ ¿Seguro que quieres limpiar la pila? (s/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("s")) {
            stack.clear();
            System.out.println("✓ Pila limpiada.");
        } else {
            System.out.println("Operación cancelada.");
        }
        currentPhase = 1;
    }

    private void displayStackStatus() {
        System.out.println("\n📋 Estado de la pila:");
        System.out.println("   └─ Tamaño: " + stack.size() + " | Vacía: " + (stack.isEmpty() ? "Sí" : "No"));
    }
}
