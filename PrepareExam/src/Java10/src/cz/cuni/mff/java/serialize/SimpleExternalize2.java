package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class SimpleExternalize2 {
    public static void main(String[] argv) {
        Data3 data1 = new Data3();
        Data3 data2 = new Data3();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.dat"))) {
            out.writeObject(data1);
            out.writeObject(data2);
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.dat"))) {
            Data3 data3 = (Data3) in.readObject();
            Data3 data4 = (Data3) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            System.exit(1);
        }

    }
}
