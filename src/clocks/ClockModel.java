package clocks;

import java.awt.event.*;
import java.util.*;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockModel.java
 */
public final class ClockModel extends Observable {

    ClockUpdate _clockUpd;
    
    Calendar now = Calendar.getInstance();

    private int hr = now.get(Calendar.HOUR_OF_DAY); //Set to System Hour
    private int min = now.get(Calendar.MINUTE);     //Set to System Minutes
    private int sec = now.get(Calendar.SECOND);     //Set to System Seconds

    private javax.swing.Timer tickInterval;         //Timer

    private boolean isRunning;                      //Bool to check clocks
                                                    //running state

    //ClockModel Constructor
    public ClockModel() {

        setHr(hr);
        setMin(min);
        setSec(sec);

    }

    //Start all clocks, read from the Model
    public void start() {
        if (!isRunning) {
            isRunning = true;

            tickInterval = new javax.swing.Timer(1000, new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    tick();

                }
            });

            tickInterval.start();
            _clockUpd = new ClockUpdate(hr, min, sec, isRunning);

            setChanged();                //Set Model as Changed
            notifyObservers(_clockUpd);  //Notify all Views observing this Model
        }
    }

    //Stop all clocks
    public void stop() {
        if (isRunning) {
            isRunning = false;

            tickInterval.stop();


            _clockUpd = new ClockUpdate(hr, min, sec, isRunning);

            setChanged();
            notifyObservers(_clockUpd);
        }
    }

    public int getHr() {
        return hr;
    }

    //JML Statement
    //@ requires h >= 0 && h < 24;
    /*@ ensures setChanged(), notifyObservers,
    @         update (hr);
    @*/
    public void setHr(int h) {

        hr = ((h >= 0 && h < 24) ? h : 0);

        setChanged();

        if (hasChanged()) {
            ClockUpdate _clockUpdate = new ClockUpdate(hr, min, sec, isRunning);

            notifyObservers(_clockUpdate);
        }

    }

    public int getMin() {
        return min;
    }

    //JML Statement
    //@ requires m >= 0 && m < 60;
    /*@ ensures setChanged(), notifyObservers,
    @         update (min);
    @*/
    public void setMin(int m) {

        min = ((m >= 0 && m < 60) ? m : 0);

        setChanged();

        if (hasChanged()) {
            ClockUpdate _clockUpdate = new ClockUpdate(hr, min, sec, isRunning);

            notifyObservers(_clockUpdate);

        }

    }

    //JML Statement
    //@ requires s >= 0 && s < 60;
    /*@ ensures setChanged(), notifyObservers,
    @         update (sec);
    @*/
    public void setSec(int s) {

        sec = ((s >= 0 && s < 60) ? s : 0);

        setChanged();

        if (hasChanged()) {
            ClockUpdate _clockUpdate = new ClockUpdate(hr, min, sec, isRunning);

            //Push the changed data to the Views.
            notifyObservers(_clockUpdate);
        }

    }

    public int getSec() {
        return sec;
    }


//Moves the Clocks
    public void tick() {
        int h = hr;
        int m = min;
        int s = sec;

        s += 1;

        if (s > 59) {
            s = 0;
            m += 1;


            if (m > 59) {
                m = 0;
                h += 1;

                if (h > 23) {
                    h = 0;
                }
            }
        }

        //Set the new time.
        setSec(s);
        setMin(m);
        setHr(h);
    }

    //Reset all Clocks to 00:00:00
    public void reset() {
        int h = 0;
        int m = 0;
        int s = 0;

        setHr(h);
        setMin(m);
        setSec(s);

    }

    //Set new hour entered by user
    public void setAdjustedHr(int h) {

        hr = h;

        setHr(h);
    }

    //Set new minutes entered by user
    public void setAdjustedMn(int m) {
        min = m;

        setMin(m);
    }

    //Set new seconds entered by user
    public void setAdjustedSc(int s) {
        sec = s;

        setSec(s);

    }
}//End Class ClockModel
