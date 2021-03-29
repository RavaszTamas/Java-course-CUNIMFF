package prepare2;

public class Test01 {
    public static synchronized void main(String[] a) {
        Thread t = new Thread() {
            public void run() {
                pong();
            }
        };
        t.start();
        System.out.println("Ping");

        Thread.currentThread().interrupt();
        if (Thread.interrupted()) {
            System.out.println("Interrupted: " +
                    Thread.interrupted());
        } else {
            System.out.println("Not interrupted: " +
                    Thread.interrupted());
        }


    }
    static synchronized void pong() {
        System.out.println("Pong");
    }
}
