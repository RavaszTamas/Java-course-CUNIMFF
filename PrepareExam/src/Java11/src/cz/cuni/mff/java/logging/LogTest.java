package Java11.src.cz.cuni.mff.java.logging;

import java.util.logging.*;

public class LogTest {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("cz.cuni.mff.java.logging.TestLog");
        //logger.setLevel(Level.WARNING);
        //logger.setLevel(Level.SEVERE);
        //logger.setLevel(Level.FINEST);
        logger.info("doing stuff");
        try {
            System.out.println(args[0]);
        } catch (Throwable ex) {
            logger.log(Level.WARNING, "exception occurred", ex);
        }
        logger.info("done");
    }
}
