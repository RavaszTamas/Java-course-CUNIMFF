package Java05_enum_vararg_switch_lambdas.src.cz.cuni.mff.java.sw;

public class SwitchExample01 {

    static void howMany(int k) {
        /*switch (k) {
            case 1 -> System.out.println("one");
            case 2 -> System.out.println("two");
            case 3 -> System.out.println("many");
        }*/
    }

    static void howManyOldWay(int k) {
        switch (k) {
            case 1:
                System.out.println("one");
                break;   // break needed
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("many");
        }
    }

    static void isWeekend(String day) {
        /*switch (day) {
            case "mon", "tue", "wed", "thu", "fri" -> System.out.println("Working day");
            case "sat", "sun" -> System.out.println("Weekend");
            default -> System.out.println("Unknown day");
        }*/
    }

    static boolean isWeekendExpr(String day) {
        /*return switch (day) {
            case "mon", "tue", "wed", "thu", "fri" -> false;
            case "sat", "sun" -> true;
            default -> throw new IllegalArgumentException("oops!");
        };*/
        return  true;
    }

    static boolean isWeekendExpr2(String day) {
        /*return switch (day) {
            case "mon", "tue", "wed", "thu", "fri" -> false;
            case "sat", "sun" -> true;
            default -> {
                System.out.printf("unknown day: %s%n", day);
                yield false;
            }
        };*/
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            howMany(i);
            howManyOldWay(i);
        }

        isWeekend("tue");
        isWeekend("sun");
        isWeekend("san");

        System.out.println(isWeekendExpr("sun"));
        System.out.println(isWeekendExpr2("san"));
    }
}
