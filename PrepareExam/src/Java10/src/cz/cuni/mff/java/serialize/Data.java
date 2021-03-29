package Java10.src.cz.cuni.mff.java.serialize;

import java.io.Serializable;

public class Data implements Serializable {
    private int d;

    Data(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return super.toString() + ", d=" + d;
    }
}
