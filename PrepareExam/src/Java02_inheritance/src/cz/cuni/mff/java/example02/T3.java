package Java02_inheritance.src.cz.cuni.mff.java.example02;

class T1 {
    int x = 1;
}

class T2 extends T1 {
    int x = 2;
    void asd()
    {

    }
}

public class T3 extends T2 {
    int x = 3;

    void test() {
        System.out.println(x);
        System.out.println(super.x);
        System.out.println(((T2) this).x);
        System.out.println(((T1) this).x);
    }

    public static void main(String[] argv) {
        T3 o = new T3();

        o.test();
    }
}
