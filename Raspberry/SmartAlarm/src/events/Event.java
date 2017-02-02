package events;

public class Event implements Events {

	private PossibleEvents eventType;
	
	public Event(final PossibleEvents eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public PossibleEvents getEvents() {
		return this.eventType;
	}

}
