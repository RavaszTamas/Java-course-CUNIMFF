package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.enums;

public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    double eval(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown op: " + this);
    }

    public static void main(String[] argv) {
        Operation p = MINUS;
        System.out.println(p.eval(1, 3));
    }
}
