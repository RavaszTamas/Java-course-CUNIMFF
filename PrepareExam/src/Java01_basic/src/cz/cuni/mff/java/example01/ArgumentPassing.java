package Java01_basic.src.cz.cuni.mff.java.example01;

public class ArgumentPassing {

    static void plusOne(int a) {
        a = a + 1;
    }

    static void usePlusOne() {
        int a = 5;
        plusOne(a);
        System.out.println(a);
    }

    static void appendA(StringBuilder sb) {
        sb.append("A");
    }

    static void useAppendA() {
        StringBuilder sb = new StringBuilder("hello");
        appendA(sb);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        usePlusOne();
        useAppendA();
    }
}
