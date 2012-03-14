package mtcls.client.controller.view;

import mtcls.client.controller.app.AppController;


public abstract class AbstractEmbeddableController extends AbstractViewController
		implements EmbeddableViewController {
	
	public AbstractEmbeddableController(){
		this(null);
	}
	
	public AbstractEmbeddableController(ViewController parent){
		this(null, parent);
	}
	
	public AbstractEmbeddableController(AppController appController, ViewController parent){
		super(appController, parent);
	}
	
	
	
	public abstract void attachToParent(ViewController parent);
}
