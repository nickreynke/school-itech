package com.reynke.school.ae09mvcmicrowaveobserverpattern.View;

import com.reynke.school.ae09mvcmicrowaveobserverpattern.Controller.MicrowaveController;
import com.reynke.school.ae09mvcmicrowaveobserverpattern.Model.Microwave;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class MicrowaveView extends JFrame implements Observer {

    public static final int ACTION_DOOR_OPENED = 0;
    public static final int ACTION_DOOR_CLOSED = 1;
    public static final int ACTION_MICROWAVE_STARTED = 2;
    public static final int ACTION_MICROWAVE_STOPPED = 3;
    public static final int ERROR = 4;

    private MicrowaveController microwaveController;

    private JPanel mainPanel;

    private JFormattedTextField timerFormattedTextField;
    private JTextPane infoTextPane;
    private JButton startButton;
    private JButton doorButton;
    private JButton tubeButton;
    private JButton lampButton;

    public MicrowaveView(MicrowaveController microwaveController) {

        this.microwaveController = microwaveController;

        this.buildView();
        this.pack();

        this.setTitle("Microwave");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(250, 300));
    }

    /**
     * Build the components for the view and at them to the `mainPanel`.
     */
    protected void buildView() {
        this.buildComponents();
        this.buildMainPanel();
        this.addActionListeners();
    }

    protected void buildComponents() {
        this.timerFormattedTextField = this.buildTimerFormattedTextField();
        this.infoTextPane = this.buildInfoTextPane();
        this.startButton = this.buildStartButton();
        this.tubeButton = this.buildTubeButton();
        this.lampButton = this.buildLampButton();
        this.doorButton = this.buildDoorButton();
    }

    protected void buildMainPanel() {

        this.mainPanel = new JPanel(new GridLayout(6, 1));

        this.addToMainPanel(this.timerFormattedTextField);
        this.addToMainPanel(this.infoTextPane);
        this.addToMainPanel(this.startButton);
        this.addToMainPanel(this.tubeButton);
        this.addToMainPanel(this.lampButton);
        this.addToMainPanel(this.doorButton);

        this.add(mainPanel);
    }

    protected void addActionListeners() {
        this.timerFormattedTextField.addActionListener(this.microwaveController);
        this.startButton.addActionListener(this.microwaveController);
        this.doorButton.addActionListener(this.microwaveController);
    }

    protected JTextPane buildInfoTextPane() {

        JTextPane infoTextPane = new JTextPane();
        infoTextPane.setEnabled(false);

        return infoTextPane;
    }

    protected JFormattedTextField buildTimerFormattedTextField() {
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        return new JFormattedTextField(amountFormat);
    }

    protected JButton buildStartButton() {

        JButton startButton = new JButton("Start");
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);

        return startButton;
    }

    protected JButton buildTubeButton() {

        JButton tubeButton = new JButton();
        tubeButton.setBackground(Color.GREEN);
        tubeButton.setText("Tube");
        tubeButton.setOpaque(true);
        tubeButton.setBorderPainted(false);

        return tubeButton;
    }

    protected JButton buildLampButton() {

        JButton lampButton = new JButton();
        lampButton.setBackground(null);
        lampButton.setText("Lamp");
        lampButton.setOpaque(true);
        lampButton.setBorderPainted(false);

        return lampButton;
    }

    protected JButton buildDoorButton() {

        JButton doorButton = new JButton();
        doorButton.setText("Open Door");
        doorButton.setOpaque(true);
        doorButton.setBorderPainted(false);

        return doorButton;
    }

    protected void addToMainPanel(JComponent component) {
        this.mainPanel.add(component);
    }

    public JFormattedTextField getTimerFormattedTextField() {
        return timerFormattedTextField;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getDoorButton() {
        return doorButton;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!(o instanceof Microwave)) {
            throw new RuntimeException(String.format(
                    "Observable is not an instance of `%s`.",
                    Microwave.class
            ));
        }

        if (!(arg instanceof Integer)) {
            throw new RuntimeException(String.format(
                    "Second argument, passed to `update` method of observer `%s`, is not an instance of `%s`.",
                    this.getClass(),
                    String.class
            ));
        }

        Microwave microwave = (Microwave) o;
        Integer argument = (Integer) arg;

        switch (argument) {

            case ERROR:

                this.infoTextPane.setText(microwave.getLastInfoText());
                return;

            case ACTION_DOOR_OPENED:

                this.tubeButton.setBackground(Color.GREEN);
                this.lampButton.setBackground(Color.YELLOW);
                this.doorButton.setText("Close door");
                this.infoTextPane.setText(microwave.getLastInfoText());

                break;

            case ACTION_DOOR_CLOSED:

                this.tubeButton.setBackground(Color.GREEN);
                this.lampButton.setBackground(null);
                this.doorButton.setText("Open door");
                this.infoTextPane.setText(microwave.getLastInfoText());

                break;

            case ACTION_MICROWAVE_STOPPED:

                this.tubeButton.setBackground(Color.GREEN);
                this.lampButton.setBackground(null);
                this.startButton.setText("Start");
                this.infoTextPane.setText(microwave.getLastInfoText());

                break;

            case ACTION_MICROWAVE_STARTED:

                this.tubeButton.setBackground(Color.RED);
                this.lampButton.setBackground(Color.YELLOW);
                this.startButton.setText("Stop");
                this.infoTextPane.setText(microwave.getLastInfoText());

                break;
        }
    }

}
