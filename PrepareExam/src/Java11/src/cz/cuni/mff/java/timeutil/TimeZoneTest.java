package Java11.src.cz.cuni.mff.java.timeutil;

import java.util.TimeZone;

public class TimeZoneTest {

    public static void main(String[] args) {
        TimeZone tz = TimeZone.getDefault();

        System.out.println("Default TimeZone: " + tz.getID());
        System.out.println("Display name    : " + tz.getDisplayName());

        String[] tzones = TimeZone.getAvailableIDs();

        System.out.println("\nAvailable TimeZones\n===================");
        for (String tzone : tzones) {
            System.out.println(tzone);
        }
    }

}
