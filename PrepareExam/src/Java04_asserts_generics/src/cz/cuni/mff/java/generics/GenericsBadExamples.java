/* $Id$ */
package Java04_asserts_generics.src.cz.cuni.mff.java.generics;

import java.util.*;

public class GenericsBadExamples {

    public static void main(String[] argv) {

        ///////////////////////////////////////
        // Zmeny v typ. parametrech
        // nejsou povoleny
        //////////////////////////////////////
        List<String> ls = new ArrayList<String>();
        // List<Object> lo = ls;  // BAD line


        /////////////////////////////////////
        // Nelze pouzit na nic jineho nez na
        // kolekci Object
        /////////////////////////////////////
        // printCollection(ls);  // BAD line

        ////////////////////////////////////
        // OK
        ////////////////////////////////////
        printCollection2(ls);

        ////////////////////////////////////
        // Nelze naalokovat pole
        ////////////////////////////////////
        // List<String>[] lsa = new List<String>[10]; // BAD line

        ///////////////////////////////////
        // Tahle jiz lze
        ///////////////////////////////////
        List<?>[] lsb = new List<?>[10];

    }


    ////////////////////////////////////////
    // Lze pouzit pouze na kolekci presne
    // s typem Object
    ////////////////////////////////////////
    static void printCollection(Collection<Object> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }

    ////////////////////////////////////////
    // Spravna varianta predchozi metody
    ////////////////////////////////////////
    static void printCollection2(Collection<?> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }


    ////////////////////////////////////////
    // Do Collection<?> nelze prirazovat
    ////////////////////////////////////////
    static void fillCollection(Collection<?> c) {
        // c.add(new Object());   // BAD line
    }
}
