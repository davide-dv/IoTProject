package SmartAlarm.components;

/**
 * Created by Antonio Tagliente on 20.12.16.
 */
public interface BlinkingLed extends Led{

    //Start to blink the led with specific period, duty-cycle = 50%
    public void blink(int period);

    //Return true if the led is in blink state
    public boolean getBlinkingState();

}
