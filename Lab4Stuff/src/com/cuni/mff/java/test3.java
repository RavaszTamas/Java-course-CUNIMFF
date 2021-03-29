package com.cuni.mff.java;
class A {
    public static void foo() {
        System.out.println("foo");
    }
}
    class B extends A{
        public static void foo(){
            System.out.println("bar");
        }
    }

public class test3 {



    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        a.foo();
        ((B)b).foo();

    }

}
