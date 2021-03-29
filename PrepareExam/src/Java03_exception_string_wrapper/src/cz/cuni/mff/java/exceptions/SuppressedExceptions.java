/* $Id$ */
package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class SuppressedExceptions {

    static class MyResource1 implements AutoCloseable {
        public void close() throws Exception {
            System.out.println("MyResource1 was closed!");
        }
    }

    static class MyResource2 implements AutoCloseable {
        public void close() throws Exception {
            throw new Exception("Resource 2");
        }
    }

    static void argtest(Object... a)
    {
        for(Object o : a)
        {
            System.out.println(o);
        }
    }

    static <T> void malac (T did)
    {
        System.out.println(did);
    }

    public static void main(String[] args) throws Exception {

        argtest("Ahoj", "jak", "se", "vede");
        argtest(new Object[] {"Ahoj", "jak", "se", "vede"});
        argtest((Object) new Object[] {"Ahoj", "jak", "se",
                "vede"});
        malac(new Object());

        int i = 1;
        switch (i) {
            case 1:
                i += 1;break;
            case 2:
                i += 1;
            case 3:
                i += 1;
        }
        System.out.println(i);

        try (MyResource1 myResource1 = new MyResource1();
             MyResource2 myResource2 = new MyResource2()) {
            throw new Exception("try exception");
        }
    }
}

