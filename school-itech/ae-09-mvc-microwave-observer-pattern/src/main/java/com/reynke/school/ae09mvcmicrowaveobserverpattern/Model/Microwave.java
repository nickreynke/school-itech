package com.reynke.school.ae09mvcmicrowaveobserverpattern.Model;

import java.awt.*;
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
    private boolean lampOn = false;
    private Color lampColor;

    public Microwave() {
        this.lampColor = new Color(0);
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {

        if (this.timeInSeconds != timeInSeconds) {
            this.setChanged();
        }

        this.timeInSeconds = timeInSeconds;
    }

    public int getTimeRemainingInSeconds() {
        return timeRemainingInSeconds;
    }

    public void setTimeRemainingInSeconds(int timeRemainingInSeconds) {

        if (this.timeRemainingInSeconds != timeRemainingInSeconds) {
            this.setChanged();
        }

        this.timeRemainingInSeconds = timeRemainingInSeconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {

        if (this.running != running) {
            this.setChanged();
        }

        this.running = running;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(boolean doorOpen) {

        if (this.doorOpen != doorOpen) {
            this.setChanged();
        }

        this.doorOpen = doorOpen;
    }

    public boolean isTubeOn() {
        return tubeOn;
    }

    public void setTubeOn(boolean tubeOn) {

        if (this.tubeOn != tubeOn) {
            this.setChanged();
        }

        this.tubeOn = tubeOn;
    }

    public boolean isLampOn() {
        return lampOn;
    }

    public void setLampOn(boolean lampOn) {

        if (this.lampOn != lampOn) {
            this.setChanged();
        }

        this.lampOn = lampOn;
    }

    public Color getLampColor() {
        return lampColor;
    }

    public void setLampColor(Color lampColor) {

        if (!this.lampColor.equals(lampColor)) {
            this.setChanged();
        }

        this.lampColor = lampColor;
    }

}
