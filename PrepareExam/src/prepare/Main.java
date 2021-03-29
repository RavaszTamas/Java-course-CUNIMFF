package prepare;

import static java.lang.Math.scalb;

public class Main {

    public static void main(String[] args) {

        StringBuffer asd = new StringBuffer("asdasd");
        String asda = new String(asd);
        asd.append('a');
        System.out.println(asda.compareTo(asd.toString()));
        System.out.println(asd + " " + asda);
    }


}
