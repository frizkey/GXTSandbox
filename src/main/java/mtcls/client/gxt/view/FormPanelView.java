package mtcls.client.gxt.view;

import mtcls.client.view.HasParentView;
import mtcls.client.view.View;

import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class FormPanelView extends FormPanel implements HasParentView {
	protected View parentView;

	public FormPanelView(View parentView) {
		setParentView(parentView);
	}

	protected View createParentView() {
		return null;
	}

	@Override
	public View getParentView() {
		if (parentView == null) {
			parentView = createParentView();
		}
		return parentView;
	}

	@Override
	public void setParentView(View parentView) {
		this.parentView = parentView;
	}
}