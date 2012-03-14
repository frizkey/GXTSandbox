package mtcls.client.event;

import mtcls.common.event.TopicGroup;
import mtcls.common.event.TopicGroups;

import com.google.gwt.event.shared.HandlerRegistration;

public class AbstractTopicGroupEventHandler implements TopicGroupEventHandler {
	protected TopicGroup topicGroup;
	protected HandlerRegistration registration;
	
	protected TopicGroup createTopicGroup(){
		TopicGroup retval = TopicGroups.AllTopics;
		return retval;
	}
	/**
	 * @return the topicGroup
	 */
	public TopicGroup getTopicGroup() {
		if(topicGroup == null){
			topicGroup = createTopicGroup();
		}
		return topicGroup;
	}
	/**
	 * @param topicGroup the topicGroup to set
	 */
	public void setTopicGroup(TopicGroup topicGroup) {
		this.topicGroup = topicGroup;
	}
	/**
	 * @return the registration
	 */
	public HandlerRegistration getRegistration() {
		return registration;
	}
	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(HandlerRegistration registration) {
		this.registration = registration;
	}


}
