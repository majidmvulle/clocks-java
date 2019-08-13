package clocks;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockSliderView.java
 */
public final class ClockSliderView extends JPanel implements Observer,
        ChangeListener {

    //JML Statement
    private /*@ spec_public @*/ ClockModel model;

    //@ public invariant this.model = model;
  /*@ requires model;
    @ assignable model
    @ ensures this.model == model;
    @*/
    public ClockSliderView(ClockModel model) {
        this.model = model;

        makeClock();
    }

    private JSlider slider1;  //Hours Slider
    private JSlider slider2;  //Minutes Slider
    private JSlider slider3;  //Seconds Slider

    //Makes the TextFields on th panel
    public void makeClock() {
        slider1 = new JSlider();

        slider1.setMajorTickSpacing(2);
        slider1.setMinorTickSpacing(1);
        slider1.setMaximum(23);
        slider1.setMinimum(0);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setSize(300, 50);
        slider1.setVisible(true);

        slider2 = new JSlider();

        slider2.setMajorTickSpacing(5);
        slider2.setMinorTickSpacing(1);
        slider2.setMaximum(59);
        slider2.setMinimum(0);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        slider2.setSize(300, 50);
        slider2.setVisible(true);

        slider3 = new JSlider();

        slider3.setMajorTickSpacing(5);
        slider3.setMinorTickSpacing(1);
        slider3.setMaximum(59);
        slider3.setMinimum(0);
        slider3.setPaintTicks(true);
        slider3.setPaintLabels(true);
        slider3.setSize(300, 50);
        slider3.setVisible(true);

        setLayout(new BorderLayout());

        slider1.addChangeListener(this);
        slider2.addChangeListener(this);
        slider3.addChangeListener(this);

        add(slider1, BorderLayout.NORTH);
        add(slider2, BorderLayout.CENTER);
        add(slider3, BorderLayout.SOUTH);
    }

    //Get updated values from Observable model
    public void update(Observable o, Object arg) {

        ClockUpdate info = (ClockUpdate) arg;

        int h = info.hour;
        int m = info.minute;
        int s = info.second;

        slider1.setValue(h);
        slider2.setValue(m);
        slider3.setValue(s);

        repaint();          //Repaint the user interface
    }

    //Get actions peformed by user
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider1) {
            int hr = slider1.getValue();
            model.setAdjustedHr(hr);
        } else if (e.getSource() == slider2) {
            int mn = slider2.getValue();
            model.setAdjustedMn(mn);
        } else if (e.getSource() == slider3) {
            int sc = slider3.getValue();
            model.setAdjustedSc(sc);
        }
    }
}//End ClockSliderView class
