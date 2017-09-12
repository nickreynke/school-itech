package com.reynke.school.ae09mvcmicrowaveobserverpattern.Model;

import java.util.Observable;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class Microwave extends Observable {

    private int timeInSeconds = 0;
    private int timeRemainingInSeconds = 0;
    private boolean running = false;
    private boolean doorOpen = false;
    private boolean tubeOn = false;
    private MicrowaveLamp microwaveLamp;

    public Microwave() {
        this.microwaveLamp = new MicrowaveLamp();
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
        this.setChanged();
    }

    public int getTimeRemainingInSeconds() {
        return timeRemainingInSeconds;
    }

    public void setTimeRemainingInSeconds(int timeRemainingInSeconds) {
        this.timeRemainingInSeconds = timeRemainingInSeconds;
        this.setChanged();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        this.setChanged();
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(boolean doorOpen) {
        this.doorOpen = doorOpen;
        this.setChanged();
    }

    public boolean isTubeOn() {
        return tubeOn;
    }

    public void setTubeOn(boolean tubeOn) {
        this.tubeOn = tubeOn;
        this.setChanged();
    }

    public MicrowaveLamp getMicrowaveLamp() {
        return microwaveLamp;
    }

    public void setMicrowaveLamp(MicrowaveLamp microwaveLamp) {
        this.microwaveLamp = microwaveLamp;
        this.setChanged();
    }

}
