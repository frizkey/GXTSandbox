package mtcls.client.event.remote;

import mtcls.client.controller.remote.RemoteCallController;

import com.google.gwt.event.shared.EventBus;


public interface HasRemoteCall<T> {
	
	public String getRemoteCallName();
	
	public RemoteCallController getRemoteCallController();
	
	public void setRemoteCallController(RemoteCallController remoteCallController);
	
	public EventBus getEventBus();
	
//	public RemoteCall<T> getRemoteCall();
	
	public int getRetryCount();
	
	public void bind(RemoteCallEventHandler<T> handler);
	
	public void invoke(Object config);
	
	public void invoke(Object config, int retryCount);
}
