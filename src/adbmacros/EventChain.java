package adbmacros;

import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class EventChain {
    private ArrayList<Event> listOfEvents;
    private int durationTime;
    private long startTime;
    private boolean started;

    public EventChain(ArrayList<Event> listOfEvents, int durationTime) {
        this.listOfEvents = listOfEvents;
        this.durationTime = durationTime;
        this.started = false;
        this.startTime = 0;
    }

    public ArrayList<Event> getListOfEvents() {
        return listOfEvents;
    }

    public void setListOfEvents(ArrayList<Event> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    
    public void stop() {
        this.started = false;
        this.startTime = 0;
    }
}
