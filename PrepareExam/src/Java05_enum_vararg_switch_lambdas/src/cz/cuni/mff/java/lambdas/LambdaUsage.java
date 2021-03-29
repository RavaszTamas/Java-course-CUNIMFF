package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.lambdas;

public class LambdaUsage {
    /**
     * Second argument can be a lambda expression.
     */
    public static void applyOnEachItem(String[] arr, Ice e) {
        for (String s : arr) {
            e.foo(s);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"Hello", "world", "!"};

        LambdaUsage.applyOnEachItem(arr, s -> System.out.println(s));
        // or, equivalently, we can pass directly a reference to the println method
        LambdaUsage.applyOnEachItem(arr, System.out::println);

    }
}
