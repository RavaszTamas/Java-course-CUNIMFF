package prepare;

interface MyIface {
    int value();
}

public class MyClass {
    class InnerClass {
        int i = 0;
        public int value() { return i; }
    }
    public InnerClass add() {
        return new InnerClass();
    }
    public static void main(String[] args){
        MyClass p = new MyClass();
        MyClass.InnerClass a = p.new InnerClass();
    }
}
