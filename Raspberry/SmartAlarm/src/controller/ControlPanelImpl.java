package controller;

import devices.Button;
import devices.ObservableButton;
import devices.p4j_impl.Led;
import events.Events;
import observer.Observable;
import observer.Observer;

import java.io.IOException;

/**
 * Created by davide on 27/01/17.
 */
public class ControlPanelImpl implements ControlPanel, Observer {

    private final ObservableButton ob = new ObservableButton(new Button(19));
    private final Led l1 = new Led(3);
    private final Led l2 = new Led(5);
    private final Led l3 = new Led(4);


    public ControlPanelImpl(){
        ob.addObserver(this);
    }


    @Override
    public synchronized void setAlarm() {
        try {
            l1.switchOn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void setComunication(boolean state) {
        try {
            if (state) {
                l2.switchOn();
            } else {
                l2.switchOff();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable obs, Events ev) {
        if (obs instanceof ObservableButton) {
            switch (ev.getEvents()) {
                case IS_PRESSED:
                    try {
                        l3.switchOff();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
