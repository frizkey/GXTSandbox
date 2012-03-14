package mtcls.client.event.remote;

import mtcls.client.event.TopicGroupEventHandler;


public interface RemoteCallEventHandler<T> extends TopicGroupEventHandler {
	public void onRemoteCall(RemoteCallEvent<T> event);
}
