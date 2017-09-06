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
        this.setMinimumSize(new Dimension(250, 300));
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
        this.doorButton.setText("Open Door");
        this.doorButton.setOpaque(true);
        this.doorButton.setBorderPainted(false);

        this.add(this.buildMicrowavePanel());
    }

    private Component buildMicrowavePanel() {

        this.subPanel = new JPanel(new GridLayout(6, 1));
        this.mainPanel = new JPanel(new GridLayout(1, 1));

        this.subPanel.add(this.timerFormattedTextField);
        this.subPanel.add(this.infoTextPane);
        this.subPanel.add(this.startButton);
        this.subPanel.add(this.tubeButton);
        this.subPanel.add(this.lampButton);
        this.subPanel.add(this.doorButton);

        this.mainPanel.add(this.subPanel);

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

}
