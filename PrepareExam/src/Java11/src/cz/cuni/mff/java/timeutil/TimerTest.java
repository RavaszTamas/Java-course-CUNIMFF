package Java11.src.cz.cuni.mff.java.timeutil;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class TimerTest {

    static class Task1 extends TimerTask {
        static int cnt = 1;

        @Override
        public void run() {
            System.out.println("Task1: " + cnt + " \"" + new Date() + "\"");
            cnt++;
        }
    }

    static class Task2 extends TimerTask {
        @Override
        public void run() {
            System.out.println("Task2 \"" + new Date() + "\"");
        }
    }

    static class Task3 extends TimerTask {
        @Override
        public void run() {
            System.out.println("Task3 start \"" + new Date() + "\"");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("Task3 end \"" + new Date() + "\"");
        }
    }

    public static void main(String[] args) throws Exception {
        Timer tm = new Timer();
        tm.schedule(new Task1(), 1000, 1000);
        tm.schedule(new Task2(), 10000);
        tm.schedule(new Task3(), 15000);
        Thread.sleep(30000);
        tm.cancel();
    }


}


