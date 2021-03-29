package Java10.src.cz.cuni.mff.java.serialize;

import java.io.*;

public class TreeSerialize1 {
    public static void main(String[] argv) {
        Node tr1 = new Node();
        Node tr2 = new Node();
        Node tr3 = new Node();
        Node tr4 = new Node();

        tr1.left = tr2;
        tr1.right = tr3;
        tr2.right = tr4;
        tr3.left = tr4;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.dat"))) {
            System.out.println("Pred ulozenim");
            tr1.printTree(0);
            System.out.println();
            out.writeObject(tr1);
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.dat"))) {
            Node s1 = (Node) in.readObject();
            System.out.println("Po nacteni");
            s1.printTree(0);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            System.exit(1);
        }

    }
}
