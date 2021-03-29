package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.lambdas;

public class NoLambdaUsage {

    public static void main(String[] args) {
        String[] arr = {"Hello", "world", "!"};

        LambdaUsage.applyOnEachItem(arr, new Ice() {
            @Override
            public void foo(String s) {
                System.out.println(s);
            }
        });
    }

}
