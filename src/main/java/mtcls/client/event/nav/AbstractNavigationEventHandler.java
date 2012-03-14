package mtcls.client.event.nav;

import mtcls.client.controller.nav.NavigationController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.AbstractTopicGroupEventHandler;

public abstract class AbstractNavigationEventHandler<C extends ViewController> extends
		AbstractTopicGroupEventHandler implements NavigationEventHandler<C>{
	protected NavigationController controller;
	
	public AbstractNavigationEventHandler(){
	}
	
	public AbstractNavigationEventHandler(NavigationController controller){
		setController(controller);
	}

	/**
	 * @return the controller
	 */
	public NavigationController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(NavigationController controller) {
		this.controller = controller;
	}
	@Override
	public void bind(NavigationController navController){
		setController(navController);
	}
}
