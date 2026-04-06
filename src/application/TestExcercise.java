package application;

public class TestExcercise extends Excercise {

    public TestExcercise() {
        super();
    }

    @Override
    protected void excerciseLogic() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       Test Básico Completado       ║");
        System.out.println("╚════════════════════════════════════╝\n");
        System.out.println("✓ El programa está funcionando correctamente.\n");
        running = false;
    }
}