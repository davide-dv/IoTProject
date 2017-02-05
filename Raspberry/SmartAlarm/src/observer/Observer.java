package observer;

import events.Events;

import java.io.IOException;

/**
 * 
 * @author Antonio Tagliente
 *
 */

public interface Observer {

	void update(final Observable obs, final Events ev);

}
