package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.vararg;

public class VarArg {

    public static void varargs(String... args)
    {
        for (String o : args)
        {
            System.out.println(o.trim());
        }
    }

    public static void vararg(Object... args) {
        for (Object o : args) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        vararg("Hello", "world", "!");
        varargs(" asd 0 ", "asd", "   12312");
    }
}
