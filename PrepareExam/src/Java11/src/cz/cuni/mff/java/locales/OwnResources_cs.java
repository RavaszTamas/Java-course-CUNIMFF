package Java11.src.cz.cuni.mff.java.locales;

import java.util.*;

public class OwnResources_cs extends ResourceBundle {

    private final HashMap t;

    public OwnResources_cs() {
        t = new HashMap();
        t.put("CancelKey", "Zrusit");
    }

    @Override
    public Object handleGetObject(String key) {
        return t.get(key);
    }

    @Override
    public Enumeration getKeys() {
        return Collections.enumeration(t.keySet());
    }

}
