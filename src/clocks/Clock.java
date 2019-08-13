package clocks;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        Clock.java
 */
public class Clock extends JApplet {

    ClockModel model;
    ClockController controller;
    ClockDigitalView digitalView;
    ClockSliderView sliderView;
    ClockAnalogView analogView;
    ClockButtons buttons;

    //Clock constructor
    public Clock() {
        model = new ClockModel();
        controller = new ClockController(model);

        digitalView = new ClockDigitalView(model);
        sliderView = new ClockSliderView(model);
        analogView = new ClockAnalogView(model);
        buttons = new ClockButtons(model, controller);

        model.addObserver(sliderView);      //Add sliderView as an Observer
        model.addObserver(digitalView);     //Add digitalView as an Observer
        model.addObserver(analogView);      //Add analogView as an Observer
        model.addObserver(buttons);         //Add buttons as an Observer

        model.start();                      //Call ClockModel's start() function

        JPanel ClockPanel = new JPanel();
        ClockPanel.setLayout(new BoxLayout(ClockPanel, BoxLayout.Y_AXIS));

        ClockPanel.add(sliderView);
        ClockPanel.add(analogView);
        ClockPanel.add(digitalView);
        ClockPanel.add(buttons);

        getContentPane().add(ClockPanel);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("U08186 Assignment : MVC Clocks");
        window.setContentPane(ClockPanel);
        window.pack();                          // Layout components
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Clock clock = new Clock();

    }
}//End Clock class.

