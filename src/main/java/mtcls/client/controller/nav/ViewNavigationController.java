package mtcls.client.controller.nav;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.helper.RegistrationHelper;
import mtcls.client.event.nav.NavigationEventHandler;
import mtcls.client.model.nav.NavigationModel;
import mtcls.client.model.nav.types.NavigationEventTypes;

import com.google.gwt.event.shared.EventBus;

public class ViewNavigationController implements NavigationController {
	protected EventBus eventBus;
	protected ViewController rootController;
	protected NavigationModel<ViewController> model;
	
	public ViewNavigationController(EventBus eventBus) {
		setEventBus(eventBus);
	}
	
	protected NavigationModel<ViewController> createModel(){
		NavigationModel<ViewController> retval = new NavigationModel<ViewController>(getEventBus());
		return retval;
	}

	@Override
	public void initWithRootController(RootViewController rootController) {
		setRootController(rootController);
		getModel().pushRoot(rootController);

	}

	@Override
	public ViewController getRootController() {
		return rootController;
	}
	/**
	 * @param rootController the rootController to set
	 */
	public void setRootController(ViewController rootController) {
		this.rootController = rootController;
	}

	@Override
	public List<ViewController> getViewControllers() {
		List<ViewController> retval = new ArrayList<ViewController>();
		Enumeration<ViewController> elements = getModel().elements();
		while(elements.hasMoreElements()){
			ViewController controller = elements.nextElement();
			retval.add(controller);
		}
		return retval;
	}

	@Override
	public void pushViewController(ViewController viewController) {
		getModel().push(viewController);
	}

	@Override
	public void reset() {
		if(getModel()!=null && 
				getModel().size()<=0){
			return;
		}
		getModel().clear();
	}
	
	@Override
	public ViewController popViewController() {
		ViewController retval = null;
		if(getModel()!=null && 
				getModel().size()>0 && 
				!getRootController().equals(getModel().peek())){
			return retval;
		}
		retval = getModel().pop();
		return retval;
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

	@Override
	public void bindRootNavigationHandlers(List<NavigationEventHandler<RootViewController>> handlers) {
//		for (NavigationEventHandler navigationEventHandler : list) {
//			HandlerRegistration registration = getEventBus().addHandler(NavigationEvent.TYPE, navigationEventHandler);
//			navigationEventHandler.setRegistration(registration);
//		}
		if(eventBus == null){
			return;
		}
		if(handlers == null){
			return;
		}
		RegistrationHelper.registerRootNavigationHandlers(this, getEventBus(), NavigationEventTypes.RootNavigationType, handlers);
	}

	@Override
	public void bindRootNavigationHandler(NavigationEventHandler<RootViewController> handler) {
		if(eventBus == null){
			return;
		}
		if(handler == null){
			return;
		}		
		RegistrationHelper.registerRootNavigationHandler(this, getEventBus(), NavigationEventTypes.RootNavigationType, handler);
	}

	@Override
	public void bindViewNavigationHandlers(
			List<NavigationEventHandler<ViewController>> handlers) {
		if(eventBus == null){
			return;
		}
		if(handlers == null){
			return;
		}
		RegistrationHelper.registerViewNavigationHandlers(this, getEventBus(), NavigationEventTypes.ViewNavigationType, handlers);
	}

	@Override
	public void bindViewNavigationHandler(
			NavigationEventHandler<ViewController> handler) {
		if(eventBus == null){
			return;
		}
		if(handler == null){
			return;
		}
		RegistrationHelper.registerViewNavigationHandler(this, getEventBus(), NavigationEventTypes.ViewNavigationType, handler);
	}

	/**
	 * @return the model
	 */
	public NavigationModel<ViewController> getModel() {
		if(model == null){
			model = createModel();
		}
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(NavigationModel<ViewController> model) {
		this.model = model;
	}

}
