package Java02_inheritance.src.cz.cuni.mff.java.example02;

interface pista
{
    int a = 5;
    String s = "hello";
    static void alpha()
    {
        System.out.println("sd");
    }
}

abstract class asd implements  pista
{
    abstract int draw();
}

class asdasd extends asd
{

    @Override
    int draw() {

        return a;
    }
}
class TX1 {
    public void foo() {
        System.out.println("TX1.foo()");
    }
}

class TX2 extends TX1 {
    public void foo() {
        System.out.println("TX2.foo()");
    }
}

public class TX3 extends TX2 {

    public void foo() {
        super.foo();
        //((TX1) this).foo();
        System.out.println("TX3.foo()");
    }

    public static void main(String[] argv) {
        TX3 o = new TX3();

        o.foo();
    }
}
