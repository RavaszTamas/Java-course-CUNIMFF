package Java08.src.cz.cuni.mff.java.threads;

public class SimpleLockExample extends Thread {

    public static class Resource {
        private long data;
        public void doSomething() {
		    data++;
	    }

        public long getData() {
            return data;
        }
    }

    private Resource resource;
    private static SimpleLock lock = new SimpleLock();

    public SimpleLockExample(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            if (lock.lock()) {
                resource.doSomething();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] argv) {
        Resource resource = new Resource();
        Thread[] ths = {new SimpleLockExample(resource), new SimpleLockExample(resource), new SimpleLockExample(resource) };
        for (Thread t : ths) {
            t.start();
        }
        for (Thread t : ths) {
            try {
                t.join();
            } catch (InterruptedException ex) {}
        }
        System.out.println(resource.getData());
    }
}
