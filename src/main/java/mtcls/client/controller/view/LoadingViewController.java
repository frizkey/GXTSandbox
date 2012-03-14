package mtcls.client.controller.view;

/**
 * represents a view controller that has a "load" delegate
 * @author josgraha
 *
 */
public interface LoadingViewController extends ViewController {
	
	public void loadChildren();
	
	public void load();
}
