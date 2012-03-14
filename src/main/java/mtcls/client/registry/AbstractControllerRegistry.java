package mtcls.client.registry;

import java.util.List;

import mtcls.client.controller.app.AppController;
import mtcls.client.controller.view.ViewController;
import mtcls.common.registry.AbstractTypesRegistry;

import com.google.gwt.event.shared.EventBus;

public abstract class AbstractControllerRegistry extends AbstractTypesRegistry<ViewController>{
	protected AppController appController;
	protected EventBus sharedEventBus;

	public AbstractControllerRegistry(AppController appController, EventBus sharedEventBus){
		setAppController(appController);
		setSharedEventBus(sharedEventBus);
	}
	@Override
	public void afterAdd(String key, String [] childNames){
		onChildrenUpdated(key);
	}
	
	@Override
	public void afterAdd(String key, String childName){
		onChildrenUpdated(key);
	}
	
	@Override
	protected void onChildUpdated(String key, ViewController child){
		if(key == null || child == null){
			return;
		}
		ViewController parent = getTypeMap().get(key);
		child.setParent(parent);
	}
	
	protected void onChildrenUpdated(String key){
		ViewController viewController = getTypeMap().get(key);
		List<ViewController> childControllers = getChildMap().getChildren(key);
		viewController.setChildControllers(childControllers);
		if(childControllers==null || childControllers.size()<=0){
			return;
		}
		for (ViewController childController : childControllers) {
			childController.setParent(viewController);
		}
	}
	
	/**
	 * @return the sharedEventBus
	 */
	public EventBus getSharedEventBus() {
		return sharedEventBus;
	}

	/**
	 * @param sharedEventBus the sharedEventBus to set
	 */
	public void setSharedEventBus(EventBus sharedEventBus) {
		this.sharedEventBus = sharedEventBus;
	}
	
	/**
	 * @return the appController
	 */
	public AppController getAppController() {
		return appController;
	}

	/**
	 * @param appController the appController to set
	 */
	public void setAppController(AppController appController) {
		this.appController = appController;
	}
}
