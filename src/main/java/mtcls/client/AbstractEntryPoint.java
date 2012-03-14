package mtcls.client;

import mtcls.client.controller.app.AppController;
import mtcls.client.registry.AppRegistry;
import mtcls.client.registry.ComponentRegistry;
import mtcls.client.registry.DefaultAppRegistry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;

public abstract class AbstractEntryPoint implements EntryPoint {
	protected AppController appController;
	protected AppRegistry appRegistry;
	protected String rootControllerName;
	protected ComponentRegistry componentRegistry;
	
	protected abstract String createRootControllerName();
	
	protected abstract ComponentRegistry createComponentRegistry();
	
	protected AppRegistry createAppRegistry(){
		AppRegistry retval = new DefaultAppRegistry(getAppController().getEventBus(), getRootControllerName(), getComponentRegistry());
		return retval;
	}
	
	@Override
	public void onModuleLoad() {
		getAppController().setAppRegistry(getAppRegistry());
		getAppController().getAppRegistry().getNavigationController().bindRootNavigationHandlers(getAppRegistry().getRootNavigationHandlers());
		getAppController().getAppRegistry().getNavigationController().bindViewNavigationHandlers(getAppRegistry().getViewNavigationHandlers());
		getAppController().getAppRegistry().getNavigationController().initWithRootController(getAppRegistry().getRootViewController());
	}
	
	public EventBus getEventBus(){
		return getAppController().getEventBus();
	}

	/**
	 * @return the fierceAppController
	 */
	public AppController getAppController() {
		if(appController == null){
			appController = AppController.get();
		}
		return appController;
	}

	/**
	 * @param fierceAppController the fierceAppController to set
	 */
	public void setFierceAppController(AppController appController) {
		this.appController = appController;
	}

	/**
	 * @return the appRegistry
	 */
	public AppRegistry getAppRegistry() {
		if(appRegistry==null){
			appRegistry = createAppRegistry();
		}
		return appRegistry;
	}

	/**
	 * @param appRegistry the appRegistry to set
	 */
	public void setAppRegistry(AppRegistry appRegistry) {
		this.appRegistry = appRegistry;
	}

	/**
	 * @return the rootControllerName
	 */
	public String getRootControllerName() {
		if(rootControllerName == null){
			rootControllerName = createRootControllerName();
		}
		return rootControllerName;
	}

	/**
	 * @param rootControllerName the rootControllerName to set
	 */
	public void setRootControllerName(String rootControllerName) {
		this.rootControllerName = rootControllerName;
	}

	/**
	 * @return the componentRegistry
	 */
	public ComponentRegistry getComponentRegistry() {
		if(componentRegistry==null){
			componentRegistry = createComponentRegistry();
		}
		return componentRegistry;
	}

	/**
	 * @param componentRegistry the componentRegistry to set
	 */
	public void setComponentRegistry(ComponentRegistry componentRegistry) {
		this.componentRegistry = componentRegistry;
	}
}
