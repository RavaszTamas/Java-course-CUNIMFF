package Java02_inheritance.src.cz.cuni.mff.java.example02;

public class ConstructorTest2 extends ConstructorTest1 {

    public ConstructorTest2(int a) {
        System.out.println("Test2");

    }

    public static void main(String[] argv) {
        System.out.println("Creating first object...");
        ConstructorTest1 t1 = new ConstructorTest1();
        System.out.println("Creating second object...");
        ConstructorTest2 t2 = new ConstructorTest2(0);
    }
}
