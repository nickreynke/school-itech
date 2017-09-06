package com.reynke.school.ae09mvcmicrowave.Controller;

import com.reynke.school.ae09mvcmicrowave.Model.Microwave;
import com.reynke.school.ae09mvcmicrowave.View.MicrowaveView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class MicrowaveController implements ActionListener {

    private Timer microwaveTimer;

    private Timer microwaveInfoTimer;
    private int currentTimeLeftInSeconds;

    private Microwave microwave;
    private MicrowaveView microwaveView;

    public MicrowaveController() {
        this.microwaveTimer = new Timer(1000, this);
        this.microwaveInfoTimer = new Timer(1000, this);
        this.microwave = new Microwave();
        this.microwaveView = new MicrowaveView(this, this.microwave);
        this.resetCurrentTimeLeft();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        // Action to perform when `enter` is pressed while in text field.
        if (source.equals(this.microwaveView.getTimerFormattedTextField())) {
            this.setTimerDelay();
            return;
        }

        // Action to perform after every step of the microwave info timer.
        if (source.equals(this.microwaveInfoTimer)) {
            this.lowerCurrentTimeLeft();
            return;
        }

        // Action to perform after the microwave timer rans out.
        if (source.equals(this.microwaveTimer)) {
            this.stopTimer();
            return;
        }

        // Action to perform when the start button is pressed.
        if (source.equals(this.microwaveView.getStartButton())) {
            this.startMicrowave();
            return;
        }

        // Action to perform when the door button is pressed.
        if (source.equals(this.microwaveView.getDoorButton())) {

            if (this.microwave.isDoorOpen()) {
                this.closeDoor();
                return;
            }

            this.openDoor();
        }

    }

    /**
     * Sets a new microwave timer and resets the microwave info timer.
     */
    public void setTimerDelay() {

        if (this.microwaveTimer.isRunning()) {
            this.info("Can not set new microwaveTimer delay while the microwave is running.");
            return;
        }

        JFormattedTextField timerFormattedTextField = this.microwaveView.getTimerFormattedTextField();
        int timeInSeconds = Integer.parseInt(timerFormattedTextField.getText());

        if (timeInSeconds < 1) {
            this.info("Time delay can not be less than one second.");
            return;
        }

        this.microwaveTimer.setDelay(timeInSeconds * 1000);
        this.microwaveTimer.setInitialDelay(timeInSeconds * 1000);

        this.resetCurrentTimeLeft();

        this.info("Timer delay set to " + timeInSeconds + " " + (timeInSeconds == 1 ? "second" : "seconds") + ".");
    }

    protected void stopTimer() {

        this.info("Timer stopping ...");

        this.stopMicrowaveTimer();
        this.stopMicrowaveInfoTimer();
        this.resetCurrentTimeLeft();

        this.info("Timer stopped. Bing!");

        this.microwaveView.getTubeButton().setBackground(Color.GREEN);
        this.microwaveView.getLampButton().setBackground(Color.WHITE);
    }

    public void startMicrowave() {

        this.info("Microwave starting ...");

        if (this.microwaveTimer.isRunning()) {
            this.info("Microwave is already running.");
            return;
        }

        if (this.microwave.isDoorOpen()) {
            this.info("Microwaves' door is open! Can not start.");
            return;
        }

        this.microwaveView.getLampButton().setBackground(Color.RED);
        this.microwaveView.getTubeButton().setBackground(Color.YELLOW);

        this.startMicrowaveInfoTimer();
        this.startMicrowaveTimer();
    }

    public void openDoor() {

        this.info("Door is opening ...");

        this.microwaveView.getLampButton().setBackground(Color.RED);
        this.microwaveView.getTubeButton().setBackground(Color.GREEN);

        this.stopMicrowaveTimer();
        this.stopMicrowaveInfoTimer();
        this.resetCurrentTimeLeft();

        this.microwave.setDoorOpen(true);
        this.microwaveView.getDoorButton().setText("Close Door");

        this.info("Door opened.");
    }

    public void closeDoor() {

        this.info("Door is closing ...");

        this.microwaveView.getLampButton().setBackground(Color.WHITE);
        this.microwave.setDoorOpen(false);
        this.microwaveView.getDoorButton().setText("Open Door");

        this.info("Door closed.");
    }

    protected void startMicrowaveTimer() {
        this.microwaveTimer.start();
    }

    protected void stopMicrowaveTimer() {
        this.microwaveTimer.stop();
    }

    protected void startMicrowaveInfoTimer() {
        this.microwaveInfoTimer.start();
    }

    protected void stopMicrowaveInfoTimer() {
        this.microwaveInfoTimer.stop();
        this.resetCurrentTimeLeft();
    }

    protected void lowerCurrentTimeLeft() {
        this.currentTimeLeftInSeconds -= 1;
        this.info(currentTimeLeftInSeconds + " " + (currentTimeLeftInSeconds == 1 ? "second" : "seconds") + " remaining ...");
    }

    protected void resetCurrentTimeLeft() {
        this.currentTimeLeftInSeconds = this.microwaveTimer.getInitialDelay() / 1000;
    }

    protected void info(String infoText) {
        this.microwaveView.getInfoTextPane().setText(infoText);
    }

}
