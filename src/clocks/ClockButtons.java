/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clocks;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockButtons.java
 */
public final class ClockButtons extends JPanel implements Observer,
        ActionListener {

    ClockModel model;
    ClockController controller;
    private JButton pause;    //Pause button
    private JButton resume;   //Reset button
    private JButton reset;    //Reset Clocks button

    //ClockButtons constructor
    public ClockButtons(ClockModel model, ClockController controller) {
        this.model = model;
        this.controller = controller;

        makeButtons();

    }

    //Get updated values from Observable model
    public void update(Observable o, Object arg) {
        ClockUpdate info = (ClockUpdate) arg;
        if (info.isRunning) {
            pause.setEnabled(true);
            resume.setEnabled(false);
            reset.setEnabled(false);
        } else if (!info.isRunning) {
            pause.setEnabled(false);
            resume.setEnabled(true);
            reset.setEnabled(true);
        }

        repaint();              //Repaint the User interface.
    }

    //Make Buttons on the panel
    public void makeButtons() {
        pause = new JButton("Pause");
        resume = new JButton("Resume");
        reset = new JButton("Reset Clocks");

        pause.addActionListener(this);
        reset.addActionListener(this);
        resume.addActionListener(this);

        setLayout(new FlowLayout());
        add(pause);
        add(resume);
        add(reset);
    }

    //Get actions peformed by user
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pause) {
            controller.pause();
        } else if (e.getSource() == reset) {
            controller.reset();
        } else if (e.getSource() == resume) {
            controller.resume();
        }

    }
}//End ClockButtons class

