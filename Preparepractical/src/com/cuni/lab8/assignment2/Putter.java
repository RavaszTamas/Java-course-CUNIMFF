package com.cuni.lab8.assignment2;

import java.util.Random;

public class Putter extends Thread {

    RoundBuffer buffer;
    Putter(RoundBuffer b){
        buffer =b;
    }

    @Override
    public void run() {
        while (true)
        {
            Random rnd = new Random();
            try {
                buffer.put(Integer.toString(rnd.nextInt(10)));
                sleep(rnd.nextInt(100));
            }
            catch (InterruptedException ignored)
            {

            }
        }
    }
}
