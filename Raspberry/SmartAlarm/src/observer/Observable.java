package observer;

import events.Events;

public interface Observable {

	void notifyEvent(final Events ev);

	void addObserver(final Observer obs);

	void removeObserver(final Observer obs);

}