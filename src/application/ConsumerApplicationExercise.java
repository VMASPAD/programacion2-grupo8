package application;

import applicationModule.consumerApp.modelo.NivelUrgencia;
import applicationModule.consumerApp.modelo.Reclamo;
import applicationModule.consumerApp.tda.SimpleLinkedPriorityQueue;
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
public class ConsumerApplicationExercise extends Excercise {
    private int currentPhase = 0;  // 0: menú principal, 1+: acción específica
    private SimpleLinkedPriorityQueue<Reclamo> colaReclamos;
    private boolean showWelcome = true;
    private static final String BORDER = "╔════════════════════════════════════════════════╗";
    private static final String FOOTER = "╚════════════════════════════════════════════════╝";

    public ConsumerApplicationExercise(Scanner scanner) {
        super(scanner);
        this.colaReclamos = new SimpleLinkedPriorityQueue<>();
    }

    @Override
    protected void excerciseLogic() {
        switch (currentPhase) {
            case 0:
                mainMenu();
                break;
            case 1:
                redactarReclamo();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 2:
                visualizarReclamo();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 3:
                mostrarEstadisticas();
                currentPhase = 0;  // Vuelve al menú principal
                break;
        }
    }

    private void mainMenu() {
        if (showWelcome) {
            mostrarBienvenida();
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

        int opcion = leerEnteroSeguro();

        switch (opcion) {
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

    private void redactarReclamo() {
        System.out.println("\n" + BORDER);
        System.out.println("║   REGISTRAR NUEVO RECLAMO                     ║");
        System.out.println(FOOTER);

        System.out.print("▶ Ingrese el título del reclamo: ");
        String titulo = leerTextoObligatorio();

        System.out.print("▶ Ingrese la descripción del reclamo: ");
        String descripcion = leerTextoObligatorio();

        System.out.println("\n¿Cuál es el nivel de urgencia?");
        System.out.println("  1. " + NivelUrgencia.CRITICO.getEtiqueta());
        System.out.println("  2. " + NivelUrgencia.ALTO.getEtiqueta());
        System.out.println("  3. " + NivelUrgencia.MEDIO.getEtiqueta());
        System.out.println("  4. " + NivelUrgencia.BAJO.getEtiqueta());
        System.out.print("▶ Seleccione (1-4): ");

        NivelUrgencia urgencia = leerUrgenciaValida();

        try {
            Reclamo nuevoReclamo = new Reclamo(titulo, descripcion, urgencia);
            colaReclamos.enqueue(nuevoReclamo, urgencia.getPeso());
            System.out.println("\n✅ Reclamo registrado exitosamente en la cola de atención.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError al registrar el reclamo: " + e.getMessage());
        }
    }

    private void visualizarReclamo() {
        if (colaReclamos.isEmpty()) {
            System.out.println("\n" + BORDER);
            System.out.println("║   VISUALIZAR RECLAMO                           ║");
            System.out.println(FOOTER);
            System.out.println("\n✅ ¡Excelente! No hay reclamos pendientes en la cola.");
            return;
        }

        System.out.println("\n" + BORDER);
        System.out.println("║   PRÓXIMO RECLAMO A ATENDER                   ║");
        System.out.println(FOOTER);

        Reclamo proximo = null;
        try {
            proximo = colaReclamos.peek();
        } catch (Exception e) {
            System.out.println("Error al obtener reclamo: " + e.getMessage());
            return;
        }

        System.out.println("\n" + proximo.toString());
        System.out.println("\n---------------------------------");

        System.out.println("\n¿Qué desea hacer con este reporte?");
        System.out.println("  1. Marcar como resuelto (Remover de la cola)");
        System.out.println("  2. Dejar en la cola por ahora");
        System.out.print("▶ Seleccione (1-2): ");

        int opcion = leerEnteroSeguro();

        if (opcion == 1) {
            try {
                colaReclamos.dequeue();
                System.out.println("\n✅ Reclamo resuelto y eliminado del sistema.");
            } catch (Exception e) {
                System.out.println("\nError al resolver reclamo: " + e.getMessage());
            }
        } else if (opcion == 2) {
            System.out.println("\n📋 El reclamo fue conservado en la cola de atención.");
        } else {
            System.out.println("\nOpción inválida.");
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n" + BORDER);
        System.out.println("║   ESTADÍSTICAS DE LA COLA                      ║");
        System.out.println(FOOTER);

        if (colaReclamos.isEmpty()) {
            System.out.println("✅ No hay reclamos en el sistema actualmente.");
        } else {
            System.out.println("📊 Total de reclamos en cola: " + colaReclamos.size());
            System.out.println("⚠️  Prioridad más alta (inmediata): " +
                    NivelUrgencia.fromIndex(colaReclamos.getHighestPriority()).getEtiqueta());
        }
    }

    /**
     * Lee un entero del usuario con manejo seguro de excepciones.
     */
    private int leerEnteroSeguro() {
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
            }
        }
    }

    /**
     * Lee texto obligatorio que no puede estar vacío.
     */
    private String leerTextoObligatorio() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.print("Este campo no puede quedar vacío. Intente de nuevo: ");
        }
    }

    /**
     * Lee y valida el nivel de urgencia ingresado por el usuario.
     */
    private NivelUrgencia leerUrgenciaValida() {
        while (true) {
            int seleccion = leerEnteroSeguro();
            NivelUrgencia urgencia = NivelUrgencia.fromIndex(seleccion);
            if (urgencia != null) {
                return urgencia;
            }
            System.out.print("Nivel incorrecto. Ingrese un número del 1 al 4: ");
        }
    }

    private void mostrarBienvenida() {
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
