package com.cuni.mff.java;

public class Main {

    public static  boolean test(){
        try{
            return  true;
        }finally{
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
