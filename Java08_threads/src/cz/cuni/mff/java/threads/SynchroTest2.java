package cz.cuni.mff.java.threads;

public class SynchroTest2 extends Thread {

    public SynchroTest2() {
        start();
    }

    public static synchronized void print1() {
        System.out.println("print1()");
        print2();
        System.out.println("print1()");
    }

    public static synchronized void print2() {
        System.out.println("print2()");
    }

    @Override
    public void run() {
        print1();
    }

    public static void main(String[] args) {
        new SynchroTest2();
    }
}
