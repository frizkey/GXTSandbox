package mtcls.client.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtcls.client.controller.app.AppController;
import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEventHandler;
import mtcls.client.event.nav.RootViewNavigationHandler;
import mtcls.common.registry.AppMetadataRegistry;

import com.google.gwt.event.shared.EventBus;

public class AbstractComponentRegistry extends AbstractControllerRegistry
		implements ComponentRegistry{
	protected List<NavigationEventHandler<RootViewController>> rootNavigationHandlers;
	protected  List<NavigationEventHandler<ViewController>> viewNavigationHandlers;
	protected Map<String, CustomRegistry<?>> customRegistryMap;
	protected RemoteCallController remoteCallController;
	
	protected RemoteCallController createRemoteCallController(){
		return null;
	}
	
	protected AppMetadataRegistry createAppMetadataRegistry(){
		return null;
	}
	
	protected Map<String, CustomRegistry<?>> createCustomRegistryMap(){
		Map<String, CustomRegistry<?>> retval = new HashMap<String, CustomRegistry<?>>();
		return retval;
	}
	
	public AbstractComponentRegistry(AppController appController, EventBus sharedEventBus) {
		super(appController, sharedEventBus);
		init();
	}
	
	public void addRootNavigationHandler(NavigationEventHandler<RootViewController> rootNavigationHandler){
		List<NavigationEventHandler<RootViewController>> rootHandlers = getRootNavigationHandlers();
		if(rootHandlers == null){
			rootHandlers = new ArrayList<NavigationEventHandler<RootViewController>>();
		}
		rootHandlers.add(rootNavigationHandler);
	}
	
	public void addViewNavigationHandler(NavigationEventHandler<ViewController> viewNavigationHandler){
		List<NavigationEventHandler<ViewController>> viewHandlers = getViewNavigationHandlers();
		if(viewHandlers == null){
			viewHandlers = new ArrayList<NavigationEventHandler<ViewController>>();
		}
		viewHandlers.add(viewNavigationHandler);
	}

	/**
	 * @return the rootNavigationHandlers
	 */
	@Override
	public List<NavigationEventHandler<RootViewController>> getRootNavigationHandlers() {
		if(rootNavigationHandlers == null){
			rootNavigationHandlers = createRootNavigationHandlers();
		}
		return rootNavigationHandlers;
	}


	/**
	 * @param rootNavigationHandlers the rootNavigationHandlers to set
	 */
	public void setRootNavigationHandlers(
			List<NavigationEventHandler<RootViewController>> rootNavigationHandlers) {
		this.rootNavigationHandlers = rootNavigationHandlers;
	}


	/**
	 * @return the viewNavigationHandlers
	 */
	@Override
	public List<NavigationEventHandler<ViewController>> getViewNavigationHandlers() {
		return viewNavigationHandlers;
	}


	/**
	 * @param viewNavigationHandlers the viewNavigationHandlers to set
	 */
	public void setViewNavigationHandlers(
			List<NavigationEventHandler<ViewController>> viewNavigationHandlers) {
		this.viewNavigationHandlers = viewNavigationHandlers;
	}


	/**
	 * @return the remoteCallController
	 */
	@Override
	public RemoteCallController getRemoteCallController() {
		if(remoteCallController == null){
			remoteCallController = createRemoteCallController();
		}
		return remoteCallController;
	}


	/**
	 * @param remoteCallController the remoteCallController to set
	 */
	public void setRemoteCallController(RemoteCallController remoteCallController) {
		this.remoteCallController = remoteCallController;
	}
	
	@Override
	public Map<String, List<ViewController>> getChildTypeMap() {
		return getChildTypeMap();
	}

	@Override
	public void init() {
		initControllers();
		initControllerChildren();
		initCustomRegistry();
	}
	
	protected List<NavigationEventHandler<RootViewController>> createRootNavigationHandlers(){
		List<NavigationEventHandler<RootViewController>> rootHandlers = 
			new ArrayList<NavigationEventHandler<RootViewController>>();
		rootHandlers.add(new RootViewNavigationHandler());
		return rootHandlers;
	}
	
	protected void initControllers(){
		
	}
	
	protected void initControllerChildren(){
		
	}
	
	protected void initCustomRegistry(){
		
	}
	public CustomRegistry<?> getCustomRegistry(String registryName){
		return getCustomRegistryMap().get(registryName);
	}
	
	public CustomRegistry<?> setCustomRegistry(String registryName, CustomRegistry<?> customRegistry){
		return getCustomRegistryMap().put(registryName, customRegistry);
	}

	public Map<String, CustomRegistry<?>> getCustomRegistryMap() {
		if(customRegistryMap==null){
			customRegistryMap = createCustomRegistryMap();
		}
		return customRegistryMap;
	}

	public void setCustomRegistryMap(
			Map<String, CustomRegistry<?>> customRegistryMap) {
		this.customRegistryMap = customRegistryMap;
	}
}
