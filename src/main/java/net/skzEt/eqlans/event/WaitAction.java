package net.skzEt.eqlans.event;


public class WaitAction {
    public int ticks;
    public Runnable action;

    public WaitAction(int ticks, Runnable action) {
        this.ticks = ticks;
        this.action = action;
        CommonEvents.queue.add(this);
    }
}
