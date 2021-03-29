package prepare;

public class ParamTest {
    public ParamTest(Object o) {
        System.out.println("ParamsTest(Object o)");
    }
    public ParamTest(long[] a) {
        System.out.println("ParamsTest(long[] a)");
    }
    public static void main(String[] argv) {
        new ParamTest(null);
    }
}