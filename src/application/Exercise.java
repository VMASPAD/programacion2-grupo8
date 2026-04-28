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

    protected abstract void exerciseLogic();
}