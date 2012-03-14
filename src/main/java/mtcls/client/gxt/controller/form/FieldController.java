package mtcls.client.gxt.controller.form;

import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public interface FieldController<M extends ModelData> {
	
	public <D> Field<D> getField(int index);
	
	public <D> Field<D> getField(String key);
	
	public FormBinding getFormBinding();
	
	public Store<M> getBindingStore();
	
	public FormPanel getView();
}
