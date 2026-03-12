
import java.util.Scanner;

public abstract class Excercise {
    protected boolean running = true;
    protected Scanner scanner;

    public void run(){
        System.err.println("Running excercise");
        while (running) {
            excerciseLogic();
        }
    }
    protected abstract void excerciseLogic();
}
