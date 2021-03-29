package Java11.src.cz.cuni.mff.java.locales;

import java.util.*;

public class MyResources_cs extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"OkKey", "OK"},
            {"CancelKey", "Zru�it"},
    };
}
