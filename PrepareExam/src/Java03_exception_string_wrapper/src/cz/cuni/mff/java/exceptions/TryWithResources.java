/* $Id$ */
package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class TryWithResources {

    static class MyResource1 implements AutoCloseable {
        public void close() throws Exception {
            System.out.println("MyResource1 was closed!");
        }
    }

    static class MyResource2 implements AutoCloseable {
        public void close() throws Exception {
            System.out.println("MyResource2 was closed!");
        }
    }

    public static void main(String[] args) throws Exception {
        try (MyResource1 myResource1 = new MyResource1();
             MyResource2 myResource2 = new MyResource2()) {

        }

    }

}
