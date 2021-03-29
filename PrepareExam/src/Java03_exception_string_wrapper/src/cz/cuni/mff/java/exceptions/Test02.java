/* $Id$ */
package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class Test02 {

    public static void main(String[] argv) {
        try {
            System.out.println("Hello world!");
            System.exit(0);
        } finally {
            System.out.println("Goodbye");
        }
    }

}
