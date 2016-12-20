package SmartAlarm.components;

/**
 * Created by Antonio Tagliente on 20.12.16.
 */
public class BlinkingLedImpl extends LedImpl implements BlinkingLed{

    private boolean blinkState;
    private long period;
    private Led led = null;
    private Blink blinkingthread;

    public BlinkingLedImpl(int pin) {
        super(pin);
        this.blinkState = false;
        this.period = 0;
        this.blinkingthread = new Blink(this);
    }

    @Override
    public void blink(int period) {
        this.blinkState = true;
        this.blinkingthread.setPeriod(period);
        this.blinkingthread.start();
    }

    @Override
    public boolean getBlinkingState() {
        return blinkState;
    }


    private class Blink extends Thread {

        private boolean blinkState;
        private long period;
        private BlinkingLed led;

        public Blink(final BlinkingLed led) {
            this.led = led;
            this.period = 0;
        }

        public void setPeriod(final long period) {
            this.period = period;
        }

        public void run() {
            while (this.led.getBlinkingState()) {
                if (this.led.isON()) {
                    this.led.setOFF();
                } else {
                    this.led.setON();
                }
                try {
                    Thread.sleep(this.period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
