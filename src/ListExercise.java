import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import application.Exercise;

public class ListExercise extends Exercise {
    private int currentPhase = 0;
    private List<String> list;
    private boolean bienvenida = true;

    public ListExercise(Scanner scnr) {
        super(scnr);
        list = new ArrayList<>();
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("exercise Logic");
        switch(currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                addLogic();
                break;
            case 2:
                removeByIndexLogic();
                break;
            case 3:
                removeByReferenceLogic();
                break;
            case 4:
                clearLogic();
                break;
        }
    }

    private void menuLogic() {
        if (bienvenida) {
            System.out.println("Welcome to the List Exercise");
            bienvenida = false;
        }
        else {
            String fullList = "";

            for (int i = 0; i < list.size(); i++){
                fullList += list.get(i);
                if (i < list.size() - 1)
                    fullList += ", ";
            }
            System.out.println("Current List: " + fullList);
        }
        System.out.println("Choose an option:"
                        + "\nadd: Add element."
                        + "\nremoveindex: Remove element by index."
                        + "\nremoveref: Remove element by reference."
                        + "\nclear: Clear list."
                        + "\nmm: Exit.");

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "add":
                currentPhase = 1;
                break;
            case "removeindex":
                currentPhase = 2;
                break;
            case "removeref":
                currentPhase = 3;
                break;
            case "clear":
                currentPhase = 4;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Invalid choice, try again");
                break;
        }
    }

    private void addLogic() {
        System.out.println("\nEnter a String to add:");
        list.add(scanner.nextLine());
        currentPhase = 0;
    }

    private void removeByIndexLogic() {
        System.out.println("\nEnter the index of the element to remove:");
        list.remove(scanner.nextInt());
        currentPhase = 0;
    }

    private void removeByReferenceLogic() {
        System.out.println("\nEnter the element to remove:");
        list.remove(scanner.nextLine());
        currentPhase = 0;
    }

    private void clearLogic() {
        list.clear();
        System.out.println("\nThe List has been cleared.");
        currentPhase = 0;
    }
}
