package mtcls.client.model.nav;

import java.util.Stack;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEvent;
import mtcls.client.event.nav.NavigationEventHandler;
import mtcls.client.model.nav.types.NavigationEventTypes;
import mtcls.common.event.EventTopic;
import mtcls.common.event.nav.NavigationTopics;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;

@SuppressWarnings("serial")
public class NavigationModel<V extends ViewController> extends Stack<V> {
	protected EventBus eventBus;
	
	public NavigationModel(EventBus eventBus){
		setEventBus(eventBus);
	}
	public RootViewController pushRoot(RootViewController view){
		return push((RootViewController)view);
	}
	
	@SuppressWarnings("unchecked")
	public RootViewController push(RootViewController view){
		fireBeforeRootNavigationEvent(view);
		RootViewController retval = (RootViewController)super.push((V) view);
		fireRootNavigationEvent(view);
		return retval;
	}
	
	@Override
	public V push(V view) {
		fireBeforeViewNavigationEvent((ViewController)view);
		V retval = super.push(view);
		fireViewNavigationEvent((ViewController)view);
		return retval;
	}
	

	protected void fireBeforeViewNavigationEvent(ViewController view){
		fireBeforeNavigationEvent(NavigationEventTypes.ViewNavigationType, view);
	}
	
	protected void fireViewNavigationEvent(ViewController view){
		fireNavigationEvent(NavigationEventTypes.ViewNavigationType, view);
	}
	
	protected void fireBeforeRootNavigationEvent(RootViewController view){
		fireBeforeNavigationEvent(NavigationEventTypes.RootNavigationType, view);
	}
		
	protected void fireRootNavigationEvent(RootViewController view){
		fireNavigationEvent(NavigationEventTypes.RootNavigationType, view);
	}
	protected void fireBeforeNavigationEvent(Type<NavigationEventHandler<RootViewController>> associatedType, RootViewController view){
		EventTopic topic = NavigationTopics.BeforeNavigateView;
		NavigationEvent<RootViewController> event = createEvent(associatedType, topic, view);
		doFire(view, event);
	}	
	protected void fireBeforeNavigationEvent(Type<NavigationEventHandler<ViewController>> associatedType, ViewController view){
		EventTopic topic = NavigationTopics.BeforeNavigateView;
		NavigationEvent<ViewController> event = createEvent(associatedType, topic, view);
		doFire(view, event);
	}
	
	protected void fireNavigationEvent(Type<NavigationEventHandler<RootViewController>> associatedType, RootViewController view){
		EventTopic topic = NavigationTopics.NavigateRoot;
		NavigationEvent<RootViewController> event = createEvent(associatedType, topic, view);
		doFire(view, event);
	}
	
	protected void fireNavigationEvent(Type<NavigationEventHandler<ViewController>> associatedType, ViewController view){
		EventTopic topic = NavigationTopics.NavigateView;
		NavigationEvent<ViewController> event = createEvent(associatedType, topic, view);
		doFire(view, event);
	}
	
	protected <C extends ViewController> void doFire(C view, NavigationEvent<C> event){
		event.setViewController(view);
		getEventBus().fireEvent(event);
	}
	
	protected <C extends ViewController> NavigationEvent<C> createEvent(Type<NavigationEventHandler<C>> associatedType, EventTopic topic, ViewController view){
		NavigationEvent<C> navEvent = new NavigationEvent<C>(associatedType, topic);
		return navEvent;
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
}
