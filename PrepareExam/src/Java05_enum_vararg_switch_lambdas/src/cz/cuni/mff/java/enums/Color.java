package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.enums;

public enum Color {
    RED, GREEN, BLUE;

    public static void main(String[] argv) {
        Color c = RED;
        System.out.println(c);
        System.out.println(Color.RED.toString());
        for (Color o : Color.values()) {
            System.out.println(o.ordinal());
        }
    }
}
