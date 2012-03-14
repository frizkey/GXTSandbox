package mtcls.client.registry;

import java.util.List;
import java.util.Map;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEventHandler;
import mtcls.common.model.type.KeyTypeMap;

import com.google.gwt.event.shared.EventBus;

public interface ComponentRegistry {
	public KeyTypeMap<ViewController> getTypeMap();
	public EventBus getSharedEventBus();
	public Map<String, List<ViewController>> getChildTypeMap();
	public List<NavigationEventHandler<RootViewController>> getRootNavigationHandlers();
	public List<NavigationEventHandler<ViewController>> getViewNavigationHandlers();
	public RemoteCallController getRemoteCallController();
	public CustomRegistry<?> getCustomRegistry(String registryName);
}
