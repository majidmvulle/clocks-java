package clocks;

/*
 * NAME:        MAJID RAMADHAN MVULLE
 * ID  :        708-1339-03
 * SUBJECT:     U08186 (Object Component Technology)
 * Assignment:  1
 *
 * File:        ClockController.java
 */
public class ClockController {

    ClockModel model;
    

    //ClockController constructor
    public ClockController(ClockModel model)
    {
        this.model = model;
    }

    //Pause/Stop all clocks
    public void pause()
    {
        model.stop();
    }

    //Reset all clocks to 00:00:00
    public void reset()
    {
        model.reset(); 
    }

    //Resume/Restart all clocks
    public void resume()
    {
        model.start();
       
    }
    
}//End ClockController class
