package clocks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockAnalogView.java
 */
public class ClockAnalogView extends JPanel implements Observer {

    ClockModel model;
    private int _diameter;                 // Height and width of clock face
    private int _centerX;                  // x coord of middle of clock
    private int _centerY;                  // y coord of middle of clock
    private BufferedImage _clockImage;     // Saved image of the clock face.
    private static final double TWO_PI = 2.0 * Math.PI;

    //ClockAnalogView constructor
    public ClockAnalogView(ClockModel model) {
        this.model = model;

        setPreferredSize(new Dimension(300, 300));

    }

    //Get updated values from Observable model
    public void update(Observable o, Object arg) {
        model = (ClockModel) o;

        repaint();      //Repaint the User Interface.
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Get current dimensions, if panel is resized
        int w = getWidth();
        int h = getHeight();
        _diameter = ((w < h) ? w : h);
        _centerX = _diameter / 2;
        _centerY = _diameter / 2;

        // Create the clock face background image
        if (_clockImage == null
                || _clockImage.getWidth() != w
                || _clockImage.getHeight() != h) {
            _clockImage = (BufferedImage) (this.createImage(w, h));

            // Get a graphics context from this image
            Graphics2D g2a = _clockImage.createGraphics();
            g2a.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            drawClockFace(g2a);
        }

        // Draw the clock face
        g2.drawImage(_clockImage, null, 0, 0);

        // Draw the clock hands dynamically every time
        drawClockHands(g2);
    }

    //Draw Clock Hands
    private void drawClockHands(Graphics2D g2) {
        // Get the various time elements from the Clock Model.

        int hours = model.getHr();

        int minutes = model.getMin();

        int seconds = model.getSec();

        int millis = model.getSec();

        // Second hand
        int handMin = _diameter / 18;
        int handMax = _diameter / 2;

        double fseconds = (seconds + (double) millis / 1000) / 60.0;
        drawRadius(g2, fseconds, 0, handMax);

        // Minute hand
        handMin = 0;                    // Minute hand starts in middle.
        handMax = _diameter / 3;
        double fminutes = (minutes + fseconds) / 60.0;
        drawRadius(g2, fminutes, 0, handMax);

        // Hour hand
        handMin = 0;
        handMax = _diameter / 5;
        drawRadius(g2, (hours + fminutes) / 12.0, 0, handMax);
    }

    // DrawClockFace
    private void drawClockFace(Graphics2D g2) {
        // Draw the clock face into a buffer.
        g2.setColor(Color.WHITE);
        g2.fillOval(0, 0, _diameter, _diameter);
        g2.setColor(Color.BLACK);
        g2.drawOval(0, 0, _diameter, _diameter);

        int radius = _diameter / 2;

        // Draw the tick marks around the clock face.
        for (int sec = 0; sec < 60; sec++) {
            int tickStart;
            if (sec % 5 == 0) {
                tickStart = radius - 20;  // Draw long tick mark every 5.
            } else {
                tickStart = radius - 5;   // Short tick mark.
            }
            drawRadius(g2, sec / 60.0, tickStart, radius);
        }

    }

    // Draw Radius
    private void drawRadius(Graphics2D g2, double percent,
            int minRadius, int maxRadius) {

        double radians = (0.5 - percent) * TWO_PI;
        double sine = Math.sin(radians);
        double cosine = Math.cos(radians);

        int dxmin = _centerX + (int) (minRadius * sine);
        int dymin = _centerY + (int) (minRadius * cosine);

        int dxmax = _centerX + (int) (maxRadius * sine);
        int dymax = _centerY + (int) (maxRadius * cosine);
        g2.drawLine(dxmin, dymin, dxmax, dymax);
    }
}//End ClockAnalogView class

