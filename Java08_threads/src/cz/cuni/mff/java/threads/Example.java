package cz.cuni.mff.java.threads;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

 class CustomRecursiveAction extends RecursiveAction {

        private String workload = "";
        private static final int THRESHOLD = 8;

        private static Logger logger = Logger.getAnonymousLogger();

        public CustomRecursiveAction(String workload) {
            this.workload = workload;
        }
        @Override
        protected void compute() {
            if (workload.length() > THRESHOLD) {
                var subtasks = createSubtasks();
                ForkJoinTask.invokeAll(subtasks);
            } else {
                processing(workload);
            }
        }

        private List<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> subtasks = new ArrayList<>();

        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());

        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));

        return subtasks;
    }

        private void processing(String work) {
            String result = work.toUpperCase();
            logger.info("This result - (" + result + ") - was processed by "
                + Thread.currentThread().getName());
    }
}

public class Example {
    public static void main(String[] args) {
        ForkJoinPool.commonPool().invoke(new CustomRecursiveAction("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
    }
}
