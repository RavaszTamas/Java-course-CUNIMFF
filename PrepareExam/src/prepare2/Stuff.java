package prepare2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

class arany
{
    void ara()
    {

    }

}
interface ezust
{
    void ara();
}

class erme extends arany implements ezust
{
    static void  elem()
    {

    }
    @Override
    public void ara() {

    }
}


class igen extends erme
{
}

public class Stuff {

    int val;
    Stuff(int p)
    {
        p = val;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Stuff)
        {
            if(o == this)
                return true;
            Stuff other = (Stuff) o;
            if(other.val == this.val)
                return true;
        }
        return false;
    }
    public static <T>  T dos(T val)
    {
        return val;
    }
    public static void main(String[] args) {
        int a =1;
        switch (a)
        {
            case 1:
            case 2:
                System.out.println("asd");
                break;
        }
        String greeting = "Hello world";
        for (int i = 0; i < greeting.length(); i++) {
            System.out.write(greeting.charAt(i));
        }
        System.out.flush();
        System.out.println(dos(1));




        Path p = Paths.get("Java09/text.txt");
        Path p2 = Paths.get("Java09/text.txt");
        Path p1_to_p2 = p.relativize(p2);
        System.out.println(p1_to_p2);
        System.out.println(p.toAbsolutePath());


        String fullClassName = "cz.cuni.mff.java.io.Slasher";
        String fileName =
                fullClassName.replaceAll("\\.", "/") + ".java";
        System.out.println("The class " + fullClassName +
                " must be in the file " + fileName);

        for (var ps:
             p) {
            System.out.println(ps);
        }

        try(DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Java09/text.txt"))))
        {
            os.write('A');
            os.flush();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        try(FileChannel fc = new FileOutputStream("Java09/text.txt").getChannel())
        {
            fc.write(ByteBuffer.wrap("ásd".getBytes()));
        }
        catch (IOException e)
        {

        }
        ByteBuffer bb = ByteBuffer.allocate(1024);
        IntBuffer bbb = bb.asIntBuffer();
        bbb.put(123);

        System.out.println(bb.getInt());
        Integer[] arr = {1,2,3,4,5,6};
        Arrays.sort(arr,(Integer o, Integer u) -> o - u);
    }


}

