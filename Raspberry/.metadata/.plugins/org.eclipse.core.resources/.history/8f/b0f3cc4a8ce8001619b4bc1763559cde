package controller;

import common.Event;
import common.Observer;
import devices.p4j_impl.Button;
import devices.p4j_impl.Config;
import devices.p4j_impl.GPIOBox;
import devices.p4j_impl.Led;

import java.io.IOException;

/**
 * Created by davide on 27/01/17.
 */
public class ControlPanelImpl implements ControlPanel {

    private final Button t1 = new Button(19);
    private final Led l1 = new Led(3);
    private final Led l2 = new Led(5);
    private final Led l3 = new Led(4);


    public ControlPanelImpl(){

    }


    @Override
    public synchronized void setAlarm() {

    }

    @Override
    public synchronized void setComunication(boolean state) {
        try {
            l2.switchOn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
