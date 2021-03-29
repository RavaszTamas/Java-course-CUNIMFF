package Java02_inheritance.src.cz.cuni.mff.java.example02;

public class LoopTest {

    public static void main(String[] argv) {
        int START = 2000000000;
        int count = 0;

        for (float f = START; f < START + 50; f++) {
            count++;
        }

        System.out.println(count);
        Object[] oa = new Object [2];
        oa[0] = new String("hello");
        oa[1] = new String("world");
        //String[] sa1 = oa; // error
        String[] sa2 = {(String) oa[0],(String) oa[1]};
    }
}
