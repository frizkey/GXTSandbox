package mtcls.client.event.nav;

import mtcls.client.controller.nav.NavigationController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.TopicGroupEventHandler;


public interface NavigationEventHandler<C extends ViewController> 
		extends TopicGroupEventHandler {
	
	public void onNavigation(NavigationEvent<C> navigationEvent);
	
	public void bind(NavigationController controller);
}
