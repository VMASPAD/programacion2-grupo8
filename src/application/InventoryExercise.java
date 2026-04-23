package application;

import inventoryModule.modelo.Producto;
import java.util.Scanner;
import listModule.SimpleLinkedList;
import listModule.SimpleList;

public class InventoryExercise extends Excercise {
    private SimpleList<Producto> inventory;
    private int currentPhase = 0;
    private boolean showWelcome = true;

    public InventoryExercise(Scanner scanner) {
        super(scanner);
        this.inventory = new SimpleLinkedList<>();
        initializeSampleData();
    }

    // Inicializa con algunos productos de ejemplo para facilitar las pruebas
    private void initializeSampleData() {
        inventory.add(new Producto("P001", "Notebook", 1200.00, 5));
        inventory.add(new Producto("P002", "Mouse", 25.50, 50));
        inventory.add(new Producto("P003", "Teclado", 85.00, 30));
    }

    @Override
    protected void excerciseLogic() {
        if (showWelcome) {
            showWelcome = false;
            displayWelcome();
        }
        displayMainMenu();
    }

    private void displayWelcome() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE INVENTARIO DE COMERCIO   ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private void displayMainMenu() {
        System.out.println("\n┌─── MENÚ PRINCIPAL ───┐");
        System.out.println("│ 1. Buscar Producto   │");
        System.out.println("│ 2. Agregar Producto  │");
        System.out.println("│ 3. Borrar Producto   │");
        System.out.println("│ 4. Editar Producto   │");
        System.out.println("│ 5. Listar Productos  │");
        System.out.println("│ 0. Salir             │");
        System.out.println("└──────────────────────┘");
        System.out.print("▶ Selecciona una opción: ");

        try {
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    buscarProducto();
                    break;
                case 2:
                    agregarProducto();
                    break;
                case 3:
                    borrarProducto();
                    break;
                case 4:
                    editarProducto();
                    break;
                case 5:
                    listarProductos();
                    break;
                case 0:
                    System.out.println("\n✓ Saliendo del sistema de inventario...\n");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta nuevamente.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Entrada inválida. Por favor, ingresa un número.");
            scanner.nextLine();
        }
    }

    private void buscarProducto() {
        System.out.print("\n🔍 Ingresa el código del producto: ");
        String codigo = scanner.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Producto p = inventory.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("\n✓ Producto encontrado:");
                System.out.println("   " + p);
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    private void agregarProducto() {
        System.out.println("\n--- Agregar Nuevo Producto ---");
        
        System.out.print("Código del producto: ");
        String codigo = scanner.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        // Verificar que el código sea único
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("❌ Ya existe un producto con ese código.");
                return;
            }
        }

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        double precio = leerDouble("Precio ($): ");
        if (precio < 0) {
            System.out.println("❌ El precio no puede ser negativo.");
            return;
        }

        int cantidad = leerInt("Cantidad en stock: ");
        if (cantidad < 0) {
            System.out.println("❌ La cantidad no puede ser negativa.");
            return;
        }

        try {
            Producto nuevoProducto = new Producto(codigo, nombre, precio, cantidad);
            inventory.add(nuevoProducto);
            System.out.println("✓ Producto agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al agregar el producto: " + e.getMessage());
        }
    }

    private void borrarProducto() {
        System.out.print("\n🗑️  Ingresa el código del producto a borrar: ");
        String codigo = scanner.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Producto p = inventory.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                inventory.remove(i);
                System.out.println("✓ Producto borrado exitosamente.");
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    private void editarProducto() {
        System.out.print("\n✏️  Ingresa el código del producto a editar: ");
        String codigo = scanner.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Producto p = inventory.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("\nProducto actual: " + p);
                System.out.println("\n¿Qué deseas editar?");
                System.out.println("1. Nombre");
                System.out.println("2. Precio");
                System.out.println("3. Cantidad");
                System.out.println("0. Cancelar");
                System.out.print("Opción: ");

                try {
                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1:
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = scanner.nextLine().trim();
                            if (!nuevoNombre.isEmpty()) {
                                p.setNombre(nuevoNombre);
                                System.out.println("✓ Nombre actualizado.");
                            } else {
                                System.out.println("❌ El nombre no puede estar vacío.");
                            }
                            break;
                        case 2:
                            double nuevoPrecio = leerDouble("Nuevo precio ($): ");
                            p.setPrecio(nuevoPrecio);
                            System.out.println("✓ Precio actualizado.");
                            break;
                        case 3:
                            int nuevaCantidad = leerInt("Nueva cantidad: ");
                            p.setCantidad(nuevaCantidad);
                            System.out.println("✓ Cantidad actualizada.");
                            break;
                        case 0:
                            System.out.println("Edición cancelada.");
                            break;
                        default:
                            System.out.println("❌ Opción inválida.");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Error: Entrada inválida.");
                    scanner.nextLine();
                }
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    private void listarProductos() {
        if (inventory.isEmpty()) {
            System.out.println("\n📦 El inventario está vacío.");
            return;
        }

        System.out.println("\n╔════ LISTADO DE PRODUCTOS ════╗");
        double valorTotalInventario = 0;

        for (int i = 0; i < inventory.size(); i++) {
            Producto p = inventory.get(i);
            System.out.println((i + 1) + ". " + p);
            valorTotalInventario += p.getValorTotal();
        }

        System.out.println("╠══════════════════════════════╣");
        System.out.println(String.format("║ VALOR TOTAL DEL INVENTARIO   ║\n║      $%.2f                ║",
                valorTotalInventario));
        System.out.println("╚══════════════════════════════╝");
    }

    // Métodos auxiliares para leer entrada numérica de forma segura
    private double leerDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("❌ Por favor, ingresa un número válido.");
                scanner.nextLine();
            }
        }
    }

    private int leerInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Por favor, ingresa un número entero válido.");
                scanner.nextLine();
            }
        }
    }
}
