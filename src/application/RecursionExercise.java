package application;

import java.util.Scanner;

public class RecursionExercise extends Exercise {

    //private int currentPhase = 0;
    private boolean showWelcome = true;

    public RecursionExercise(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void exerciseLogic() {
        if (showWelcome) {
            showWelcome = false;
            displayWelcome();
        }
        displayMainMenu();
    }

    private void displayWelcome() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  EJERCICIO DE RECURSION               ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private void displayMainMenu() {
        System.out.println("\n┌───── MENÚ PRINCIPAL ───────┐");
        System.out.println("│ 1. Ejercicio Factorial     │");
        System.out.println("│ 2. Ejercico Fibonacci      │");
        System.out.println("│ 3. Ejercicio exclusiveSum  │");
        System.out.println("│ 4. Ejercicio Pyramid       │");
        System.out.println("│ 5. Ejercicio isPalindrome  │");
        System.out.println("│ 0. Salir                   │");
        System.out.println("└────────────────────────────┘");
        System.out.print("▶ Selecciona una opción: ");

        int option = readSafeInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                ejercicioFactorial();
                break;
            case 2:
                ejercicioFibonacci();
                break;
            case 3:
                ejercicioExclusiveSum();
                break;
            case 4:
                ejercicioPyramid();
                break;
            case 5:
                ejercicioIsPalindrome();
                break;
            case 0:
                System.out.println("\n✓ Saliendo del ejercicio de Recursion...\n");
                running = false;
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.");
        }
    }

    private void ejercicioFactorial() {
        System.out.println("\n✓ Ejercicio Factorial\n");
        System.out.println("\n✓ Ingrese un numero para calcular el factorial:\n");
        int numFactorial = readSafeInt();
        scanner.nextLine();
        System.out.println("\n✓ El factorial de " + numFactorial + " es: " + factorial(numFactorial) + "\n");

    }

    private long factorial(int numFactorial) {
        if (numFactorial <= 1) return 1;

        return numFactorial * factorial(numFactorial - 1);
    }


    private void ejercicioFibonacci() {
        System.out.println("\n✓ Ejercicio Fibonacci\n");
        System.out.println("\n✓ Ingrese un numero para calcular el fibonacci:\n");
        int numFibonacci = readSafeInt();
        scanner.nextLine();
        System.out.println("\n✓ El numero fibonacci de " + numFibonacci + " es: " + fibonacci(numFibonacci) + "\n");
    }

    private long fibonacci(int numFibonacci){
        if (numFibonacci <=1) return numFibonacci;
        return fibonacci(numFibonacci - 1) + fibonacci( numFibonacci - 2);
    }

    private void ejercicioExclusiveSum() {
        System.out.println("\n✓ Ejercicio ExclusiveSum\n");
        System.out.println("\n✓ Ingrese un numero para calcular la suma de los numeros enteros positivos menores:\n");
        int numExclusiveSum = readSafeInt();
        scanner.nextLine();
        System.out.println("\n✓ La suma exclusiva de " + numExclusiveSum + " es: " + exclusiveSum(numExclusiveSum) + "\n");
    }

    private long exclusiveSum(int numExclusiveSum){
        if (numExclusiveSum <= 1) return numExclusiveSum;
        return exclusiveSum(numExclusiveSum - 1) + numExclusiveSum;
    }

    private void ejercicioPyramid() {
        System.out.println("\n✓ Ejercicio IsPyramid\n");
        System.out.println("\n✓ Ingrese un numero para dibujar la piramide:\n");
        int numPyramid = readSafeInt();
        scanner.nextLine();
        System.out.println("\n✓ Piramide: \n");
        Pyramid(numPyramid);
    }

    public static void Pyramid(int numPyramid) {
        // Iniciamos la recursión en la posición 0
        Pyramid(numPyramid, 0);
    }

    private static void Pyramid(int pisos, int pos) {
        // cada fila mide (2 * pisos - 1) de ancho + 1 char para el salto de línea
        int anchoFila = 2 * pisos;
        int totalCaracteres = pisos * anchoFila;

        // caso base
        if (pos >= totalCaracteres) {
            return;
        }

        // calculamos la fila y columna actual basandonos en la pos
        int fila = pos / anchoFila;
        int col = pos % anchoFila;

        // si estamos en el final de la fila, imprimir salto de linea
        if (col == anchoFila - 1) {
            System.out.println();
        } else {
            // calculamos los límites de las X para la fila actual
            int limiteIzquierdo = pisos - fila - 1;
            int limiteDerecho = pisos + fila - 1;

            // decidimos que imprimir segun la posicion de la columna
            if (col >= limiteIzquierdo && col <= limiteDerecho) {
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
        }
        //siguiente pos
        Pyramid(pisos, pos + 1);
    }


    private void ejercicioIsPalindrome() {
        System.out.println("\n✓ Ejercicio IsPalindrome\n");
        System.out.println("\n✓ Ingrese una palabra para comprobar si es palindomo\n");
        String palabraPalindromo = scanner.nextLine();
        if (esPalindromo(palabraPalindromo)){
            System.out.println("\n✓ La palabra "+ palabraPalindromo + " ES palindromo\n");
        }else{
            System.out.println("\n✓ La palabra "+ palabraPalindromo + " NO es palindromo\n");
        }
    }

    private boolean esPalindromo (String palabraPalindromo){
        String palabraLimpia = palabraPalindromo.toLowerCase();
        //caso base
        if (palabraLimpia.length() <= 1) {
            return true;
        }//quito el primer y ultimo char para la siguiente recursion
        if (palabraLimpia.charAt(0) == palabraLimpia.charAt(palabraLimpia.length() - 1)){
            return esPalindromo(palabraLimpia.substring(1, palabraLimpia.length() - 1));
        }
        return false;
    }
}