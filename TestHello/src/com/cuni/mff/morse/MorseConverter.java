package com.cuni.mff.morse;

import javax.crypto.spec.ChaCha20ParameterSpec;
import java.util.HashMap;
import java.util.Scanner;

public class MorseConverter {

    public static void main(String args[]){
        HashMap<Character,String> letterToMorseMap = new HashMap<Character, String>();
        letterToMorseMap.put('a',".-");
        letterToMorseMap.put('b',"-...");
        letterToMorseMap.put('c',"-.-.");
        letterToMorseMap.put('d',"-..");
        letterToMorseMap.put('e',".");
        letterToMorseMap.put('f',"..-.");
        letterToMorseMap.put('g',"--");
        letterToMorseMap.put('h',"....");
        letterToMorseMap.put('i',"..");
        letterToMorseMap.put('j',".---");
        letterToMorseMap.put('k',"-.-");
        letterToMorseMap.put('l',".-..");
        letterToMorseMap.put('m',"--");
        letterToMorseMap.put('n',"-.");
        letterToMorseMap.put('o',"---");
        letterToMorseMap.put('p',".--.");
        letterToMorseMap.put('q',"--.-");
        letterToMorseMap.put('r',".-.");
        letterToMorseMap.put('s',"...");
        letterToMorseMap.put('t',"-");
        letterToMorseMap.put('u',"..-");
        letterToMorseMap.put('v',"...-");
        letterToMorseMap.put('w',".--");
        letterToMorseMap.put('x',"-..-");
        letterToMorseMap.put('y',"-.--");
        letterToMorseMap.put('z',"--..");

        Scanner inputUser = new Scanner(System.in);
        System.out.println("Enter the sentence(s):");
        String inputData = inputUser.nextLine();
        System.gc();
        inputData = inputData.toLowerCase();
        for(char character : inputData.toCharArray()){
            if(letterToMorseMap.containsKey(character))
                System.out.print(letterToMorseMap.get(character));
        }
        System.out.println();
    }
}
