package Java11.src.cz.cuni.mff.java.timeutil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduled {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(()-> System.out.println("Task 1"), 1, 1, TimeUnit.SECONDS);
        service.schedule(()-> System.out.println("Task 2"), 10, TimeUnit.SECONDS);
        service.schedule(()-> {
            System.out.println("Task 3 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
            System.out.println("Task 3 end");
        }, 15, TimeUnit.SECONDS);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {}
        service.shutdown();
    }
}
