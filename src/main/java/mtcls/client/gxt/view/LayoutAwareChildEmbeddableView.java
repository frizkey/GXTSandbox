package mtcls.client.gxt.view;

import mtcls.client.view.View;

import com.extjs.gxt.ui.client.widget.layout.LayoutData;
import com.google.gwt.user.client.ui.Widget;

public abstract class LayoutAwareChildEmbeddableView extends LayoutAwarePanelView
		implements ChildEmbeddableView {

	public LayoutAwareChildEmbeddableView(View parentView) {
		super(parentView);
	}
	
	public abstract LayoutData getChildLayoutData(View childView);

	@Override
	public void embedChild(View childView){
		LayoutData data = getChildLayoutData(childView);
		add((Widget)childView, data);
	}
	
	@Override
	public void embedChild(View childView, LayoutData layoutData){
		add((Widget)childView, layoutData);
	}

}
