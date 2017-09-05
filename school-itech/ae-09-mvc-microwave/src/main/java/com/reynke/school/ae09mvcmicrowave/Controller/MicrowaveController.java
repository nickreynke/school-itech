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

    public MicrowaveController() {

        this.microwave = new Microwave();

        this.microwaveView = new MicrowaveView(this, this.microwave);
        this.microwaveView.setTimer(new Timer(6000, this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

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
        JFormattedTextField timerFormattedTextField = this.microwaveView.getTimerFormattedTextField();
        this.info(timerFormattedTextField.getText());
    }

    public void stopTimer() {

        this.info("Timer stopping ...");

        this.microwaveView.getTimer().stop();

        this.info("Timer stopped. Bing!");

        this.microwaveView.getTubeButton().setBackground(Color.GREEN);
        this.microwaveView.getLampButton().setBackground(Color.WHITE);
    }

    public void startMicrowave() {

        this.info("Microwave tries to start ...");

        if (this.microwave.isDoorOpen()) {
            this.info("Microwaves' door is open! Can not start.");
            return;
        }

        this.info("Microwave started.");

        this.microwaveView.getLampButton().setBackground(Color.RED);
        this.microwaveView.getTubeButton().setBackground(Color.YELLOW);
        this.microwaveView.getTimer().start();
    }

    public void openDoor() {

        this.info("Door is opening ...");

        this.microwaveView.getDoorButton().setIcon(new ImageIcon("open.gif"));
        this.microwaveView.getLampButton().setBackground(Color.RED);
        this.microwaveView.getTubeButton().setBackground(Color.GREEN);
        this.microwaveView.getTimer().stop();
        this.microwave.setDoorOpen(true);

        this.info("Door opened.");
    }

    public void closeDoor() {

        this.info("Door is closing ...");

        this.microwaveView.getDoorButton().setIcon(new ImageIcon("closed.gif"));
        this.microwaveView.getLampButton().setBackground(Color.WHITE);
        this.microwave.setDoorOpen(false);

        this.info("Door closed.");
    }

    protected void info(String infoText) {
        this.microwaveView.getInfoTextPane().setText(infoText);
    }

}
