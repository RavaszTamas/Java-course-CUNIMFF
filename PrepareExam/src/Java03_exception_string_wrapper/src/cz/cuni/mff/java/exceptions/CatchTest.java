package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class CatchTest {

    public static void main(String[] argv) {
        try {

            throw new MyException("Exception occurred");

        } catch (MyException e) {
            System.out.println("MyException");
        } catch (Exception e) {  // order of the exceptions cannot be changed here
            System.out.println("Exception");
        }
    }

}
