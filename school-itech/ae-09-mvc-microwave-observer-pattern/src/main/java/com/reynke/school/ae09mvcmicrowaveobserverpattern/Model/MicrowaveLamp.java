package com.reynke.school.ae09mvcmicrowaveobserverpattern.Model;

import java.awt.*;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class MicrowaveLamp {

    private boolean on;
    private Color color;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
