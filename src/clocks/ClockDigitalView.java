package clocks;

import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockDigitalView.java
 */
public final class ClockDigitalView extends JPanel implements Observer,
        ActionListener {

    ClockModel model;
    int hour;
    int minute;
    int second;
    private JTextField hrField;
    private JTextField minField;
    private JTextField secField;
    private JLabel colonHrMinLabel;
    private JLabel colonMinSecLabel;

    ClockDigitalView(ClockModel model) {
        this.model = model;

        makeClock();
    }

    public void makeClock() {

        hrField = new JTextField(2);
        minField = new JTextField(2);
        secField = new JTextField(2);



        colonHrMinLabel = new JLabel(":");
        colonMinSecLabel = new JLabel(":");

        hrField.addActionListener(this);
        minField.addActionListener(this);
        secField.addActionListener(this);

        setLayout(new FlowLayout());

        hrField.setHorizontalAlignment(JTextField.CENTER);
        hrField.setEditable(true);
        add(hrField);

        add(colonHrMinLabel);

        minField.setHorizontalAlignment(JTextField.CENTER);
        minField.setEditable(true);
        add(minField);

        add(colonMinSecLabel);

        secField.setHorizontalAlignment(JTextField.CENTER);
        secField.setEditable(true);
        add(secField);
    }

    //If h < 10, add 0 before it
    private String formatHr(int h) {
        String hrString = "";
        if (h < 10) {
            hrString += "0";
        }

        hrString += h;

        return hrString;
    }

    //If m < 10, add 0 before it
    private String formatMin(int m) {
        String minString = "";
        if (m < 10) {
            minString += "0";
        }

        minString += m;

        return minString;
    }

    //If s < 10, add 0 before it
    private String formatSec(int s) {
        String secString = "";
        if (s < 10) {
            secString += "0";
        }

        secString += s;

        return secString;
    }

    //Get updated values from Observable model
    public void update(Observable o, Object arg) {

        ClockUpdate info = (ClockUpdate) arg;

        String hr = formatHr(info.hour);
        String mn = formatMin(info.minute);
        String sc = formatSec(info.second);


        hrField.setText(hr);
        minField.setText(mn);
        secField.setText(sc);


        repaint();      //Repaint the User Interface
    }

    //Get actions peformed by user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hrField) {
            int hr = Integer.parseInt(hrField.getText());
            model.setAdjustedHr(hr);
        } else if (e.getSource() == minField) {
            int mn = Integer.parseInt(minField.getText());
            model.setAdjustedMn(mn);
        } else if (e.getSource() == secField) {
            int sc = Integer.parseInt(secField.getText());
            model.setAdjustedSc(sc);
        }
    }
}//End ClockDigitalView class
