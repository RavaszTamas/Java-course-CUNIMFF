package cz.cuni.mff.java.threads;

public class SimpleLock {

    private boolean locked;

    public static int waiting = 0;
    public static int unlocked = 0;

    public SimpleLock() {
        locked = false;
    }

    synchronized public boolean lock() {
        try {
            while (locked) {
                waiting++;
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
        unlocked++;
        locked = false;
        notifyAll();
    }

}
