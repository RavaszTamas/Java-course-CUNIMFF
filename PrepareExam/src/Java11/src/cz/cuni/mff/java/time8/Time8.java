package Java11.src.cz.cuni.mff.java.time8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Time8 {

    public static void main(String[] args) {
        Instant inst = Instant.now();
        System.out.println(inst);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime ldt2 = ldt.plusDays(30);
        System.out.println(ldt2);

        System.out.println(Duration.between(ldt, ldt2));

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
        ZonedDateTime zdt2 = zdt.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println(zdt2);

        System.out.println(Duration.between(zdt, zdt2));

        System.out.println("Zones:");
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }

}
