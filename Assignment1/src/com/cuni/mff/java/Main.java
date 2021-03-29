package com.cuni.mff.java;


import com.cuni.mff.java.AVLTree.AVLTree;
import com.cuni.mff.java.ui.UI;

import java.util.function.Predicate;

public class Main {


    public static void main(String[] args) {

        AVLTree testTree = new AVLTree();
        testTree.insertElement(1);
        testTree.insertElement(3);
        testTree.insertElement(-1);
        testTree.insertElement(4);
        testTree.insertElement(6);
        for(Object O : testTree)
            System.out.println(O.toString());
        return;
        /*
        UI newUI = new UI();
        newUI.run();
        */
    }

}
