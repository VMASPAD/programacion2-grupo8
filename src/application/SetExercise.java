package application;

import setModule.SimpleSet;
import setModule.SimpleArraySet;
import setModule.SimpleLinkedSet;
import java.util.Scanner;

public class SetExercise extends Excercise {
    private int currentPhase = 0;
    private SimpleSet<String> setA;
    private SimpleSet<String> setB;
    private String implementationType;
    private boolean showWelcome = true;
    private String currentSetOperating;

    public SetExercise(Scanner scanner) {
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
                selectSetToOperate();
                break;
            case 3:
                addElement();
                break;
            case 4:
                removeElement();
                break;
            case 5:
                showUnion();
                break;
            case 6:
                showIntersection();
                break;
            case 7:
                showDifference();
                break;
        }
    }

    private void selectImplementation() {
        System.out.println("\n=== Seleccionar Implementación de Set ===");
        System.out.println("1. SimpleArraySet (Basado en Array Dinámico)");
        System.out.println("2. SimpleLinkedSet (Basado en Nodos Enlazados)");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                setA = new SimpleArraySet<>();
                setB = new SimpleArraySet<>();
                implementationType = "SimpleArraySet";
                currentPhase = 1;
                break;
            case 2:
                setA = new SimpleLinkedSet<>();
                setB = new SimpleLinkedSet<>();
                implementationType = "SimpleLinkedSet";
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
            System.out.println("║   Ejercicio de Sets - " + implementationType);
            System.out.println("╚════════════════════════════════════╝");
            showWelcome = false;
        }

        displaySetsStatus();

        System.out.println("\n┌─ Opciones ─────────────────────────┐");
        System.out.println("│ 1. Operar sobre Set A o B");
        System.out.println("│ 2. Mostrar Unión (A ∪ B)");
        System.out.println("│ 3. Mostrar Intersección (A ∩ B)");
        System.out.println("│ 4. Mostrar Diferencia");
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
                currentPhase = 5;
                break;
            case 3:
                currentPhase = 6;
                break;
            case 4:
                currentPhase = 7;
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.");
                break;
        }
    }

    private void selectSetToOperate() {
        System.out.println("\n┌─ Seleccionar Set ──────────────────┐");
        System.out.println("│ 1. Trabajar sobre Set A");
        System.out.println("│ 2. Trabajar sobre Set B");
        System.out.println("│ 0. Volver al menú principal");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                currentSetOperating = "A";
                currentPhase = 2;
                operateOnSet();
                break;
            case 2:
                currentSetOperating = "B";
                currentPhase = 2;
                operateOnSet();
                break;
            case 0:
                currentPhase = 1;
                break;
            default:
                System.out.println("❌ Opción inválida.");
                currentPhase = 2;
                break;
        }
    }

    private void operateOnSet() {
        SimpleSet<String> activeSet = currentSetOperating.equals("A") ? setA : setB;

        System.out.println("\n┌─ Operaciones sobre Set " + currentSetOperating + " ─┐");
        System.out.println("│ Elementos: " + formatSetElements(activeSet));
        System.out.println("├────────────────────────────────────┤");
        System.out.println("│ 1. Agregar elemento");
        System.out.println("│ 2. Remover elemento");
        System.out.println("│ 0. Volver");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                currentPhase = 3;
                break;
            case 2:
                currentPhase = 4;
                break;
            case 0:
                currentPhase = 1;
                break;
            default:
                System.out.println("❌ Opción inválida.");
                currentPhase = 2;
                break;
        }
    }

    private void addElement() {
        SimpleSet<String> activeSet = currentSetOperating.equals("A") ? setA : setB;

        System.out.print("\n▶ Ingresa el elemento a agregar: ");
        String element = scanner.nextLine();

        if (activeSet.add(element)) {
            System.out.println("✓ Elemento \"" + element + "\" agregado exitosamente.");
        } else {
            System.out.println("❌ El elemento \"" + element + "\" ya existe en el Set.");
        }
        currentPhase = 2;
    }

    private void removeElement() {
        SimpleSet<String> activeSet = currentSetOperating.equals("A") ? setA : setB;

        System.out.print("\n▶ Ingresa el elemento a remover: ");
        String element = scanner.nextLine();

        if (activeSet.remove(element)) {
            System.out.println("✓ Elemento \"" + element + "\" removido exitosamente.");
        } else {
            System.out.println("❌ El elemento \"" + element + "\" no existe en el Set.");
        }
        currentPhase = 2;
    }

    private void showUnion() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        Unión de Sets (A ∪ B)       ║");
        System.out.println("╚════════════════════════════════════╝");

        SimpleSet<String> union = setA.unionWith(setB);

        System.out.println("\n📋 Resultado:");
        System.out.println("   Tamaño: " + union.size());
        System.out.println("   Elementos: " + formatSetElements(union));
        System.out.println();

        currentPhase = 1;
    }

    private void showIntersection() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      Intersección de Sets (A ∩ B)   ║");
        System.out.println("╚════════════════════════════════════╝");

        SimpleSet<String> intersection = setA.intersectWith(setB);

        System.out.println("\n📋 Resultado:");
        System.out.println("   Tamaño: " + intersection.size());
        System.out.println("   Elementos: " + formatSetElements(intersection));
        System.out.println();

        currentPhase = 1;
    }

    private void showDifference() {
        System.out.println("\n┌─ Diferencia de Sets ───────────────┐");
        System.out.println("│ 1. Diferencia A - B");
        System.out.println("│ 2. Diferencia B - A");
        System.out.println("│ 0. Volver");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayDifferenceResult(setA, setB, "A - B");
                break;
            case 2:
                displayDifferenceResult(setB, setA, "B - A");
                break;
            case 0:
                currentPhase = 1;
                return;
            default:
                System.out.println("❌ Opción inválida.");
                break;
        }

        currentPhase = 1;
    }

    private void displayDifferenceResult(SimpleSet<String> set1, SimpleSet<String> set2, String label) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     Diferencia de Sets (" + label + ")   ║");
        System.out.println("╚════════════════════════════════════╝");

        SimpleSet<String> difference = set1.differenceWith(set2);

        System.out.println("\n📋 Resultado:");
        System.out.println("   Tamaño: " + difference.size());
        System.out.println("   Elementos: " + formatSetElements(difference));
        System.out.println();
    }

    private void displaySetsStatus() {
        System.out.println("\n📋 Estado de los Sets:");
        System.out.println("   ┌─ Set A ─────────────────────────┐");
        System.out.println("   │ Tamaño: " + setA.size() + " | Vacío: " + (setA.isEmpty() ? "Sí" : "No"));
        System.out.println("   │ Elementos: " + formatSetElements(setA));
        System.out.println("   ├─────────────────────────────────┤");
        System.out.println("   │ Set B");
        System.out.println("   │ Tamaño: " + setB.size() + " | Vacío: " + (setB.isEmpty() ? "Sí" : "No"));
        System.out.println("   │ Elementos: " + formatSetElements(setB));
        System.out.println("   └─────────────────────────────────┘");
    }

    private String formatSetElements(SimpleSet<String> set) {
        if (set.isEmpty()) {
            return "[Vacío]";
        }
        String[] elements = set.toArray();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elements.length; i++) {
            sb.append("\"").append(elements[i]).append("\"");
            if (i < elements.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
