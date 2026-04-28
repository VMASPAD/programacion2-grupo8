package application;

import java.util.Scanner;

public abstract class Exercise {
    protected boolean running = true;
    protected Scanner scanner;

    public Exercise() {}

    public Exercise(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("Running exercise");
        while (running) {
            exerciseLogic();
        }
    }

    protected int readSafeInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Entrada inválida. Ingresa un número: ");
            }
        }
    }

    protected abstract void exerciseLogic();
}