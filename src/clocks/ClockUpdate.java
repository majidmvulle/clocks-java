package clocks;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockUpdate.java
 */
class ClockUpdate {

    public int hour;
    public int minute;
    public int second;
    public boolean isRunning;

    //ClockUpdate constructor
    //Updates the running state of the clocks
    public ClockUpdate(int h, int m, int s, boolean b) {
        hour = h;

        minute = m;
        second = s;
        isRunning = b;
    }
}//End ClockUpdate class

