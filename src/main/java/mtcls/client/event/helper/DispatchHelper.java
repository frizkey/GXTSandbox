package mtcls.client.event.helper;

import java.util.List;

import mtcls.common.event.EventTopic;
import mtcls.common.event.TopicGroup;
import mtcls.common.event.TopicGroups;

public abstract class DispatchHelper {
	public static boolean isDispatchable(EventTopic eventTopic, TopicGroup topicGroup){
		boolean retval = false;
		TopicGroup tg = TopicGroups.AllTopics;
		if(topicGroup!=null && !tg.equals(topicGroup)){
			tg = topicGroup;
		}
		retval = TopicGroups.AllTopics.equals(tg);
		if(!retval){
			List<EventTopic> topicList = tg.getTopicList();
			for (EventTopic topic : topicList) {
				if(!retval && eventTopic != null){
					retval = eventTopic.equals(topic);
				}
			}
		}
		return retval;
	}
}
