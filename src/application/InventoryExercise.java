package application;

import inventoryModule.model.Product;
import java.util.Scanner;
import listModule.SimpleLinkedList;
import listModule.SimpleList;

/**
 * Sistema de gestión de inventario de comercio.
 * Permite buscar, agregar, eliminar, editar y listar productos en el inventario.
 */
public class InventoryExercise extends Exercise {
    private SimpleList<Product> inventory;
    private int currentPhase = 0;
    private boolean showWelcome = true;

    public InventoryExercise(Scanner scanner) {
        super(scanner);
        this.inventory = new SimpleLinkedList<>();
        initializeSampleData();
    }

    /**
     * Inicializa el inventario con algunos productos de ejemplo para facilitar las pruebas.
     */
    private void initializeSampleData() {
        inventory.add(new Product("P001", "Notebook", 1200.00, 5));
        inventory.add(new Product("P002", "Mouse", 25.50, 50));
        inventory.add(new Product("P003", "Teclado", 85.00, 30));
    }

    @Override
    protected void exerciseLogic() {
        if (showWelcome) {
            showWelcome = false;
            displayWelcome();
        }
        displayMainMenu();
    }

    /**
     * Muestra el encabezado de bienvenida del sistema.
     */
    private void displayWelcome() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE INVENTARIO DE COMERCIO   ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    /**
     * Muestra el menú principal y procesa la opción seleccionada.
     */
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

        int option = readSafeInt();
        scanner.nextLine();

        switch (option) {
                case 1:
                    searchProduct();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    editProduct();
                    break;
                case 5:
                    listProducts();
                    break;
                case 0:
                    System.out.println("\n✓ Saliendo del sistema de inventario...\n");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta nuevamente.");
        }
    }

    /**
     * Busca un producto por su código y lo muestra si existe.
     */
    private void searchProduct() {
        System.out.print("\n🔍 Ingresa el código del producto: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getCode().equalsIgnoreCase(code)) {
                System.out.println("\n✓ Producto encontrado:");
                System.out.println("   " + p);
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    /**
     * Agrega un nuevo producto al inventario.
     * Valida que el código sea único y que los datos sean válidos.
     */
    private void addProduct() {
        System.out.println("\n--- Agregar Nuevo Producto ---");
        
        System.out.print("Código del producto: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        // Verificar que el código sea único
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getCode().equalsIgnoreCase(code)) {
                System.out.println("❌ Ya existe un producto con ese código.");
                return;
            }
        }

        System.out.print("Nombre del producto: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        double price = readDouble("Precio ($): ");
        if (price < 0) {
            System.out.println("❌ El precio no puede ser negativo.");
            return;
        }

        int quantity = readInt("Cantidad en stock: ");
        if (quantity < 0) {
            System.out.println("❌ La cantidad no puede ser negativa.");
            return;
        }

        try {
            Product newProduct = new Product(code, name, price, quantity);
            inventory.add(newProduct);
            System.out.println("✓ Producto agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al agregar el producto: " + e.getMessage());
        }
    }

    /**
     * Elimina un producto del inventario por su código.
     */
    private void deleteProduct() {
        System.out.print("\n🗑️  Ingresa el código del producto a borrar: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getCode().equalsIgnoreCase(code)) {
                inventory.remove(i);
                System.out.println("✓ Producto borrado exitosamente.");
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    /**
     * Edita un producto existente permitiendo cambiar nombre, precio o cantidad.
     */
    private void editProduct() {
        System.out.print("\n✏️  Ingresa el código del producto a editar: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("❌ El código no puede estar vacío.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getCode().equalsIgnoreCase(code)) {
                System.out.println("\nProducto actual: " + p);
                System.out.println("\n¿Qué deseas editar?");
                System.out.println("1. Nombre");
                System.out.println("2. Precio");
                System.out.println("3. Cantidad");
                System.out.println("0. Cancelar");
                System.out.print("Opción: ");

                int option = readSafeInt();
                scanner.nextLine();

                switch (option) {
                        case 1:
                            System.out.print("Nuevo nombre: ");
                            String newName = scanner.nextLine().trim();
                            if (!newName.isEmpty()) {
                                p.setName(newName);
                                System.out.println("✓ Nombre actualizado.");
                            } else {
                                System.out.println("❌ El nombre no puede estar vacío.");
                            }
                            break;
                        case 2:
                            double newPrice = readDouble("Nuevo precio ($): ");
                            p.setPrice(newPrice);
                            System.out.println("✓ Precio actualizado.");
                            break;
                        case 3:
                            int newQuantity = readInt("Nueva cantidad: ");
                            p.setQuantity(newQuantity);
                            System.out.println("✓ Cantidad actualizada.");
                            break;
                        case 0:
                            System.out.println("Edición cancelada.");
                            break;
                        default:
                            System.out.println("❌ Opción inválida.");
                    }
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    /**
     * Muestra un listado de todos los productos en el inventario.
     * Incluye el valor total del inventario.
     */
    private void listProducts() {
        if (inventory.isEmpty()) {
            System.out.println("\n📦 El inventario está vacío.");
            return;
        }

        System.out.println("\n╔════ LISTADO DE PRODUCTOS ════╗");
        double totalInventoryValue = 0;

        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.println((i + 1) + ". " + p);
            totalInventoryValue += p.getTotalValue();
        }

        System.out.println("╠══════════════════════════════╣");
        System.out.println(String.format("║ VALOR TOTAL DEL INVENTARIO   ║\n║      $%.2f                ║",
                totalInventoryValue));
        System.out.println("╚══════════════════════════════╝");
    }

    /**
     * Lee un número decimal del usuario con validación y manejo de errores.
     */
    private double readDouble(String prompt) {
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

    /**
     * Lee un número entero del usuario con validación y manejo de errores.
     */
    private int readInt(String prompt) {
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
