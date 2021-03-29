package Java11.src.cz.cuni.mff.java.locales;

import java.util.*;

public class OwnResources extends ResourceBundle {

    private final HashMap t;

    public OwnResources() {
        t = new HashMap();
        t.put("OkKey", "OK");
        t.put("CancelKey", "Cancel");
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
