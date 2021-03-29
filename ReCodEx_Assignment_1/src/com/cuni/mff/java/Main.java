package com.cuni.mff.java;

public class Main {

    public static void main(String[] args) {
      int num  = Integer.parseInt(args[0]);
      for(int i = 1; i <= 10; i++)
          System.out.println(i+" * " + num+ " = " + i * num);
    }
}
