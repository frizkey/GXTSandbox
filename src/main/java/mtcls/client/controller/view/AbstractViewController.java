package mtcls.client.controller.view;

import java.util.List;

import mtcls.client.controller.app.AppController;
import mtcls.client.controller.app.HasAppController;
import mtcls.client.view.View;
import mtcls.common.model.AppModule;
import mtcls.common.model.AppNamespace;
import mtcls.common.model.HasMetadata;
import mtcls.common.registry.AppMetadataRegistry;
import mtcls.common.registry.HasMetadataRegistry;

public class AbstractViewController implements 
		LoadingViewController, 
		HasAppController, 
		HasMetadataRegistry, 
		HasMetadata{
	protected AppController appController;
	protected String name;
	protected ViewController parent;
	protected View view;
	protected List<ViewController> childControllers;
	protected AppNamespace namespace;
	protected AppModule module;

	public AbstractViewController(){
		this(null, null);
	}
	
	public AbstractViewController(AppController appController, ViewController parent){
		setAppController(appController);
		setParent(parent);
	}
	
	public AbstractViewController(ViewController parent){
		this(null, parent);
	}
	
	protected AppController createAppController(){
		return AppController.get();
	}
	
	@Override
	public void loadChildren(){
		if(getChildControllers()==null || getChildControllers().size()<=0){
			return;
		}
		List<ViewController> childControllers = getChildControllers();
		for (ViewController viewController : childControllers) {
			if(viewController instanceof LoadingViewController){
				LoadingViewController loadable = (LoadingViewController)viewController;
				loadable.load();
				loadable.loadChildren();
			}
		}
	}
	
	/**
	 * invoked by loadChildren
	 */
	@Override
	public void load(){
		
	}
	
	protected AppNamespace createNamespace(){
		return null;
	}
	
	protected AppModule createModule(){
		return null;
	}
	
//	protected abstract <V> V createView();
	protected View createView(){
		return null;
	}
	
	public View getParentView(){
		View retval = null;
		if(getParent() == null){
			return retval;
		}
		retval = getParent().getView();
		return retval;
	}
	
	/**
	 * @return the parent
	 */
	@Override
	public ViewController getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	@Override
	public void setParent(ViewController parent) {
		this.parent = parent;
	}

	/**
	 * @return the view
	 */
	@Override
	public View getView() {
		if(view == null){
			view = createView();
		}
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the childControllers
	 */
	@Override
	public List<ViewController> getChildControllers() {
		return childControllers;
	}

	/**
	 * @param childControllers the childControllers to set
	 */
	@Override
	public void setChildControllers(List<ViewController> childControllers) {
		this.childControllers = childControllers;
	}

	@Override
	public boolean isLeaf() {
		return getChildControllers()==null;
	}

	public AppController getAppController() {
		if(appController == null){
			appController = createAppController();
		}
		return appController;
	}

	public void setAppController(AppController appController) {
		this.appController = appController;
	}
	
	public AppMetadataRegistry getAppMetadataRegistry(){
		return getAppController().getAppRegistry().getAppMetadataRegistry();
	}

	public AppNamespace getNamespace() {
		if(namespace == null){
			namespace = createNamespace();
		}
		return namespace;
	}

	public void setNamespace(AppNamespace namespace) {
		this.namespace = namespace;
	}

	public AppModule getModule() {
		if(module == null){
			module = createModule();
		}
		return module;
	}

	public void setModule(AppModule module) {
		this.module = module;
	}
}
