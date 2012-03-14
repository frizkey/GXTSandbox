package mtcls.client.gxt.view;

import java.util.List;

import mtcls.client.view.HasParentView;
import mtcls.client.view.View;
import mtcls.common.model.AppModule;
import mtcls.common.model.AppNamespace;
import mtcls.common.model.AppProperty;
import mtcls.common.model.HasMetadata;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class ContentPanelView extends ContentPanel implements
		HasParentView, HasMetadata{
	protected View parentView;
	protected AppNamespace namespace;
	protected AppModule module;

	public ContentPanelView(View parentView) {
		setParentView(parentView);
	}
	
	protected View createParentView(){
		return null;
	}

	@Override
	public View getParentView() {
		if(parentView == null){
			parentView = createParentView();
		}
		return parentView;
	}

	@Override
	public void setParentView(View parentView) {
		this.parentView = parentView;
	}
	public List<AppProperty> getProperties() {
		return getModule().getProperties();
	}

	public AppModule getModule() {
		return module;
	}

	public AppNamespace getNamespace() {
		if(namespace == null && getModule()!=null){
			namespace = getModule().getAppNamespace();
		}
		return namespace;
	}

	public void setNamespace(AppNamespace namespace) {
		this.namespace = namespace;
	}
}