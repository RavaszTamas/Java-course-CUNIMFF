package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class Data2 implements Externalizable {
    public Data2() {
        System.out.println("Data2");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Data2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Data2.readExternal");
    }
}
