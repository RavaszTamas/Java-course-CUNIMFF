package cz.cuni.mff.java.threads;

public class SimpleLambdaThread {

    public static synchronized  void printStuff()
    {
        System.out.println("asd");
    }

    public static void main(String[] args) {
        Thread t = new Thread(() -> printStuff());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello from main!");
    }
}
