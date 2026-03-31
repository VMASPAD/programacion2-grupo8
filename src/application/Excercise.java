package application;

import java.util.Scanner;

public abstract class Excercise {
    protected boolean running = true;
    protected Scanner scanner;

    public Excercise() {}

    public Excercise(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("Running excercise");
        while (running) {
            excerciseLogic();
        }
    }

    protected abstract void excerciseLogic();
}