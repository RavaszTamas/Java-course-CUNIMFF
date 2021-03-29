package Java08.src.cz.cuni.mff.java.properties;

import java.util.*;

public class ShowAll {
    public static void main(String[] args) {
        Properties p = System.getProperties();
        p.forEach((k,v) -> System.out.println(k + ": " + v));
    }
}
