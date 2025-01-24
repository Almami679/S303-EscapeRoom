import org.example.observers.Observer;

public class ObserverTest implements Observer {
    private String receivedMessage;

    @Override
    public void update(String msg) {
        this.receivedMessage = msg;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public void clearReceivedMessage() {
        this.receivedMessage = null;
    }
}

