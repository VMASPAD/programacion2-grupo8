package application;

public class TestExcercise extends Excercise {

    public TestExcercise() {
        super();
    }

    @Override
    protected void excerciseLogic() {
        System.out.println("Running test excercise");
        running = false;
    }
}