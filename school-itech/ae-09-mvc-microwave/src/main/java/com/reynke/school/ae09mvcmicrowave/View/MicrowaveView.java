package com.reynke.school.ae09mvcmicrowave.View;

import com.reynke.school.ae09mvcmicrowave.Controller.MicrowaveController;
import com.reynke.school.ae09mvcmicrowave.Model.Microwave;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class MicrowaveView extends JFrame {

    private JPanel mainPanel;
    private JPanel subPanel;

    private JFormattedTextField timerFormattedTextField;
    private JTextPane infoTextPane;
    private JButton startButton;
    private JButton doorButton;
    private JButton tubeButton;
    private JButton lampButton;

    private Timer timer;

    private MicrowaveController microwaveController;
    private Microwave microwave;

    public MicrowaveView(MicrowaveController microwaveController, Microwave microwave) {

        this.microwaveController = microwaveController;
        this.microwave = microwave;

        this.initComponents();
        this.pack();

        this.setTitle("Microwave");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents() {

        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        this.timerFormattedTextField = new JFormattedTextField(amountFormat);
        this.infoTextPane = new JTextPane();
        this.infoTextPane.setEnabled(false);

        this.startButton = new JButton("Start");
        this.startButton.setOpaque(true);
        this.startButton.setBorderPainted(false);

        this.tubeButton = new JButton();
        this.tubeButton.setBackground(Color.GREEN);
        this.tubeButton.setText("Tube");
        this.tubeButton.setOpaque(true);
        this.tubeButton.setBorderPainted(false);

        this.lampButton = new JButton();
        this.lampButton.setText("Lamp");
        this.lampButton.setOpaque(true);
        this.lampButton.setBorderPainted(false);

        this.doorButton = new JButton();
        this.doorButton.setIcon(new ImageIcon("closed.gif"));

        this.add(this.buildMicrowavePanel());
    }

    private Component buildMicrowavePanel() {

        this.subPanel = new JPanel(new GridLayout(5, 1));
        this.mainPanel = new JPanel(new GridLayout(2, 1));

        this.subPanel.add(this.timerFormattedTextField);
        this.subPanel.add(this.infoTextPane);
        this.subPanel.add(this.startButton);
        this.subPanel.add(this.tubeButton);
        this.subPanel.add(this.lampButton);

        this.mainPanel.add(this.subPanel);
        this.mainPanel.add(this.doorButton);

        this.timerFormattedTextField.addActionListener(this.microwaveController);
        this.startButton.addActionListener(this.microwaveController);
        this.doorButton.addActionListener(this.microwaveController);

        return mainPanel;
    }

    public JFormattedTextField getTimerFormattedTextField() {
        return timerFormattedTextField;
    }

    public JTextPane getInfoTextPane() {
        return infoTextPane;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getDoorButton() {
        return doorButton;
    }

    public JButton getTubeButton() {
        return tubeButton;
    }

    public JButton getLampButton() {
        return lampButton;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
