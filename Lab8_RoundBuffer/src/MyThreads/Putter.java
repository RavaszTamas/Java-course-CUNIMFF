package MyThreads;

import RoundBuffer.RoundBuffer;

import java.util.Random;

public class Putter extends Thread {

    RoundBuffer myBuffer;
    long seed;
    public Putter(RoundBuffer paramBuffer, long paramSeed){myBuffer = paramBuffer;seed=paramSeed;}

    @Override
    public void run()
    {
        Random rnd = new Random(seed);
        while (true)
        {

            try {
                myBuffer.put(Integer.toString(rnd.nextInt(999999)));
                Thread.sleep(rnd.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
