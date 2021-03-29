package Java03_exception_string_wrapper.src.cz.cuni.mff.java.exceptions;

public class CauseTest {

    void m() {
        int[] array = {1, 2};
        array[3] = 4;
    }

    public static void main(String[] argv) throws MyException {
        try {
            CauseTest nc = new CauseTest();
            nc.m();
        } catch (Exception e) {
            throw new MyException("Exception occurred", e);
        }
    }

}
