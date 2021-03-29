package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class Data3 implements Externalizable {
    Data3() {
        System.out.println("Data3");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Data3.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Data3.readExternal");
    }
}
