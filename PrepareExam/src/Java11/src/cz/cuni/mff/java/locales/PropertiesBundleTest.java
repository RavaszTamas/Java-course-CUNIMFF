package Java11.src.cz.cuni.mff.java.locales;

import java.util.*;

public class PropertiesBundleTest {

    public static void main(String[] args) {
        ResourceBundle rs = ResourceBundle.getBundle("cz/cuni/mff/java/locales/presources");

        System.out.println(rs.getString("OkKey"));
        System.out.println(rs.getString("CancelKey"));
    }

}
