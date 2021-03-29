package Java03_exception_string_wrapper.src.cz.cuni.mff.java.methods;

class Foo implements AutoCloseable
{
    @Override
    public void close()
    {
        System.out.println("closed");
    }
}

public class Inference {

    /*
    static class var {

    } */

    public static void main(String[] args) {
        var i = 1;
        //var var = i;
        int[] a = new int[1];
        Foo t = new Foo();
        try(t)
        {
            a[1] = 1;
        }
        finally {

        }
        System.out.println(i);
        //System.out.println(var);
    }
}
