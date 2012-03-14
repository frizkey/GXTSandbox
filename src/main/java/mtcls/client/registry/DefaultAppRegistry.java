package mtcls.client.registry;

import com.google.gwt.event.shared.EventBus;

/**
 * demonstrates what to override to implement custom widgets in custom app
 * instance
 * 
 * @author josgraha
 * 
 */
public class DefaultAppRegistry extends AbstractAppRegistry {

	public DefaultAppRegistry(EventBus eventBus, String rootControllerName, ComponentRegistry controllerRegistry) {
		super(eventBus, rootControllerName, controllerRegistry);
	}

	@Override
	protected void init() {

	}

}
