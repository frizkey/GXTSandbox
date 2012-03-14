package mtcls.client.registry;

import java.util.List;
import java.util.Map;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.model.ModelController;
import mtcls.client.controller.nav.NavigationController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEventHandler;
import mtcls.client.view.RootView;
import mtcls.common.registry.AppMetadataRegistry;

import com.extjs.gxt.ui.client.state.StateManager;

public interface AppRegistry {
	
	public List<NavigationEventHandler<RootViewController>> getRootNavigationHandlers();
	
	public List<NavigationEventHandler<ViewController>> getViewNavigationHandlers();
	
	public String getRootParentElemId();
	
	public RootView getRootView();
	
	public RootViewController getRootViewController();
	
	public NavigationController getNavigationController();

	public Map<String, ViewController> getViewControllerMap();
		
	public <C extends ViewController> C getViewController(String controllerName);
	
	public RemoteCallController getRemoteCallController();
	
	public AppMetadataRegistry getAppMetadataRegistry();
	
	public CustomRegistry<?> getCustomRegistry(String registryName);
	
	public StateManager getStateManager();
	
	public ModelController getModelController();
}
