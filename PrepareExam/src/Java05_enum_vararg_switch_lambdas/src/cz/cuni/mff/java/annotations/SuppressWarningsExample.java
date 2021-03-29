package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.annotations;

public class SuppressWarningsExample {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        DeprecationExample.foo();                // no warning here
    }
}
