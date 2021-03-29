package Java02_inheritance.src.cz.cuni.mff.java.example02;

interface If1 {
    default void foo() {
        System.out.println("interface");
    }
}
class A {
    public void foo() {
        System.out.println("class");
    }
}

class B extends A implements If1 {
    public static void main(String[] args) {
        B b = new B();
        b.foo(); // -> "class"
    }
}


public class ConstructorTest3 extends ConstructorTest2 {

    public ConstructorTest3(int a) {
        super(a);
        System.out.println("Test3(int)");
    }

    public ConstructorTest3() {
        this(0);
        System.out.println("Test3()");
    }

    public static void main(String[] argv) {
        ConstructorTest3 t3 = new ConstructorTest3();
    }
}
