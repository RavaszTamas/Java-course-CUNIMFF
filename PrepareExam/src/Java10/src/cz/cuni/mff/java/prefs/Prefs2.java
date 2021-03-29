package Java10.src.cz.cuni.mff.java.prefs;

import java.util.prefs.*;

public class Prefs2 {
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(Prefs.class);

        String[] keys = prefs.keys();
        for (String key : keys) {
            System.out.println(key + ": " + prefs.get(key, null));
        }
    }
}

