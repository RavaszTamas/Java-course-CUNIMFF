package Java08.src.cz.cuni.mff.java.threads;

public class SimpleLock {

    private boolean locked;

    public SimpleLock() {
        locked = false;
    }

    synchronized public boolean lock() {
        try {
            while (locked) {
                wait();
            }
            locked = true;
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    synchronized public boolean tryLock() {
        if (locked) {
            return false;
        } else {
            locked = true;
            return true;
        }
    }

    synchronized public void unlock() {
        locked = false;
        notify();
    }

}
