package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class Data4 implements Serializable {
    public Data4() {
        System.out.println("Data4");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("Data4.writeObject");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("Data4.readObject");
    }
}
