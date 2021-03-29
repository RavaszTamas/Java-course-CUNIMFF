package Java10.src.cz.cuni.mff.java.prefs;

import java.util.prefs.*;

public class Prefs {
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(Prefs.class);
        prefs.put("url", "http://somewhere/");
        prefs.putInt("port", 1234);
        prefs.putBoolean("connected", true);
        int port = prefs.getInt("port", 1234);

        String[] keys = prefs.keys();
        for (String key : keys) {
            System.out.println(key + ": " + prefs.get(key, null));
        }
    }
}

