package com.reynke.school.ae09mvcmicrowaveobserverpattern.Controller;

import com.reynke.school.ae09mvcmicrowaveobserverpattern.Model.Microwave;
import com.reynke.school.ae09mvcmicrowaveobserverpattern.View.MicrowaveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class MicrowaveController implements ActionListener {

    private Microwave microwave;
    private MicrowaveView microwaveView;

    private Timer timer;

    public MicrowaveController() {
        this.microwave = new Microwave();
        this.microwaveView = new MicrowaveView(this);
        this.microwave.addObserver(this.microwaveView);
        this.timer = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        // What to perform when the door button was clicked.
        if (source.equals(this.microwaveView.getDoorButton())) {

            if (this.microwave.isDoorOpen()) {
                this.closeDoorAction();
                return;
            }

            this.openDoorAction();
            return;
        }

        // What to perform when the start button was clicked.
        if (source.equals(this.microwaveView.getStartButton())) {

            if (this.microwave.isRunning()) {
                this.stopAction();
                return;
            }

            this.startAction();
            return;
        }

        // What to perform when the timer formatted text field was changed.
        if (source.equals(this.microwaveView.getTimerFormattedTextField())) {
            this.changeTimerTimeAction();
        }
    }

    public void closeDoorAction() {

        if (!this.microwave.isDoorOpen()) {
            this.microwave.setLastInfoText("Couldn't close door of the microwave because it is already closed");
            this.microwave.notifyObservers(MicrowaveView.ERROR);
            return;
        }

        this.microwave.setDoorOpen(false);
        this.microwave.setLastInfoText("Door closed");
        this.microwave.notifyObservers(MicrowaveView.ACTION_DOOR_CLOSED);
    }

    public void openDoorAction() {

        if (this.microwave.isDoorOpen()) {
            this.microwave.setLastInfoText("Couldn't open door of the microwave because it is already opened");
            this.microwave.notifyObservers(MicrowaveView.ERROR);
            return;
        }

        if (this.microwave.isRunning()) {
            // @TODO Stop timer.
        }

        this.microwave.setDoorOpen(true);
        this.microwave.setLastInfoText("Door opened");
        this.microwave.notifyObservers(MicrowaveView.ACTION_DOOR_OPENED);
    }

    public void stopAction() {

        if (!this.microwave.isRunning()) {
            this.microwave.setLastInfoText("Microwave couldn't stop because it isn't running");
            this.microwave.notifyObservers(MicrowaveView.ERROR);
            return;
        }

        // @TODO Stop timer.

        this.microwave.setRunning(false);
        this.microwave.setTubeOn(false);
        this.microwave.setLastInfoText("Microwave stopped");
        this.microwave.notifyObservers(MicrowaveView.ACTION_MICROWAVE_STOPPED);
    }

    public void startAction() {

        if (this.microwave.isRunning()) {
            this.microwave.setLastInfoText("Microwave is already running");
            this.microwave.notifyObservers(MicrowaveView.ERROR);
            return;
        }

        if (this.microwave.isDoorOpen()) {
            this.microwave.setLastInfoText("Microwave can't start while the door is open");
            this.microwave.notifyObservers(MicrowaveView.ERROR);
            return;
        }

        // @TODO Start timer.

        this.microwave.setRunning(true);
        this.microwave.setTubeOn(true);
        this.microwave.setLastInfoText("Microwave started");
        this.microwave.notifyObservers(MicrowaveView.ACTION_MICROWAVE_STARTED);
    }

    public void changeTimerTimeAction() {
        // @TODO Implement.
        System.out.println("Change timer time");
    }

}
