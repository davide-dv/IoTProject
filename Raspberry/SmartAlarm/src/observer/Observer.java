package observer;

import events.Events;

public interface Observer {

	void update(final Observable obs, final Events ev);

}
