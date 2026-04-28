package application;

public class TestExercise extends Exercise {

    public TestExercise() {
        super();
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       Test Básico Completado       ║");
        System.out.println("╚════════════════════════════════════╝\n");
        System.out.println("✓ El programa está funcionando correctamente.\n");
        running = false;
    }
}