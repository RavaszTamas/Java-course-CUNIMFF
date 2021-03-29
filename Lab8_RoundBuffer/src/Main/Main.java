package Main;

import MyThreads.Getter;
import MyThreads.Putter;
import RoundBuffer.RoundBuffer;

public class Main {

    public static void main(String[] args) {
        RoundBuffer buffer = new RoundBuffer(100);
        Getter get1 = new Getter(buffer);
        Getter get2 = new Getter(buffer);
        Getter get3 = new Getter(buffer);
        Getter get4 = new Getter(buffer);
        Putter put1 = new Putter(buffer,System.currentTimeMillis());
        Putter put2 = new Putter(buffer,System.currentTimeMillis()*10);
        put1.start();
        get1.start();
        put2.start();
        get2.start();
        get3.start();
        get4.start();

        while (put1.isAlive())
        {
            try{
                put1.join();
            }
            catch (InterruptedException e)
            {

            }
        }
        while (get1.isAlive())
        {
            try{
                get1.join();
            }
            catch (InterruptedException e)
            {

            }
        }

        while (put2.isAlive())
        {
            try{
                put1.join();
            }
            catch (InterruptedException e)
            {

            }
        }
        while (get2.isAlive())
        {
            try{
                get2.join();
            }
            catch (InterruptedException e)
            {

            }
        }

    }
}
