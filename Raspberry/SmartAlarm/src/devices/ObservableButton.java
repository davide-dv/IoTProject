package devices;

import java.util.Deque;
import java.util.LinkedList;
import events.Event;
import events.Events;
import events.PossibleEvents;
import observer.Observable;
import observer.Observer;

/**
 * 
 * @author Antonio Tagliente
 *
 */

public class ObservableButton implements Observable{

	private volatile Deque<Observer> observers = new LinkedList<>();
	private volatile Buttons button = null;
	
	public ObservableButton(final Buttons button) {
		if (button != null) {
			this.button = button;
			Thread control = new Thread(new PinControl(this));
			control.start();
		} else throw new NullPointerException("Constructor's parameter can't be null");
	}

	@Override
	public synchronized void notifyEvent(final Events ev) {
		this.observers.forEach(x->{
			if(ev != null) { 
				x.update(this, ev);
			}
		});
	}

	@Override
	public synchronized void addObserver(final Observer obs) {
		if (obs == null) throw new NullPointerException();
		else this.observers.addLast(obs);
	}

	@Override
	public synchronized void removeObserver(final Observer obs) {
		if (obs == null) throw new NullPointerException();
		else this.observers.remove(obs);
	}
	
	public synchronized boolean isPressed() {
		return this.button.isPressed();
	}

	private class PinControl implements Runnable{

		private ObservableButton button = null;
		private boolean prevState = false;
		
		public PinControl(final ObservableButton button) {
			this.button = button;
		}
		
		@Override
		public void run() {
			boolean state;
			while(!Thread.currentThread().isInterrupted()) {
				state = this.button.isPressed();
				if (state != this.prevState) {
					if(state) {
						this.button.notifyEvent(new Event(PossibleEvents.IS_PRESSED));
					} else new Event(PossibleEvents.IS_RELEASED);
				}
			}
		}
			
	}
}
