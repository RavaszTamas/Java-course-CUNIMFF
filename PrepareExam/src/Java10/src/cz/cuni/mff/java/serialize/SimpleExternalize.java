package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class SimpleExternalize {
    public static void main(String[] argv) {
        Data2 data1 = new Data2();
        Data2 data2 = new Data2();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.dat"))) {
            out.writeObject(data1);
            out.writeObject(data2);
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.dat"))) {
            Data2 data3 = (Data2) in.readObject();
            Data2 data4 = (Data2) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            System.exit(1);
        }

    }
}
