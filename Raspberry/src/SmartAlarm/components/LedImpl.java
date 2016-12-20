package SmartAlarm.components;

/**
 * Created by Antonio Tagliente on 20.12.16.
 */
public class LedImpl implements Led{

    private boolean status;
    private int pin;

    LedImpl(final int pin) {
        this.pin = pin;
        this.status = false;
    }

    @Override
    public void setON() {
        this.status = true;
    }

    @Override
    public void setOFF() {
        this.status = false;
    }

    @Override
    public boolean isON() {
        return this.status;
    }

}
