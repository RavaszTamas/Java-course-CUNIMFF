package Java02_inheritance.src.cz.cuni.mff.java.example02;

public class ConditionTest {

    public static void main(String[] argv) {
        int i = Integer.MIN_VALUE;

        if (i == -i && i != 0) {
            System.out.println("ANO");
        } else {
            System.out.println("NE");
        }
    }


}
