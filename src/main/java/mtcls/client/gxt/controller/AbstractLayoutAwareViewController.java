package mtcls.client.gxt.controller;

import java.util.List;

import mtcls.client.controller.app.AppController;
import mtcls.client.controller.view.AbstractEmbeddableController;
import mtcls.client.controller.view.EmbeddableViewController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.gxt.view.LayoutAwareView;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.LayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class AbstractLayoutAwareViewController extends
		AbstractEmbeddableController 
		implements LayoutAwareViewController{
	
	public AbstractLayoutAwareViewController(){
		this(null);
	}
	
	public AbstractLayoutAwareViewController(ViewController parent){
		this(null, parent);
	}
	
	public AbstractLayoutAwareViewController(AppController appController, ViewController parent){
		super(appController, parent);
	}

	@Override
	public void attachToParent(ViewController parent) {
//		LayoutContainer container = parent.getView();
		List<ViewController> children = getChildControllers();
		attachChildren(this, children);
		LayoutContainer container = (LayoutContainer)getParentView();
		Widget viewable = (Widget)getView();
		if(container == null){
			return;
		}
		if(viewable != null && viewable instanceof LayoutAwareView){
				LayoutData data = getEmbeddableView().getLayoutData();
				container.add(viewable, data);
				return;
		}
		if(container != null && viewable != null){
			container.add(viewable);
		}
	}
	
	public void attachChildren(ViewController parent, List<ViewController> children){
		if(children == null || children.size()<=0){
			return;
		}
		for (ViewController viewController : children) {
			try{
				if(viewController instanceof EmbeddableViewController){
					EmbeddableViewController embeddable = (EmbeddableViewController)viewController;
					embeddable.attachToParent(parent);
				}
			}catch(Exception err){
				GWT.log("attachChildren: failed: " + err.getMessage());
				err.printStackTrace(System.err);
			}
		}
	}
	
	@Override
	public LayoutAwareView getEmbeddableView(){
		return (LayoutAwareView)getView();
	}
	
	public void setEmbeddableView(LayoutAwareView embeddableView){
		super.setView(embeddableView);
	}
}
