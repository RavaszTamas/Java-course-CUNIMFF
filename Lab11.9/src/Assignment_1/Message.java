package Assignment_1;

public class Message {
    private int priority;
    private String message;

    public int getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }

    public Message(int priority, String message) {
        this.priority = priority;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
