package mtcls.client.gxt.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class DefaultFieldController<M extends ModelData> implements FieldController<M> {
	protected SortedMap<String, Field<?>> fieldMap;
	protected FormPanel view;
	protected FormBinding formBinding;
	protected Store<M> bindingStore;
	
	public DefaultFieldController(FormPanel view){
		setView(view);
	}
	
	public DefaultFieldController(){
		this(null);
	}
	
	protected SortedMap<String, Field<?>> createFieldMap(){
		SortedMap<String, Field<?>> retval = new TreeMap<String, Field<?>>();
		return retval;
	}
	
	protected FormPanel createView(){
		return new FormPanel();
	}
	
	@SuppressWarnings("unchecked")
	protected Store<M> createBindingStore(){
		Store<M> retval = getFormBinding().getStore();
		return retval;
	}
	
	protected FormBinding createFormBinding(){
		FormBinding retval = new FormBinding(getView());
		return retval;
	}

	/**
	 * @return the fieldMap
	 */
	public SortedMap<String, Field<?>> getFieldMap() {
		if(fieldMap == null){
			fieldMap = createFieldMap();
		}
		return fieldMap;
	}

	/**
	 * @param fieldMap the fieldMap to set
	 */
	public void setFieldMap(SortedMap<String, Field<?>> fieldMap) {
		this.fieldMap = fieldMap;
	}

	/**
	 * @return the view
	 */
	public FormPanel getView() {
		if(view == null){
			view = createView();
		}
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(FormPanel view) {
		this.view = view;
	}

	/**
	 * @return the formBinding
	 */
	public FormBinding getFormBinding() {
		if(formBinding==null){
			formBinding = createFormBinding();
		}
		return formBinding;
	}

	/**
	 * @param formBinding the formBinding to set
	 */
	public void setFormBinding(FormBinding formBinding) {
		this.formBinding = formBinding;
	}

	/**
	 * @return the bindingStore
	 */
	public Store<M> getBindingStore() {
		if(bindingStore == null){
			bindingStore = createBindingStore();
		}
		return bindingStore;
	}

	/**
	 * @param bindingStore the bindingStore to set
	 */
	public void setBindingStore(Store<M> bindingStore) {
		this.bindingStore = bindingStore;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> Field<D> getField(int index) {
		List<Field<?>> fields = new ArrayList<Field<?>>(getFieldMap().values());
		Field<D> retval = (Field<D>) fields.get(index);
		return retval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> Field<D> getField(String key) {
		return (Field<D>) getFieldMap().get(key);
	}
	
}
