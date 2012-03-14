package mtcls.client.model.nav.types;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.nav.NavigationEventHandler;

import com.google.gwt.event.shared.GwtEvent.Type;

public abstract class NavigationEventTypes {
	public static final Type<NavigationEventHandler<ViewController>> ViewNavigationType = new Type<NavigationEventHandler<ViewController>>();
	public static final Type<NavigationEventHandler<RootViewController>> RootNavigationType = new Type<NavigationEventHandler<RootViewController>>();
}
