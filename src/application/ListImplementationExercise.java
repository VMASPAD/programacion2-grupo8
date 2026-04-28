package application;

import java.util.Scanner;
import listModule.SimpleArrayList;
import listModule.SimpleLinkedList;
import listModule.SimpleList;

public class ListImplementationExercise extends Exercise {
    private int currentPhase = 0;
    private SimpleList<String> list;
    private String implementationType;
    private boolean showWelcome = true;

    public ListImplementationExercise(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                selectImplementation();
                break;
            case 1:
                mainMenu();
                break;
            case 2:
                addElement();
                break;
            case 3:
                removeByIndex();
                break;
            case 4:
                removeByValue();
                break;
            case 5:
                searchElement();
                break;
            case 6:
                insertAtPosition();
                break;
            case 7:
                replaceElement();
                break;
            case 8:
                clearList();
                break;
        }
    }

    // Recibe entrada del usuario para elegir entre SimpleArrayList o SimpleLinkedList
    private void selectImplementation() {
        System.out.println("\n=== Seleccionar Implementación ===");
        System.out.println("1. SimpleArrayList (Basado en Array Dinámico)");
        System.out.println("2. SimpleLinkedList (Basado en Nodos Enlazados)");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                list = new SimpleArrayList<>();
                implementationType = "SimpleArrayList";
                currentPhase = 1;
                break;
            case 2:
                list = new SimpleLinkedList<>();
                implementationType = "SimpleLinkedList";
                currentPhase = 1;
                break;
            default:
                System.out.println("Opción inválida. Intenta nuevamente.");
                break;
        }
    }

    private void mainMenu() {
        if (showWelcome) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   Ejercicio de Listas - " + implementationType);
            System.out.println("╚════════════════════════════════════╝");
            showWelcome = false;
        }

        displayList();

        System.out.println("\n┌─ Opciones ─────────────────────────┐");
        System.out.println("│ 1. Agregar elemento");
        System.out.println("│ 2. Remover por índice");
        System.out.println("│ 3. Remover por valor");
        System.out.println("│ 4. Buscar elemento");
        System.out.println("│ 5. Insertar en posición");
        System.out.println("│ 6. Reemplazar elemento");
        System.out.println("│ 7. Limpiar lista");
        System.out.println("│ 0. Salir");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Selecciona una opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

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
            case 5:
                currentPhase = 6;
                break;
            case 6:
                currentPhase = 7;
                break;
            case 7:
                currentPhase = 8;
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.");
                break;
        }
    }

    private void addElement() {
        System.out.print("\n▶ Ingresa el elemento a agregar: ");
        String element = scanner.nextLine();
        list.add(element);
        System.out.println("✓ Elemento agregado correctamente.");
        currentPhase = 1;
    }

    private void removeByIndex() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista está vacía.");
            currentPhase = 1;
            return;
        }

        displayList();
        System.out.print("\n▶ Ingresa el índice del elemento a remover: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            String removed = list.remove(index);
            System.out.println("✓ Elemento removido: \"" + removed + "\"");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Índice inválido.");
        }
        currentPhase = 1;
    }

    private void removeByValue() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista está vacía.");
            currentPhase = 1;
            return;
        }

        System.out.print("\n▶ Ingresa el elemento a remover: ");
        String element = scanner.nextLine();
        if (list.remove(element)) {
            System.out.println("✓ Elemento removido correctamente.");
        } else {
            System.out.println("❌ Elemento no encontrado en la lista.");
        }
        currentPhase = 1;
    }

    private void searchElement() {
        System.out.print("\n▶ Ingresa el elemento a buscar: ");
        String element = scanner.nextLine();
        if (list.contains(element)) {
            System.out.println("✓ El elemento existe en la lista.");
        } else {
            System.out.println("❌ El elemento no está en la lista.");
        }
        currentPhase = 1;
    }

    private void insertAtPosition() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista está vacía. Usa 'Agregar' primero.");
            currentPhase = 1;
            return;
        }

        displayList();
        System.out.print("\n▶ Ingresa el índice donde insertar: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            System.out.print("▶ Ingresa el elemento a insertar: ");
            String element = scanner.nextLine();
            list.add(index, element);
            System.out.println("✓ Elemento insertado correctamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Índice inválido.");
        }
        currentPhase = 1;
    }

    private void replaceElement() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista está vacía.");
            currentPhase = 1;
            return;
        }

        displayList();
        System.out.print("\n▶ Ingresa el índice del elemento a reemplazar: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            System.out.print("▶ Ingresa el nuevo elemento: ");
            String newElement = scanner.nextLine();
            String oldElement = list.set(index, newElement);
            System.out.println("✓ \"" + oldElement + "\" fue reemplazado por \"" + newElement + "\"");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Índice inválido.");
        }
        currentPhase = 1;
    }

    private void clearList() {
        System.out.print("\n⚠ ¿Seguro que quieres limpiar la lista? (s/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("s")) {
            list.clear();
            System.out.println("✓ Lista limpiada.");
        } else {
            System.out.println("Operación cancelada.");
        }
        currentPhase = 1;
    }

    private void displayList() {
        System.out.println("\n📋 Estado actual (Tamaño: " + list.size() + ")");
        if (list.isEmpty()) {
            System.out.println("   [Lista vacía]");
        } else {
            System.out.print("   [");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(i + ": \"" + list.get(i) + "\"");
                if (i < list.size() - 1) System.out.print(" | ");
            }
            System.out.println("]");
        }
    }
}
