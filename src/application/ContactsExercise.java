package application;

import ContactModule.Contact;
import treeModule.BST;

import java.util.Scanner;

public class ContactsExercise extends Exercise {
    private int currentPhase = 0;
    private BST<Contact> ContactTree;
    private int contactSize = 0;

    public ContactsExercise(Scanner scanner) {
        super(scanner);
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
        }
    }

    private void mainMenu() {

        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║   ADMINISTRADOR DE CONTACTOS                   ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. Registrar nuevo contacto                    ║");
        System.out.println("║ 2. Ver contactos                               ║");
        System.out.println("║ 3. Remover contacto                            ║");
        System.out.println("║ 0. Salir                                       ║");
        System.out.println("╚════════════════════════════════════════════════╝");
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



    private void agregarContacto(){
        contactSize++;
    }

    private void verContacto(){

    }

    private void borrarContacto(){
    contactSize--;
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
