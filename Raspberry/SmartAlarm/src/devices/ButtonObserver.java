package devices;

import communication.SerialCommChannel;
import events.Events;
import observer.Observable;
import observer.Observer;

public class ButtonObserver implements Observer {

	private SerialCommChannel comm;
	
	public ButtonObserver(final SerialCommChannel comm) {
		if(comm != null) {
			this.comm = comm;
		} else throw new NullPointerException("In ButtonObserver the SerialCommunication can't be null");
	}
	
	@Override
	public void update(final Observable obs,final Events ev) {
		if (obs instanceof ObservableButton) {
			switch (ev.getEvents()) {
			case IS_PRESSED:
				this.comm.sendMsg("A_N");
				break;
			case IS_RELEASED:
				
				break;
			}
		}
	}

}
