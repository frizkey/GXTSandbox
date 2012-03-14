package mtcls.client.event.nav;

import mtcls.client.controller.app.RootViewController;
import mtcls.common.event.TopicGroup;
import mtcls.common.event.nav.NavigationTopicGroups;

public class RootViewNavigationHandler extends AbstractNavigationEventHandler<RootViewController>
	implements RootNavigationEventHandler{

	@Override
	protected TopicGroup createTopicGroup(){
		TopicGroup retval = NavigationTopicGroups.NavigateRootView;
		return retval;
	}
	
	@Override
	public void onNavigation(NavigationEvent<RootViewController> event) {
		RootViewController controller = event.getViewController();
		controller.load();
		controller.display();
	}

}
