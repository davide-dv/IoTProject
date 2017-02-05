package observer;

import events.Events;

/**
 * 
 * @author Antonio Tagliente
 *
 */

public interface Observable {

	void notifyEvent(final Events ev);

	void addObserver(final Observer obs);

	void removeObserver(final Observer obs);

}