package prepare;

class A {
    public String className = "A";
}
class B extends A {
    private String className = "B";
}
public class Test02 {
    public static void main(String[] argv) {
        //System.out.println((A)(new B()).className);
    }
}