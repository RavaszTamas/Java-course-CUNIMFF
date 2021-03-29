package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class SimpleSerialize {
    public static void main(String[] argv) {
        Data data1 = new Data(1);
        Data data2 = new Data(4);

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("file.dat")))) {
            System.out.println(data1);
            System.out.println(data2);
            out.writeObject(data1);
            out.writeObject(data2);
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.dat"))) {
            Data data3 = (Data) in.readObject();
            Data data4 = (Data) in.readObject();
            System.out.println(data3);
            System.out.println(data4);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            System.exit(1);
        }

    }
}
