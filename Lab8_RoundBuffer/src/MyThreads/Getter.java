package MyThreads;

import RoundBuffer.RoundBuffer;

import java.util.Random;

public class Getter extends Thread {

    RoundBuffer myBuffer;
    public Getter(RoundBuffer paramBuffer){myBuffer = paramBuffer;}
    @Override
    public void run()
    {
        Random rnd = new Random();
        while (true)
        {

            try {
                String stringToGet = myBuffer.get();
                System.out.println(stringToGet + " " + stringToGet.length() + "  " + this.getName());
                Thread.sleep(rnd.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
