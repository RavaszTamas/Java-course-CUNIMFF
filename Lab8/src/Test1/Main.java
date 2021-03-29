package Test1;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        if (Thread.interrupted()) {
            System.out.println("Interrupted: " +
                    Thread.interrupted());
        } else {
            System.out.println("Not interrupted: " +
                    Thread.interrupted());
        }
    }
}
