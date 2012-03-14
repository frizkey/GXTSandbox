package mtcls.client.registry;

import java.util.List;
import java.util.Map;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.model.EventBusModelController;
import mtcls.client.controller.model.ModelController;
import mtcls.client.controller.nav.NavigationController;
import mtcls.client.controller.nav.ViewNavigationController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEventHandler;
import mtcls.client.view.RootPanelView;
import mtcls.client.view.RootView;
import mtcls.common.registry.AppMetadataRegistry;

import com.extjs.gxt.ui.client.state.CookieProvider;
import com.extjs.gxt.ui.client.state.StateManager;
import com.google.gwt.event.shared.EventBus;

public class AbstractAppRegistry implements AppRegistry {
	protected EventBus eventBus;
	protected String rootParentElemId;
	protected RootView rootParentView;
	protected RootViewController rootViewController;
	protected Map<String, ViewController> viewControllerMap;
	protected Map<String, List<ViewController>> childControllerMap;
	protected RemoteCallController remoteCallController;
	protected NavigationController navigationController;
	protected List<NavigationEventHandler<RootViewController>> rootNavigationHandlers;
	protected List<NavigationEventHandler<ViewController>> viewNavigationHandlers;
	protected String rootControllerName;
	protected ComponentRegistry controllerRegistry;
	protected AppMetadataRegistry appMetadataRegistry;
	protected StateManager stateManager;
	protected ModelController modelController;
	
	public AbstractAppRegistry(EventBus eventBus, String rootControllerName, ComponentRegistry controllerRegistry){
		setEventBus(eventBus);
		setRootControllerName(rootControllerName);
		setControllerRegistry(controllerRegistry);
		init();
	}
	
	/**
	 * invoked immediately after constructor
	 */
	protected void init(){
		
	}
	
	protected AppMetadataRegistry createAppMetadataRegistry(){
		return null;
	}

	protected Map<String, ViewController> createViewControllerMap(){
		return getControllerRegistry().getTypeMap();
	}
	
	protected Map<String, List<ViewController>> createChildControllerMap(){
		return getControllerRegistry().getChildTypeMap();
	}
	
	protected List<NavigationEventHandler<RootViewController>> createRootNavigationHandlers(){
		return getControllerRegistry().getRootNavigationHandlers();
	}
	
	protected List<NavigationEventHandler<ViewController>> createViewNavigationHandlers(){
		return getControllerRegistry().getViewNavigationHandlers();
	}
	
	protected RemoteCallController createRemoteCallController(){
		return getControllerRegistry().getRemoteCallController();
	}
		
	protected NavigationController createNavigationController(){
		NavigationController retval = new ViewNavigationController(getEventBus());
		return retval;
	}
	
	protected ModelController createModelController(){
		ModelController retval = new EventBusModelController();
		return retval;
	}

	/**
	 * @return the viewControllerMap
	 */
	public Map<String, ViewController> getViewControllerMap() {
		if(viewControllerMap == null){
			viewControllerMap = createViewControllerMap();
		}
		return viewControllerMap;
	}


	/**
	 * @param viewControllerMap the viewControllerMap to set
	 */
	public void setViewControllerMap(
			Map<String, ViewController> viewControllerMap) {
		this.viewControllerMap = viewControllerMap;
	}

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

	/**
	 * @return the navigationController
	 */
	@Override
	public NavigationController getNavigationController() {
		if(navigationController==null){
			navigationController = createNavigationController();
		}
		return navigationController;
	}

	/**
	 * @param navigationController the navigationController to set
	 */
	public void setNavigationController(NavigationController navigationController) {
		this.navigationController = navigationController;
	}

	/**
	 * @return the rootViewController
	 */
	@Override
	public RootViewController getRootViewController() {
		if(rootViewController == null){
			rootViewController = (RootViewController)getViewControllerMap().get(getRootControllerName());
		}
		return rootViewController;
	}

	/**
	 * @param rootViewController the rootViewController to set
	 */
	public void setRootViewController(RootViewController rootViewController) {
		this.rootViewController = rootViewController;
	}

	/**
	 * @return the rootParentView
	 */
	@Override
	public RootView getRootView() {
		if(rootParentView == null){
			if(getRootParentElemId()==null || "".equals(getRootParentElemId())){
				rootParentView = RootPanelView.get();
			}else{
				rootParentView = RootPanelView.get(getRootParentElemId());
			}
		}
		return rootParentView;
	}

	/**
	 * @param rootParentView the rootParentView to set
	 */
	public void setRootParentView(RootView rootParentView) {
		this.rootParentView = rootParentView;
	}

	/**
	 * @return the rootParentElemId
	 */
	public String getRootParentElemId() {
		return rootParentElemId;
	}

	/**
	 * @param rootParentElemId the rootParentElemId to set
	 */
	public void setRootParentElemId(String rootParentElemId) {
		this.rootParentElemId = rootParentElemId;
	}

	/**
	 * @return the eventBus
	 */
	public EventBus getEventBus() {
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	/**
	 * @return the rootNavigationHandlers
	 */
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
	public List<NavigationEventHandler<ViewController>> getViewNavigationHandlers() {
		if(viewNavigationHandlers == null){
			viewNavigationHandlers = createViewNavigationHandlers();
		}
		return viewNavigationHandlers;
	}

	/**
	 * @param viewNavigationHandlers the viewNavigationHandlers to set
	 */
	public void setViewNavigationHandlers(
			List<NavigationEventHandler<ViewController>> viewNavigationHandlers) {
		this.viewNavigationHandlers = viewNavigationHandlers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C extends ViewController> C getViewController(String controllerName) {
		C retval = null;
		if(controllerName == null || getViewControllerMap()==null){
			return retval;
		}
		retval = (C)getViewControllerMap().get(controllerName);
		if(retval != null && getChildControllerMap()!=null){
			
		}
		return retval;
	}
	
	/**
	 * @return the rootControllerName
	 */
	public String getRootControllerName() {
		return rootControllerName;
	}

	/**
	 * @param rootControllerName the rootControllerName to set
	 */
	public void setRootControllerName(String rootControllerName) {
		this.rootControllerName = rootControllerName;
	}

	/**
	 * @return the controllerRegistry
	 */
	public ComponentRegistry getControllerRegistry() {
		return controllerRegistry;
	}

	/**
	 * @param controllerRegistry the controllerRegistry to set
	 */
	public void setControllerRegistry(ComponentRegistry controllerRegistry) {
		this.controllerRegistry = controllerRegistry;
	}

	/**
	 * @return the childControllerMap
	 */
	public Map<String, List<ViewController>> getChildControllerMap() {
		if(childControllerMap == null){
			childControllerMap = createChildControllerMap();
		}
		return childControllerMap;
	}

	/**
	 * @param childControllerMap the childControllerMap to set
	 */
	public void setChildControllerMap(
			Map<String, List<ViewController>> childControllerMap) {
		this.childControllerMap = childControllerMap;
	}

	public AppMetadataRegistry getAppMetadataRegistry() {
		if(appMetadataRegistry==null){
			appMetadataRegistry = createAppMetadataRegistry();
		}
		return appMetadataRegistry;
	}

	public void setAppMetadataRegistry(AppMetadataRegistry appMetadataRegistry) {
		this.appMetadataRegistry = appMetadataRegistry;
	}
	
	@Override
	public CustomRegistry<?> getCustomRegistry(String registryName){
		return getControllerRegistry().getCustomRegistry(registryName);
	}

	@Override
	public StateManager getStateManager() {
		if(stateManager == null){
			StateManager.get().setProvider(new CookieProvider("/", null, null, false));
			stateManager = StateManager.get();
		}
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public ModelController getModelController() {
		if(modelController == null){
			modelController = createModelController();
		}
		return modelController;
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}
}
