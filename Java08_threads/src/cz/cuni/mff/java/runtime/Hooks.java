package cz.cuni.mff.java.runtime;

public class Hooks extends Thread {

    @Override
    public void run() {
        System.out.println("Shutdown hook");
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Hooks());
        System.out.println("End");
        if (args.length != 0) {
            rt.halt(0);
        }
    }
}
