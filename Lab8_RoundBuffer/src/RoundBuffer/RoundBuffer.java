package RoundBuffer;

public class RoundBuffer{

    private int Size;
    StringBuffer buffer;
    public  RoundBuffer()
    {
        this(100);
    }

    public RoundBuffer(int paramSize)
    {
        this.Size = paramSize;
        this.buffer = new StringBuffer(this.Size);
    }

    synchronized public void put(String stringToPut) throws InterruptedException
    {
        while (stringToPut.length() + buffer.length() > this.Size)
            wait();
        this.buffer.append(stringToPut);
        notify();
    }
    synchronized public String get() throws InterruptedException
    {
        while (this.buffer.length() == 0)
            wait();
        String toReturn = this.buffer.toString();
        this.buffer.delete(0,buffer.capacity());
        notify();
        return  toReturn;
    }
}
