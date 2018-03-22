package adbmacros;

/**
 *
 * @author Vitor
 */
public class Event {
    private String name;
    private int    waitingTime;

    public Event(String name, int waitingTime) {
        this.name = name;
        this.waitingTime = waitingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
    
}
