package Java08.src.cz.cuni.mff.java.threads;

public class SimpleLambdaThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("Hello from thread!"));
        t.start();
        System.out.println("Hello from main!");
    }
}
