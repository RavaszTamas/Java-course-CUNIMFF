package com.cuni.lab4.Assignment1;

import com.cuni.lab4.Assignment1.MyString;

public class Main {

    public static void main(String[] args) {
        MyString str = new MyString("1");
        str.append("2");
        str.append('2');
        for(Object o: str)
        {
            System.out.println(o);
        }
    }
}
