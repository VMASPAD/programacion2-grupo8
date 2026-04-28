package application;

import applicationModule.consumerApp.adt.SimpleLinkedPriorityQueue;
import applicationModule.consumerApp.model.Complaint;
import applicationModule.consumerApp.model.UrgencyLevel;
import java.util.Scanner;

/**
 * Aplicación de consola para atención al consumidor.
 * Permite registrar reclamos con diferentes niveles de urgencia y visualizarlos
 * ordenados por prioridad. Un operario puede marcar reclamos como resueltos.
 * 
 * Decisiones de UX:
 * - Se utiliza una cola de prioridad para atender primero los reclamos críticos
 * - Los reclamos se pueden dejar en la cola si no se resuelven inmediatamente
 * - Validación exhaustiva de inputs para evitar excepciones del TDA
 * - Interfaz clara con indicadores visuales del estado
 */
public class ConsumerApplicationExercise extends Exercise {
    private int currentPhase = 0;  // 0: menú principal, 1+: acción específica
    private SimpleLinkedPriorityQueue<Complaint> complaintQueue;
    private boolean showWelcome = true;
    private static final String BORDER = "╔════════════════════════════════════════════════╗";
    private static final String FOOTER = "╚════════════════════════════════════════════════╝";

    public ConsumerApplicationExercise(Scanner scanner) {
        super(scanner);
        this.complaintQueue = new SimpleLinkedPriorityQueue<>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                mainMenu();
                break;
            case 1:
                recordComplaint();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 2:
                viewComplaint();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 3:
                displayStatistics();
                currentPhase = 0;  // Vuelve al menú principal
                break;
        }
    }

    /**
     * Muestra el menú principal y maneja la selección del usuario.
     */
    private void mainMenu() {
        if (showWelcome) {
            displayWelcome();
            showWelcome = false;
        }

        System.out.println("\n" + BORDER);
        System.out.println("║   SISTEMA DE ATENCIÓN AL CONSUMIDOR        ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. Registrar nuevo reclamo                     ║");
        System.out.println("║ 2. Visualizar próximo reclamo                  ║");
        System.out.println("║ 3. Ver estadísticas                            ║");
        System.out.println("║ 0. Salir                                       ║");
        System.out.println(FOOTER);
        System.out.print("▶ Selecciona una opción: ");

        int option = readSafeInteger();

        switch (option) {
            case 1:
                currentPhase = 1;
                break;
            case 2:
                currentPhase = 2;
                break;
            case 3:
                currentPhase = 3;
                break;
            case 0:
                running = false;
                System.out.println("\n Aplicación terminada!");
                break;
            default:
                System.out.println("Opción inválida. Por favor, ingrese 0, 1, 2 o 3.");
        }
    }

    /**
     * Registra un nuevo reclamo solicitando título, descripción y nivel de urgencia.
     */
    private void recordComplaint() {
        System.out.println("\n" + BORDER);
        System.out.println("║   REGISTRAR NUEVO RECLAMO                     ║");
        System.out.println(FOOTER);

        System.out.print("▶ Ingrese el título del reclamo: ");
        String title = readMandatoryText();

        System.out.print("▶ Ingrese la descripción del reclamo: ");
        String description = readMandatoryText();

        System.out.println("\n¿Cuál es el nivel de urgencia?");
        System.out.println("  1. " + UrgencyLevel.CRITICAL.getLabel());
        System.out.println("  2. " + UrgencyLevel.HIGH.getLabel());
        System.out.println("  3. " + UrgencyLevel.MEDIUM.getLabel());
        System.out.println("  4. " + UrgencyLevel.LOW.getLabel());
        System.out.print("▶ Seleccione (1-4): ");

        UrgencyLevel urgency = readValidUrgency();

        try {
            Complaint newComplaint = new Complaint(title, description, urgency);
            complaintQueue.enqueue(newComplaint, urgency.getWeight());
            System.out.println("\n✅ Reclamo registrado exitosamente en la cola de atención.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError al registrar el reclamo: " + e.getMessage());
        }
    }

    /**
     * Visualiza el siguiente reclamo en la cola y permite resolverlo o dejarlo en espera.
     */
    private void viewComplaint() {
        if (complaintQueue.isEmpty()) {
            System.out.println("\n" + BORDER + "VISUALIZAR RECLAMO" + FOOTER);
            System.out.println("\n✅ Excelente. No hay reclamos pendientes en la cola.");
            return;
        }

        System.out.println("\n" + BORDER + "PRÓXIMO RECLAMO A ATENDER" + FOOTER);

        Complaint next = null;
        try {
            next = complaintQueue.peek();
        } catch (Exception e) {
            System.out.println("Error al obtener reclamo: " + e.getMessage());
            return;
        }

        System.out.println("\n" + next.toString());
        System.out.println("\n---------------------------------");

        System.out.println("\n¿Qué desea hacer con este reporte?");
        System.out.println("  1. Marcar como resuelto (Remover de la cola)");
        System.out.println("  2. Dejar en la cola por ahora");
        System.out.print("▶ Seleccione (1-2): ");

        int option = readSafeInteger();

        if (option == 1) {
            try {
                complaintQueue.dequeue();
                System.out.println("\n✅ Reclamo resuelto y eliminado del sistema.");
            } catch (Exception e) {
                System.out.println("\nError al resolver reclamo: " + e.getMessage());
            }
        } else if (option == 2) {
            System.out.println("\n📋 El reclamo fue conservado en la cola de atención.");
        } else {
            System.out.println("\nOpción inválida.");
        }
    }

    /**
     * Muestra estadísticas de la cola de reclamos.
     */
    private void displayStatistics() {
        System.out.println("\n" + BORDER + "ESTADÍSTICAS DE LA COLA" + FOOTER);

        if (complaintQueue.isEmpty()) {
            System.out.println("✅ No hay reclamos en el sistema actualmente.");
        } else {
            System.out.println("Total de reclamos en cola: " + complaintQueue.size());
            System.out.println("Prioridad más alta (inmediata): " +
                    UrgencyLevel.fromIndex(complaintQueue.getHighestPriority()).getLabel());
        }
    }

    /**
     * Lee un entero del usuario con manejo seguro de excepciones.
     */
    private int readSafeInteger() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Por favor, ingrese un número: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            } catch (Exception e) {
                running = false;
                return -1;
            }
        }
    }

    /**
     * Lee texto obligatorio que no puede estar vacío.
     */
    private String readMandatoryText() {
        while (true) {
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.print("Este campo no puede quedar vacío. Intente de nuevo: ");
        }
    }

    /**
     * Lee y valida el nivel de urgencia ingresado por el usuario.
     */
    private UrgencyLevel readValidUrgency() {
        while (true) {
            int selection = readSafeInteger();
            UrgencyLevel urgency = UrgencyLevel.fromIndex(selection);
            if (urgency != null) {
                return urgency;
            }
            System.out.print("Nivel incorrecto. Ingrese un número del 1 al 4: ");
        }
    }

    /**
     * Muestra mensaje de bienvenida al iniciar la aplicación.
     */
    private void displayWelcome() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║   BIENVENIDO AL SISTEMA DE ATENCIÓN AL        ║");
        System.out.println("║              CONSUMIDOR                        ║");
        System.out.println("║                                                ║");
        System.out.println("║   Este sistema permite gestionar reclamos     ║");
        System.out.println("║   de clientes priorizados por urgencia.       ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }
}
