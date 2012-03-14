package mtcls.client.event.remote;

import mtcls.client.controller.app.AppController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.registry.AppRegistry;
import mtcls.client.rpc.RemoteCallConstants;

import com.google.gwt.event.shared.EventBus;

public class AbstractRemoteCallAdapter<T> implements HasRemoteCall<T> {
	protected String remoteCallName;
	protected RemoteCallController remoteCallController;
	protected AppController appController;
	protected EventBus eventBus;
	protected Integer retryCount;
	
	public AbstractRemoteCallAdapter(){
		
	}
	
	protected Integer createRetryCount(){
		return Integer.valueOf(RemoteCallConstants.RETRY_NONE);
	}
	
	protected String createRemoteCallName(){
		return null;
	}
	
	@Override
	public void bind(RemoteCallEventHandler<T> handler){
		getRemoteCallController().bind(getRemoteCallName(), handler);
	}
	
	@Override
	public void invoke(Object config){
		getRemoteCallController().invoke(getRemoteCallName(), config);
	}
	
	@Override
	public void invoke(Object config, int retryCount){
		getRemoteCallController().invoke(getRemoteCallName(), config, retryCount);
	}
	
	public AppController getAppController(){
		if(appController == null){
			appController = AppController.get();
		}
		return appController;
	}
	
	public AppRegistry getAppRegistry(){
		return getAppController().getAppRegistry();
	}

	/**
	 * @return the remoteCallController
	 */
	public RemoteCallController getRemoteCallController() {
		if(remoteCallController == null){
			remoteCallController = getAppRegistry().getRemoteCallController();
		}
		return remoteCallController;
	}
	/**
	 * @param remoteCallController the remoteCallController to set
	 */
	public void setRemoteCallController(RemoteCallController remoteCallController) {
		this.remoteCallController = remoteCallController;
	}
	
	/**
	 * @return the remoteCallName
	 */
	public String getRemoteCallName() {
		if(remoteCallName == null){
			remoteCallName = createRemoteCallName();
		}
		return remoteCallName;
	}

	/**
	 * @param remoteCallName the remoteCallName to set
	 */
	public void setRemoteCallName(String remoteCallName) {
		this.remoteCallName = remoteCallName;
	}

	/**
	 * @return the eventBus
	 */
	public EventBus getEventBus() {
		if(eventBus == null){
			eventBus = getAppController().getEventBus();
		}
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
		
//	public RemoteCall<T> getRemoteCall(){
//		return getRemoteCallController().getRemoteCall(getRemoteCallName());
//	}

	/**
	 * @return the retryCount
	 */
	public int getRetryCount() {
		if(retryCount==null){
			retryCount = createRetryCount();
		}
		return retryCount.intValue();
	}

	/**
	 * @param retryCount the retryCount to set
	 */
	public void setRetryCount(int retryCount) {
		this.retryCount = Integer.valueOf(retryCount);
	}


}
