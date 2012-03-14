package mtcls.client.gxt.view;

import mtcls.client.view.HasParentView;

import com.extjs.gxt.ui.client.widget.layout.LayoutData;

public interface LayoutAwareView extends HasParentView{
	public LayoutData getLayoutData();
}
