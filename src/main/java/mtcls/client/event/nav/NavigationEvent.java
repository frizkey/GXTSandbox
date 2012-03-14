package mtcls.client.event.nav;

import mtcls.client.controller.view.ViewController;
import mtcls.client.event.AbstractTopicEvent;
import mtcls.common.event.EventTopic;


public class NavigationEvent<C extends ViewController> extends AbstractTopicEvent<NavigationEventHandler<C>> {
//	private final Type<NavigationEventHandler<C>> TYPE = new Type<NavigationEventHandler<C>>();
	protected C viewController;
	protected Type<NavigationEventHandler<C>> associatedType;
	
	public NavigationEvent(Type<NavigationEventHandler<C>> associatedType, EventTopic eventTopic) {
		super(associatedType, eventTopic, null);
	}
	
	public NavigationEvent(Type<NavigationEventHandler<C>> associatedType, EventTopic eventTopic, C viewController){
		this(associatedType, eventTopic);
		setViewController(viewController);
	}

//	@Override
//	public Type<NavigationEventHandler<C>> getAssociatedType() {
//		return TYPE;
//	}

	@Override
	protected void doDispatch(NavigationEventHandler<C> handler) {
		handler.onNavigation(this);
	}

	/**
	 * @return the viewController
	 */
	public C getViewController() {
		return viewController;
	}

	/**
	 * @param viewController the viewController to set
	 */
	public void setViewController(C viewController) {
		this.viewController = viewController;
	}

	/**
	 * @return the associatedType
	 */
	@Override
	public Type<NavigationEventHandler<C>> getAssociatedType() {
		return associatedType;
	}

	/**
	 * @param associatedType the associatedType to set
	 */
	public void setAssociatedType(Type<NavigationEventHandler<C>> associatedType) {
		this.associatedType = associatedType;
	}



}
