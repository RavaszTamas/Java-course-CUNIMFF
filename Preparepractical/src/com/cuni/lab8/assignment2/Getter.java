package com.cuni.lab8.assignment2;

import java.util.Random;

public class Getter extends Thread {

    RoundBuffer buffer;
    Getter(RoundBuffer b){
        buffer =b;
    }

    @Override
    public void run() {
        while (true)
        {
            Random rnd = new Random();
            try {
                String data = buffer.get();
                System.out.println(data + " " + this.getName());
                sleep(rnd.nextInt(1000));
            }
            catch (InterruptedException ignored)
            {

            }
        }
    }
}
