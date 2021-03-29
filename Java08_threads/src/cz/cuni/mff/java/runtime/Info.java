package cz.cuni.mff.java.runtime;

public class Info {

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("Available processors: " + rt.availableProcessors());

        System.out.println("Free mem:  " + rt.freeMemory());
        System.out.println("Total mem: " + rt.totalMemory());
        System.out.println("Max mem:   " + rt.maxMemory());
    }
}
