package mtcls.client.event;

import mtcls.common.event.TopicGroup;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface TopicGroupEventHandler extends EventHandler {
	
	/**
	 * populated by event dispatcher
	 * @return registration to unregister listener
	 */
	public HandlerRegistration getRegistration();
	
	/**
	 * delegate method invoked by event dispatcher
	 * @param registration
	 */
	public void setRegistration(HandlerRegistration registration);
	
	public TopicGroup getTopicGroup();
}
