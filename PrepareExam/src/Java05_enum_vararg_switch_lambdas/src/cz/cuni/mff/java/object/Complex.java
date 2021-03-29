package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.object;

public class Complex {

    private long x, y;

    public Complex(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Complex) {
            Complex c = (Complex) obj;
            if (c.x == x && c.y == y) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] argv) {
        Complex a = new Complex(5, 6);
        Complex b = new Complex(5, 6);

        System.out.println(a == b);
        System.out.println(((Object) a).equals(b));
    }
}
