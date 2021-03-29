package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.enums;

public enum Operation2 {

    PLUS { double eval(double x, double y) { return x+y; }},
    MINUS { double eval(double x, double y) { return x-y; }},
    TIMES { double eval(double x, double y) { return x*y; }},
    DIVIDE { double eval(double x, double y) { return x/y;}};

    abstract double eval(double x, double y);

    public static void main(String[] argv) {
        Operation2 p = MINUS;
        System.out.println(p.eval(1, 3));
    }
}

