package mtcls.client.controller.app;

import mtcls.client.controller.view.DisplayDelegate;
import mtcls.client.controller.view.LoadingViewController;
import mtcls.client.view.RootView;

/**
 * represents a view controller that has a "display" delegate
 * @author josgraha
 *
 */
public interface RootViewController extends LoadingViewController, DisplayDelegate {
	public RootView getRootView();
}
