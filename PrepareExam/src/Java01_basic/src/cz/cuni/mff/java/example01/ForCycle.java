package Java01_basic.src.cz.cuni.mff.java.example01;

public class ForCycle {

    public static void main(String[] argv) {
        int j = 0;
        for (int i = Integer.MAX_VALUE - 10; i <= Integer.MAX_VALUE; i++) {
            j++;
        }
        System.out.println(j);
    }

}
