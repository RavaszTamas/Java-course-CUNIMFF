package Java11.src.cz.cuni.mff.java.locales;

import java.util.Locale;

public class Locales {

    public static void main(String[] args) {
        System.out.println("Default locale: " + Locale.getDefault());

        System.out.println("\nAvailable locales\n================");
        Locale[] ls = Locale.getAvailableLocales();
        for (Locale l : ls) {
            System.out.println(l);
        }
    }
}
