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
    private int timerSecondsLeft;

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
        // @TODO Implement.
        System.out.println("Close door");
    }

    public void openDoorAction() {
        // @TODO Implement.
        System.out.println("Open door");
    }

    public void stopAction() {
        // @TODO Implement.
        System.out.println("Stop microwave");
    }

    public void startAction() {
        // @TODO Implement.
        System.out.println("Start microwave");
    }

    public void changeTimerTimeAction() {
        // @TODO Implement.
        System.out.println("Change timer time");
    }

}
