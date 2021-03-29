package Java10.src.cz.cuni.mff.java.serialize;

import java.io.Serializable;

public class Node implements Serializable {
    public Node left = null;
    public Node right = null;

    public Node() {
    }

    public void printTree(int depth) {
        printSpaces(depth);
        System.out.println(toString());
        printSpaces(depth);
        System.out.println("left: ");
        if (left != null)
            left.printTree(depth + 1);
        else {
            printSpaces(depth);
            System.out.println("null");
        }
        printSpaces(depth);
        System.out.println("right: ");
        if (right != null)
            right.printTree(depth + 1);
        else {
            printSpaces(depth);
            System.out.println("null");
        }
    }

    private static void printSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("  ");
        }
    }
}
