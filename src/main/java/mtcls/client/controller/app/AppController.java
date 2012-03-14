package mtcls.client.controller.app;

import mtcls.client.registry.AppRegistry;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;


public class AppController {
	private static AppController _instance;
	protected EventBus eventBus;
	protected AppRegistry appRegistry;
	
	private AppController(){
		this(null);
	}
	
	private AppController(AppRegistry appRegistry){
		setAppRegistry(appRegistry);
	}
	public static AppController get(){
		if(_instance == null){
			_instance = new AppController();
		}
		return _instance;
	}

	/**
	 * @return the eventBus
	 */
	public EventBus getEventBus() {
		if(eventBus == null){
			eventBus = new SimpleEventBus();
		}
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	/**
	 * @return the appRegistry
	 */
	public AppRegistry getAppRegistry() {
		return appRegistry;
	}

	/**
	 * @param appRegistry the appRegistry to set
	 */
	public void setAppRegistry(AppRegistry appRegistry) {
		this.appRegistry = appRegistry;
	}
}
