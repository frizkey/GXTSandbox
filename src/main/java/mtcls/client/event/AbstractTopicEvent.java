package mtcls.client.event;

import mtcls.client.event.helper.DispatchHelper;
import mtcls.common.event.EventTopic;

import com.google.gwt.event.shared.GwtEvent;

public abstract class AbstractTopicEvent<H extends TopicGroupEventHandler> extends GwtEvent<H> {
//	public static final Type<TopicGroupEventHandler> TYPE = new Type<TopicGroupEventHandler>();
	protected Type<H> associatedType;
	protected EventTopic eventTopic;
	protected Throwable error;
	
	public AbstractTopicEvent(Type<H> associatedType, EventTopic eventTopic) {
		this(associatedType, eventTopic, null);
	}
	
	public AbstractTopicEvent(Type<H> associatedType, EventTopic eventTopic, Throwable error){
		setAssociatedType(associatedType);
		setEventTopic(eventTopic);
		setError(error);
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public Type<H> getAssociatedType() {
//		return (com.google.gwt.event.shared.GwtEvent.Type<H>) TYPE;
//	}
	
	@Override
	protected void dispatch(H handler) {
		if(handler == null){
			return;
		}
		boolean isDispatchable = DispatchHelper.isDispatchable(eventTopic, handler.getTopicGroup());
		if(!isDispatchable){
			return;
		}
		doDispatch(handler);
	}
	
	
	/**
	 * invoked if the topic is one of the topics in the handler's topicGroup
	 * @param handler
	 */
	protected abstract void doDispatch(H handler);

	
	/**
	 * @return the eventType
	 */
	public EventTopic getEventTopic() {
		return eventTopic;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventTopic(EventTopic eventTopic) {
		this.eventTopic = eventTopic;
	}

	/**
	 * @return the error
	 */
	public Throwable getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Throwable error) {
		this.error = error;
	}

	/**
	 * @return the associatedType
	 */
	@Override
	public Type<H> getAssociatedType() {
		return associatedType;
	}

	/**
	 * @param associatedType the associatedType to set
	 */
	public void setAssociatedType(Type<H> associatedType) {
		this.associatedType = associatedType;
	}
}
