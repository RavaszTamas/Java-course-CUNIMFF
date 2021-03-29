package Java11.src.cz.cuni.mff.java.timeutil;

import java.util.Calendar;

public class CalendarTest {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("\ncal.set(Calendar.MONTH, Calendar.FEBRUARY);");
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        System.out.println(cal.getTime());

        System.out.println("\ncal.set(Calendar.DAY_OF_MONTH, 31);");
        cal.set(Calendar.DAY_OF_MONTH, 31);
        System.out.println(cal.getTime());

        System.out.println("\ncal.add(Calendar.MONTH, 11);");
        cal.add(Calendar.MONTH, 11);
        System.out.println(cal.getTime());

        System.out.println("\ncal.roll(Calendar.MONTH, 11);");
        cal.roll(Calendar.MONTH, 11);
        System.out.println(cal.getTime());
    }

}
