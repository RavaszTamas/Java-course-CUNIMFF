package com.cuni.lab8.assignment2;

public class Main {

    public static void main(String[] args) {
        RoundBuffer buff = new RoundBuffer(10000);
        Getter g1 = new Getter(buff);
        Getter g2 = new Getter(buff);
        Putter p1= new Putter(buff);
        p1.start();
        g1.start();
        g2.start();
        while (p1.isAlive())
        {
            try {
                p1.join();
            } catch (InterruptedException e) {

            }
        }
        while (g1.isAlive())
        {
            try {
                g1.join();
            } catch (InterruptedException e) {
            }
        }
        while (g2.isAlive())
        {
            try {
                g2.join();
            } catch (InterruptedException e) {

            }
        }

    }
}
