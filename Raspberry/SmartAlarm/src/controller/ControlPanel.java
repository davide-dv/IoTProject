package controller;

/**
 * Created by davide on 27/01/17.
 */
public class ControlPanel implements IControlPanel {

    private final GPIOPin ledAlarm = DeviceManager.open(1);
    private final GPIOPin ledSystemOn = DeviceManager.open(1);
    private final GPIOPin ledCommunication = DeviceManager.open(1);
    private final GPIOPin button = DeviceManager.open(1);


    public ControlPanel() {
        this.button.setInputListener(
                ledAlarm.setVelue(false);
        );

        this.ledSystemOn.setValue(true);
    }


    @Override
    public void setAlarm() {
        this.ledAlarm.setValue(true);
    }

    @Override
    public void setComunication(boolean state) {
        this.ledCommunication.setValue(state);
    }
}
