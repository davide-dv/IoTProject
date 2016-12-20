package SmartAlarm.components;

/**
 * Created by Antonio Tagliente on 20.12.16.
 */
public interface Led {
    //Set LED on
    public void setON();
    //Set Led off
    public void setOFF();
    //Return led status
    public boolean isON();

}
