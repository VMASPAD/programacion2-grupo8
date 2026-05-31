package application;

import ContactModule.Contact;
import java.util.Scanner;
import listModule.SimpleLinkedList;
import treeModule.AVLTree;

public class ContactsExercise extends Exercise {
    private int currentPhase = 0;
    private AVLTree<Contact> ContactTree;
    private int contactSize = 0;

    public ContactsExercise(Scanner scanner) {
        super(scanner);
        this.ContactTree = new AVLTree<>();
        initializeTestData();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                mainMenu();
                break;
            case 1:
                agregarContacto();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 2:
                verContacto();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 3:
                borrarContacto();
                currentPhase = 0;  // Vuelve al menú principal
                break;
            case 4:
                cargarDatos();
                currentPhase = 0;
                break;
        }
    }

    private void mainMenu() {

        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║   ADMINISTRADOR DE CONTACTOS                   ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. Registrar nuevo contacto                    ║");
        System.out.println("║ 2. Ver todos los contactos                     ║");
        System.out.println("║ 3. Remover contacto                            ║");
        System.out.println("║ 4. Cargar datos de prueba                      ║");
        System.out.println("║ 0. Salir                                       ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("▶ Selecciona una opción: ");

        int option = readSafeInteger();
        scanner.nextLine(); // Limpiar buffer

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
            case 4:
                currentPhase = 4;
                break;
            case 0:
                running = false;
                System.out.println("\n¡Aplicación terminada!");
                break;
            default:
                System.out.println("❌ Opción inválida. Por favor, ingrese 0, 1, 2, 3 o 4.");
        }
    }

    // Implementación real: agregar contacto al BST
    private void agregarContacto(){
        System.out.println("\n--- Registrar Nuevo Contacto ---");
        
        String nombre;
        while (true) {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("❌ El nombre no puede estar vacío. Intente nuevamente.");
                continue;
            }
            
            // Verificar que no exista un contacto con el mismo nombre
            boolean existe = false;
            SimpleLinkedList<Contact> allContacts = ContactTree.preOrder();
            for (int i = 0; i < allContacts.size(); i++) {
                if (allContacts.get(i).getNombre().equalsIgnoreCase(nombre)) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                System.out.println("❌ Ya existe un contacto con el nombre '" + nombre + "'. Intente con otro nombre.");
                continue;
            }
            break;
        }

        String numero;
        while (true) {
            System.out.print("Número de teléfono: ");
            numero = scanner.nextLine().trim();
            
            if (numero.isEmpty()) {
                System.out.println("❌ El número no puede estar vacío. Intente nuevamente.");
                continue;
            }
            
            // Validar que contenga solo dígitos
            if (!numero.matches("\\d+")) {
                System.out.println("❌ El número de teléfono debe contener solo dígitos. Intente nuevamente.");
                continue;
            }
            break;
        }

        String mail;
        while (true) {
            System.out.print("Email: ");
            mail = scanner.nextLine().trim();
            
            if (mail.isEmpty()) {
                System.out.println("❌ El email no puede estar vacío. Intente nuevamente.");
                continue;
            }
            break;
        }

        Contact newContact = new Contact(nombre, numero, mail);
        ContactTree.insert(newContact);
        contactSize++;
        System.out.println("Contacto '" + nombre + "' agregado exitosamente.");
    }

    // Implementación real: mostrar todos los contactos ordenados
    private void verContacto(){
        System.out.println("\n--- Lista de Contactos ---");
        
        if (contactSize == 0) {
            System.out.println(" No hay contactos registrados.");
            return;
        }

        SimpleLinkedList<Contact> allContacts = ContactTree.preOrder();
        int index = 1;
        for (int i = 0; i < allContacts.size(); i++) {
            Contact contact = allContacts.get(i);
            System.out.println("\n[" + index + "] " + contact);
            index++;
        }
        System.out.println("\n(Total: " + contactSize + " contactos)");
    }

    // Implementación real: eliminar contacto del BST
    private void borrarContacto(){
        System.out.println("\n--- Remover Contacto ---");
        
        if (contactSize == 0) {
            System.out.println(" No hay contactos para remover.");
            return;
        }

        System.out.print("Ingrese el número de teléfono del contacto a eliminar: ");
        String numeroAEliminar = scanner.nextLine().trim();

        if (numeroAEliminar.isEmpty()) {
            System.out.println("❌ El número no puede estar vacío.");
            return;
        }

        // Validar que contenga solo dígitos
        if (!numeroAEliminar.matches("\\d+")) {
            System.out.println("❌ El número de teléfono debe contener solo dígitos.");
            return;
        }

        // Buscar el contacto con ese número
        SimpleLinkedList<Contact> allContacts = ContactTree.preOrder();
        Contact contactToRemove = null;
        for (int i = 0; i < allContacts.size(); i++) {
            Contact contact = allContacts.get(i);
            if (contact.getNumero().equals(numeroAEliminar)) {
                contactToRemove = contact;
                break;
            }
        }

        if (contactToRemove == null) {
            System.out.println("❌ No se encontró contacto con el número '" + numeroAEliminar + "'.");
            return;
        }

        ContactTree.remove(contactToRemove);
        contactSize--;
        System.out.println(" Contacto '" + contactToRemove.getNombre() + "' eliminado exitosamente.");
    }

    // Cargar datos de prueba (propios de los requisitos del TP)
    private void cargarDatos(){
        System.out.println("\n--- Cargando datos de prueba ---");
        
        // Limpiar contactos existentes
        ContactTree = new AVLTree<>();
        contactSize = 0;
        
        // Datos de prueba pre-programados
        Contact[] testContacts = {
            new Contact("Juan Pérez", "541128173229", "juan@email.com"),
            new Contact("María González", "549876543210", "maria@email.com"),
            new Contact("Carlos López", "545555555555", "carlos@email.com"),
            new Contact("Ana Martínez", "544444444444", "ana@email.com"),
            new Contact("Roberto Sánchez", "543333333333", "roberto@email.com")
        };
        
        for (Contact contact : testContacts) {
            ContactTree.insert(contact);
            contactSize++;
        }
        
        System.out.println(" Se cargaron " + contactSize + " contactos de prueba.");
    }

    // Inicializar con un árbol vacío (los datos se cargan opcionalmente desde el menú)
    private void initializeTestData(){
        // El BST se inicializa vacío; el usuario puede cargarlo desde el menú
    }


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



}
