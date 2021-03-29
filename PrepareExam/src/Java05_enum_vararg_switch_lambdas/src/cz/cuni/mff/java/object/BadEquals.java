package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.object;

public class BadEquals {
    private int value;

    public BadEquals(int value) {
        this.value = value;
    }

    // this method does NOT OVERRIDE Object.equals
    // it OVERLOADS Object.equals
    public boolean equals(BadEquals obj) {
        return value == obj.value;
    }

    public static void main(String[] args) {
        BadEquals b1 = new BadEquals(1);
        BadEquals b2 = new BadEquals(1);

        System.out.println(b1.equals(b2));

        Object bo1 = b1;
        Object bo2 = b2;

        System.out.println(bo1.equals(bo2));
    }
}
