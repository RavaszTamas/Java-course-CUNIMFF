package Java11.src.cz.cuni.mff.java.logging;

public class Log9Test {
    public static void main(String[] args) {
        System.Logger logger = System.getLogger("cz.cuni.mff.java.logging.Log9Test");
        logger.log(System.Logger.Level.ERROR, "Hello");
    }
}
