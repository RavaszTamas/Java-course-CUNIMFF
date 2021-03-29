package com.cuni.lab4.Assignment1;

import java.util.Arrays;
import java.util.Iterator;

public class MyString implements Iterable{

    Character[] myStr;
    public MyString(){
        myStr = new Character[0];
    }
    public MyString(String str){
        myStr = new Character[str.length()];
        for(int i = 0; i < str.length(); i++)
        {
            myStr[i] = str.charAt(i);
        }
    }
    public void append(Character c)
    {
        append(""+c);
    }
    public void append(String str)
    {
        int oldLen = myStr.length;
        myStr = Arrays.copyOf(myStr,myStr.length+str.length());
        for(int i = oldLen; i < oldLen+str.length();i++)
        {
            myStr[i] = str.charAt(i-oldLen);
        }
    }


    @Override
    public String toString() {
        return Arrays.toString(myStr);
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int cnt = 0;
            @Override
            public boolean hasNext() {
                if(cnt < myStr.length)
                    return true;
                return false;
            }

            @Override
            public Object next() {
                return myStr[cnt++];
            }
        };
    }
}
