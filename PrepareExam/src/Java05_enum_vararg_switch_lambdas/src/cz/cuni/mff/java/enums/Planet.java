package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.enums;

public enum Planet {

    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6);

    private final double mass;
    private final double radius;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double surfaceGravity() {
        return 6.67428e-11 * mass / (radius * radius);
    }

    public static void main(String[] argv) {
        Planet e = EARTH;

        System.out.println(e.ordinal());
        System.out.println(e.surfaceGravity());
        System.out.println(VENUS.surfaceGravity());

    }
}
