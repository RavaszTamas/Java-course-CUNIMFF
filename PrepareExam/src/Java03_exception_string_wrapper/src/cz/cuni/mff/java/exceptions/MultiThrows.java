/* $Id$ */
package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class MultiThrows {

    static class Exception1 extends Exception {
    }

    static class Exception2 extends Exception {
    }

    public static void main(String[] argv) throws Exception1, Exception2 {
        try {
            boolean test = true;
            if (test) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

