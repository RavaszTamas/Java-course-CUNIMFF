package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.vararg;

public class VarArg3 {

    public static void vararg(Object... args) {
        System.out.println("Vararg...");
        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {
        vararg("Hello", "world", "!");
        vararg(new Object[]{"Hello", "world", "!"});
        vararg((Object) new Object[]{"Hello", "world", "!"});
    }
}
