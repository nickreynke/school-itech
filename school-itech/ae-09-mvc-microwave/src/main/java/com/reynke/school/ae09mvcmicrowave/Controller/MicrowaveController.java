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

    private Microwave microwave;
    private MicrowaveView microwaveView;

    private Timer infoTimer;
    private int currentTimeLeftInSeconds;

    public MicrowaveController() {

        this.microwave = new Microwave();

        this.microwaveView = new MicrowaveView(this, this.microwave);
        this.microwaveView.setTimer(new Timer(6000, this));

        this.infoTimer = new Timer(1000, this);
        this.resetCurrentTimeLeft();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source.equals(this.infoTimer)) {
            this.lowerInfoTimer();
            return;
        }

        if (source.equals(this.microwaveView.getTimerFormattedTextField())) {
            this.setTimerDelay();
            return;
        }

        if (source.equals(this.microwaveView.getTimer())) {
            this.stopTimer();
            return;
        }

        if (source.equals(this.microwaveView.getStartButton())) {
            this.startMicrowave();
            return;
        }

        if (source.equals(this.microwaveView.getDoorButton())) {

            if (this.microwave.isDoorOpen()) {
                this.closeDoor();
                return;
            }

            this.openDoor();
        }

    }

    public void setTimerDelay() {

        if (this.microwaveView.getTimer().isRunning()) {
            this.info("Can not set new timer delay while the microwave is running.");
            return;
        }

        JFormattedTextField timerFormattedTextField = this.microwaveView.getTimerFormattedTextField();
        int timeInSeconds = Integer.parseInt(timerFormattedTextField.getText());

        if (timeInSeconds < 1) {
            this.info("Time delay can not be less than one second.");
            return;
        }

        this.microwaveView.getTimer().setDelay(timeInSeconds * 1000);
        this.microwaveView.getTimer().setInitialDelay(timeInSeconds * 1000);

        this.resetCurrentTimeLeft();

        this.info("Timer delay set to " + timeInSeconds + " " + (timeInSeconds == 1 ? "second" : "seconds") + ".");
    }

    public void stopTimer() {

        this.info("Timer stopping ...");

        this.microwaveView.getTimer().stop();
        this.infoTimer.stop();
        this.resetCurrentTimeLeft();

        this.info("Timer stopped. Bing!");

        this.microwaveView.getTubeButton().setBackground(Color.GREEN);
        this.microwaveView.getLampButton().setBackground(Color.WHITE);
    }

    public void startMicrowave() {

        this.info("Microwave starting ...");

        if (this.microwaveView.getTimer().isRunning()) {
            this.info("Microwave is already running.");
            return;
        }

        if (this.microwave.isDoorOpen()) {
            this.info("Microwaves' door is open! Can not start.");
            return;
        }

        this.microwaveView.getLampButton().setBackground(Color.RED);
        this.microwaveView.getTubeButton().setBackground(Color.YELLOW);

        this.startTimer();
    }

    public void startTimer() {

        if (this.microwaveView.getTimer().isRunning()) {
            this.info("Microwave is already running.");
            return;
        }

        if (this.microwave.isDoorOpen()) {
            this.info("Microwaves' door is open! Can not start.");
            return;
        }

        this.microwaveView.getTimer().start();
        this.infoTimer.start();
    }

    public void openDoor() {

        this.info("Door is opening ...");

        this.microwaveView.getLampButton().setBackground(Color.RED);
        this.microwaveView.getTubeButton().setBackground(Color.GREEN);
        this.microwaveView.getTimer().stop();
        this.infoTimer.stop();
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

    protected void lowerInfoTimer() {
        this.currentTimeLeftInSeconds -= 1;
        this.info(currentTimeLeftInSeconds + " " + (currentTimeLeftInSeconds == 1 ? "second" : "seconds") + " remaining ...");
    }

    protected void resetCurrentTimeLeft() {
        this.currentTimeLeftInSeconds = this.microwaveView.getTimer().getInitialDelay() / 1000;
    }

    protected void info(String infoText) {
        this.microwaveView.getInfoTextPane().setText(infoText);
    }

}
