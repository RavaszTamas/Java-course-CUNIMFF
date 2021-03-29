package cz.cuni.mff.java.threads;

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

    private static SimpleLock lock = new SimpleLock();
    private Resource resource;

    public SimpleLockExample(Resource resource) {
        this.resource = resource;
        //lock = new SimpleLock();
    }

    @Override
    public void run() {
        try {
            if (lock.lock()) {
                System.out.println(this.getName());
                resource.doSomething();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] argv) {
        Resource resource = new Resource();
        Thread[] ths = new Thread[100];
        for(int i = 0; i< ths.length; i++)
            ths[i] = new SimpleLockExample(resource);
        //[] ths = {new SimpleLockExample(resource), new SimpleLockExample(resource), new SimpleLockExample(resource) };
        for (Thread t : ths) {
            t.start();
        }
        for (Thread t : ths) {
            try {
                t.join();
            } catch (InterruptedException ex) {}
        }
        System.out.println(resource.getData());
        System.out.println(SimpleLock.waiting + " " + SimpleLock.unlocked);
    }
}
