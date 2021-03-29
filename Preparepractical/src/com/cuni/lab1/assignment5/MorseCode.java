package com.cuni.lab1.assignment5;

import java.util.HashMap;

public class MorseCode {

    static HashMap<Character,String > morse ;
    static {
        morse = new HashMap<>();
        morse.put('a',".-");
        morse.put('b',"-...");
        morse.put('c',"-.-.");
        morse.put('d',"-..");
        morse.put('e',".");
        morse.put('f',"..-.");
        morse.put('g',"--.");
        morse.put('h',"....");
        morse.put('i',"..");
        morse.put('j',".---");
        morse.put('k',"-.-");
        morse.put('l',".-..");
        morse.put('m',"--");
        morse.put('n',"-.");
        morse.put('o',"---");
        morse.put('p',".--.");
        morse.put('q',"--.-");
        morse.put('r',".-.");
        morse.put('s',"...");
        morse.put('t',"-");
        morse.put('u',"..-");
        morse.put('v',"...-");
        morse.put('w',".--");
        morse.put('x',"-..-");
        morse.put('y',"-.--");
        morse.put('z',"--..");
        morse.put('1',".----");
        morse.put('2',"..---");
        morse.put('3',"...--");
        morse.put('4',"....-");
        morse.put('5',".....");
        morse.put('6',"-....");
        morse.put('7',"--...");
        morse.put('8',"---..");
        morse.put('9',"----.");
        morse.put('0',"-----");
    }
    public static String MorseConverter(String s)
    {
        s = s.toLowerCase();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            if(morse.containsKey(s.charAt(i)))
                str.append(morse.get(s.charAt(i)));
        }
        return str.toString();
    }

    public static void main(String[] args) {
        for(String arg : args)
        {
            var elements = arg.split(" ");
            for(String el:elements)
            {
                System.out.print(MorseConverter(el) + "   ");
            }
        }
        System.out.println("");
    }
}
