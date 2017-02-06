package controller;

import communication.SerialCommChannel;
import devices.Button;
import devices.ButtonObserver;
import devices.Light;
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

    private final ObservableButton ob = new ObservableButton(new Button(12));
    private final ButtonObserver btnObs;
    private final Light l1 = new Led(3);
    private final Light l2 = new Led(5);
    private final Light l3 = new Led(4);
    private final SerialCommChannel channel;


    public ControlPanelImpl(final SerialCommChannel channel){
        try {
            this.l1.switchOn();
            this.l2.switchOff();
            this.l3.switchOff();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.channel = channel;
        this.btnObs = new ButtonObserver(channel);
        this.ob.addObserver(this.btnObs);
        this.ob.addObserver(this);
    }


    @Override
    public synchronized void setAlarm() {
        try {
            this.l3.switchOn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void setComunication(boolean state) {
        try {
            if (state) {
                this.l2.switchOn();
            } else {
                this.l2.switchOff();
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
                        this.l3.switchOff();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
