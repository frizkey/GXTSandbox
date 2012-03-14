package mtcls.client.gxt.view;

import mtcls.client.view.View;

import com.extjs.gxt.ui.client.widget.layout.LayoutData;

public interface ChildEmbeddableView {
	public void embedChild(View childView);
	
	public void embedChild(View childView, LayoutData layoutData);
}
