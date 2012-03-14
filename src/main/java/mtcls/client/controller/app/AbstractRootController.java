package mtcls.client.controller.app;

import mtcls.client.controller.nav.NavigationController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.gxt.controller.AbstractLayoutAwareViewController;
import mtcls.client.registry.AppRegistry;
import mtcls.client.view.RootView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;

/**
 * represents the root controller of your application
 * although there is an <code>AppController</code> singleton class 
 * it is mostly a means to get to the <code>AppRegistry</code> this instance will contain
 * all child <code>ViewController</code> instances.  
 * Expose what you need in your <code>RootViewController</code> instance.
 * @author josgraha
 *
 */
public class AbstractRootController 
	extends AbstractLayoutAwareViewController
		implements RootViewController{
//	protected AppController appController;
	protected RootView rootView;
	public AbstractRootController(){
		this(null);
	}
	
	public AbstractRootController(AppController appController) {
		super(appController, null);
	}

	/**
	 * should never be invoked directly as this is set up in the entry point
	 * however this is left for the sake of overriding
	 */
	public void attachToParent(ViewController parent) {
		// attach children
		super.attachToParent(parent);
		RootView container = getRootView();
		Widget viewable = (Widget) getView();
		container.getPanel().add(viewable);
	}

	@Override
	public void display(){
		attachToParent(null);
		load();
		loadChildren();
	}

	@Override
	public void load() {
	}

	public EventBus getEventBus() {
		return getAppController().getEventBus();
	}
	
	public RemoteCallController getRemoteCallController(){
		return getAppRegistry().getRemoteCallController();
	}
	
	public NavigationController getNavigationController(){
		return getAppRegistry().getNavigationController();
	}
	
	public AppRegistry getAppRegistry(){
		return getAppController().getAppRegistry();
	}
	

	/**
	 * @return the rootView
	 */
	@Override
	public RootView getRootView() {
		if(rootView == null){
			rootView = getAppRegistry().getRootView();
		}
		return rootView;
	}

	/**
	 * @param rootView the rootView to set
	 */
	public void setRootView(RootView rootView) {
		this.rootView = rootView;
	}

}
