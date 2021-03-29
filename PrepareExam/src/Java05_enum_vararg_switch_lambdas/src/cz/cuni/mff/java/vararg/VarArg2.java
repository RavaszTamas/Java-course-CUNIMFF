package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.vararg;

public class VarArg2 {

    public static void vararg(Object... args) {
        System.out.println("Vararg...");
        for (Object arg : args) {
            System.out.println(arg);
        }
    }
/*
    public static void vararg(Object[] args) {
        System.out.println("Vararg[]");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
*/
    public static void main(String[] args) {
        vararg("Hello", "world", "!");
    }
}
