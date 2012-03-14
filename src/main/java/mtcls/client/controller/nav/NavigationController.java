package mtcls.client.controller.nav;

import java.util.List;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEventHandler;

public interface NavigationController {
	public void initWithRootController(RootViewController rootController);
	
	public ViewController getRootController();
	
	public List<ViewController> getViewControllers();
	
	public void pushViewController(ViewController viewController);
	
	public ViewController popViewController();
	
	public void reset();
	
	public void bindRootNavigationHandlers(List<NavigationEventHandler<RootViewController>> list);
	
	public void bindRootNavigationHandler(NavigationEventHandler<RootViewController> handler);

	public void bindViewNavigationHandlers(List<NavigationEventHandler<ViewController>> list);
	
	public void bindViewNavigationHandler(NavigationEventHandler<ViewController> handler);
}
