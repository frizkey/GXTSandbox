package mtcls.client.gxt.controller;

import mtcls.client.controller.view.EmbeddableViewController;
import mtcls.client.gxt.view.LayoutAwareView;

public interface LayoutAwareViewController extends EmbeddableViewController{
	
	public LayoutAwareView getEmbeddableView();
}
