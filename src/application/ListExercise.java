package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListExercise extends Excercise {
    private int currentPhase = 0;
    private List<String> list;
    private boolean firstTime = true;

    public ListExercise(Scanner scnr) {
        super(scnr);
        list = new ArrayList<>();
    }

    @Override
    protected void excerciseLogic() {
        switch (currentPhase) {
            case 0:
                displayMenu();
                break;
            case 1:
                addElement();
                break;
            case 2:
                removeByIndex();
                break;
            case 3:
                removeByValue();
                break;
            case 4:
                clearList();
                break;
        }
    }

    private void displayMenu() {
        if (firstTime) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║  Ejercicio de ArrayList (Java)     ║");
            System.out.println("╚════════════════════════════════════╝");
            firstTime = false;
        }

        showListStatus();

        System.out.println("\n┌─ Opciones ─────────────────────────┐");
        System.out.println("│ 1. Agregar elemento");
        System.out.println("│ 2. Remover por índice");
        System.out.println("│ 3. Remover por valor");
        System.out.println("│ 4. Limpiar lista");
        System.out.println("│ 0. Salir");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("▶ Selecciona una opción: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    currentPhase = 1;
                    break;
                case 2:
                    currentPhase = 2;
                    break;
                case 3:
                    currentPhase = 3;
                    break;
                case 4:
                    currentPhase = 4;
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta nuevamente.");
                    break;
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("❌ Entrada inválida.");
        }
    }

    private void addElement() {
        System.out.print("\n▶ Ingresa el elemento a agregar: ");
        list.add(scanner.nextLine());
        System.out.println("✓ Elemento agregado.");
        currentPhase = 0;
    }

    private void removeByIndex() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista está vacía.");
            currentPhase = 0;
            return;
        }

        showListStatus();
        System.out.print("\n▶ Ingresa el índice a remover: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 0 && index < list.size()) {
                String removed = list.remove(index);
                System.out.println("✓ Elemento removido: \"" + removed + "\"");
            } else {
                System.out.println("❌ Índice fuera de rango.");
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("❌ Entrada inválida.");
        }
        currentPhase = 0;
    }

    private void removeByValue() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista está vacía.");
            currentPhase = 0;
            return;
        }

        System.out.print("\n▶ Ingresa el elemento a remover: ");
        String element = scanner.nextLine();
        if (list.remove(element)) {
            System.out.println("✓ Elemento removido.");
        } else {
            System.out.println("❌ Elemento no encontrado.");
        }
        currentPhase = 0;
    }

    private void clearList() {
        if (list.isEmpty()) {
            System.out.println("\n❌ La lista ya está vacía.");
            currentPhase = 0;
            return;
        }

        System.out.print("\n⚠ ¿Seguro que quieres limpiar? (s/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("s")) {
            list.clear();
            System.out.println("✓ Lista limpiada.");
        } else {
            System.out.println("Operación cancelada.");
        }
        currentPhase = 0;
    }

    private void showListStatus() {
        System.out.println("\n📋 Estado de la lista (Tamaño: " + list.size() + ")");
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