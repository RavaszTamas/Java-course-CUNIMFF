package Java09.src.cz.cuni.mff.java.collections;

import java.util.*;
import java.util.stream.Collectors;

public class Print {

    static Collection fill(Collection<String> c) {
        System.out.println(c.add("cat"));
        System.out.println(c.add("dog"));
        System.out.println(c.add("cat"));
        System.out.println(c.add("do"));
        return c;
    }

    static Map fill(Map<String, String> m) {
        m.put("dog", "Bosco");
        m.put("dog", "Spot");
        m.put("cat", "Rags");
        return m;
    }

    public static void main(String[] args) {
        ArrayList<String> a;
        System.out.println(fill(a = new ArrayList<>()));
        System.out.println(fill(new HashSet<>()));
        System.out.println(fill(new HashMap<>()));
        System.out.println("H" + "a");
        System.out.println('H' + 'a');
    }
}
