package mtcls.client.gxt.helper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mtcls.client.controller.app.AppController;
import mtcls.client.gxt.event.LocalComboListeners;
import mtcls.common.model.AppModule;
import mtcls.common.model.AppProperty;
import mtcls.common.model.app.DataTypeEnum;
import mtcls.common.model.app.FormModeEnum;

import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.Model;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

public class FormHelper {
	public static final int ROW_HEIGHT_DEFAULT = 35;
	private static Integer rowHeight;
	
	public static FormPanel createFormPanel(AppController controller, AppModule module, List<AppProperty> fields, int cols, FormModeEnum mode, Map<String, Object> valuesMap){
		return createFormPanel(controller, module, fields, cols, mode, valuesMap, null, null);
	}
	
	public static FormPanel createFormPanel(AppController controller, AppModule module, List<AppProperty> fields, int cols, FormModeEnum mode, Map<String, Object> valuesMap, Store<?> store, Model model){
		return createFormPanel(controller, module, fields, cols, mode, valuesMap, store, model, null, null, true);
	}
	
	public static FormPanel createFormPanel(AppController controller, 
			AppModule module, 
			List<AppProperty> properties, 
			int cols, 
			FormModeEnum mode, 
			Map<String, Object> valuesMap, 
			Store<?> store, 
			ModelData model, 
			FormData formData, 
			FormBinding binding, 
			boolean isBindingAllowed){
//		boolean skipBinding = (model==null);
//		FormBinding binding = null;
		if(formData==null){
//			formData =  new FormData("100%");
			formData = new FormData("-20");
		}
		if(model == null){
			model = new BaseModel();
		}
		FormPanel retval = new FormPanel(){
			public FormPanel setModelData(ModelData model){
				super.setModel(model);
				return this;
			}
		}.setModelData(model);
		retval.setStyleAttribute("backgroundColor", "transparent");
		if(binding == null){
			binding = new FormBinding(retval);
		}
		if(isBindingAllowed){
			if(store == null){
				ListStore<ModelData> listStore = new ListStore<ModelData>();
				listStore.add(model);
				store = listStore;
			}
			binding.setStore(store);
		}		
		retval.setLayout(new ColumnLayout());
//		LayoutContainer [] containers = new LayoutContainer[cols];
		int i=0;
		int rows = 0;
		LayoutContainer [] containers = initColumns(cols);
		for (AppProperty property : properties) {
			boolean isPropertyBindingSkipped = false;
			boolean isPropertySkipped = false;
			if(property.isHidden()){
				isPropertySkipped = true;
			}
			if(!isPropertySkipped && FormModeEnum.SEARCH.equals(mode) && property.isAdvancedSearch()){
				isPropertySkipped = true;
			}
			if(!isPropertySkipped && FormModeEnum.EDIT.equals(mode) && !property.isEditable()){
				isPropertySkipped = true;
			}
			if(!isPropertySkipped){
				if(i>=cols){
					i = 0;
					rows++;
				}
				if(i < cols){
					LayoutContainer container = containers[i];
					retval = addFieldToColumn(controller, 
										retval, 
										property, 
										container, 
										formData, 
										binding, 
										valuesMap,
										isBindingAllowed, 
										isPropertyBindingSkipped);
				}
//				isPropertyBindingSkipped = false;
				i++;
			}
		}
		
		for (i = 0; i < cols; i++) {
			double size = 1.0 / cols;
//			formPanel.add(columns[i], new RowData(size, 1, new Margins(0, 5, 0, 0)));
			retval.add(containers[i], new ColumnData(size));
		}
//		int formHeight = rows * getRowHeight().intValue();
//		retval.setHeight(formHeight);
		if(isBindingAllowed){
			binding.bind(model);
		}
		return retval;
	}
	
	public static LayoutContainer[] initColumns(int cols){
		LayoutContainer[] retval =  new LayoutContainer[cols];
		for(int i=0; i<cols; i++){
			LayoutContainer container = retval[i];
			if(container == null){
				container = new LayoutContainer();
				FormLayout formLayout = new FormLayout();
				formLayout.setLabelAlign(LabelAlign.TOP);
				container.setLayout(formLayout);
				container.setStyleAttribute("paddingRight", "5px");
				retval[i] = container;
			}
		}
		return retval;
	}
	
	protected static FormPanel addFieldToColumn(AppController controller, 
												FormPanel formPanel, 
												AppProperty property, 
												LayoutContainer container,  
												FormData formData, 
												FormBinding binding, 
												Map<String, Object> valuesMap, 
												boolean isBindingAllowed, 
												boolean isPropertyBindingSkipped){
		FormPanel retval = formPanel;
		String fieldName = property.getName();
		TextField<?> field = createPropertyField(controller, retval, property, valuesMap);
		if(isLookupField(property)){
			isPropertyBindingSkipped = true;
		}
		if(field != null){
			field.setName(fieldName);
			field.setFieldLabel(property.getLabel());
//					field.setAutoWidth(true);
			if(property.getWidth()==null){
				field.setAutoWidth(true);
			}else{
				field.setWidth(property.getWidth().intValue());
			}
			System.err.println("FormHelper::addFieldToColumn::binding: bindingAllowed: " + isBindingAllowed + " , propertyBindingSkipped: " + isPropertyBindingSkipped +  ", fieldName: " + fieldName);
			if(isBindingAllowed && !isPropertyBindingSkipped){
				System.err.println("FormHelper::addFieldToColumn::binding: txtField: " + field.getTitle() + ", fieldName: " + fieldName);
				binding.addFieldBinding(new FieldBinding(field, fieldName));
			}
			container.add(field, formData);
		}
		return retval;

	}	
	public static boolean isStringField(AppProperty property){
		return DataTypeEnum.STRING.getTypeName().equals(property.getDataType());
	}
	
	public static boolean isNumberField(AppProperty property){
		return DataTypeEnum.NUMBER.getTypeName().equals(property.getDataType());
	}
	
	public static boolean isDateField(AppProperty property){
		return DataTypeEnum.DATE.getTypeName().equals(property.getDataType());
	}
	
	public static boolean isLookupField(AppProperty property){
		boolean retval = property.isLookup();
		return retval;
	}
	
	public static TextField<?> createPropertyField(AppController controller, FormPanel formPanel, AppProperty property, Map<String, Object> valuesMap){
		TextField<?> retval = null;
		Object fieldValue = null;
		if(valuesMap!=null && property!=null && property.getName()!=null){
			fieldValue = valuesMap.get(property.getName());
		}
		if(property == null || 
				property.getDataType() == null || 
				property.getDisplayType() == null){
			return retval;
		}
		if(isLookupField(property)){
			retval = createLocalLookupField(controller, formPanel, property, fieldValue);
			return retval;
		}
		boolean allowBlank = !property.isRequired();
		if(isDateField(property)){
			retval = createUnvalidatedDateField(controller, formPanel, property, fieldValue, allowBlank);
			return retval;
		}
		if(isNumberField(property)){
			NumberField numberField = createUnvalidatedNumberField(controller, formPanel, property, fieldValue, allowBlank);
			if(property.getDecimalPrecision()==null){
				numberField.setAllowDecimals(false);
			}else{
				numberField.setAllowDecimals(true);
			}
			if(property.getMinValue()!=null){
				numberField.setMinValue(property.getMinValue());
			}
			if(property.getMaxValue()!=null){
				numberField.setMaxValue(property.getMaxValue());
			}
			retval = numberField;
			return retval;
		}
		if(isStringField(property)){
			retval = createUnvalidatedTextField(controller, formPanel, property, fieldValue, allowBlank);
			if(property.getMaxValue()!=null){
				retval.setMaxLength(property.getMaxValue().intValue());
			}
		}
		return retval;
	}
	
	public static ComboBox<Model> createLocalLookupField(AppController controller, FormPanel formPanel, AppProperty property, Object fieldValue) {
		ComboBox<Model> retval = new ComboBox<Model>();
//		String valueField = property.getName();
		
		retval.setFieldLabel(property.getLabel());
		retval.setEmptyText("Loading ...");
		retval.setDisplayField(property.getLookupName());
		retval.setValueField(property.getName());
		retval.setWidth(200);
		retval.setTypeAhead(true);
		retval.setTriggerAction(TriggerAction.ALL);
//		retval.addSelectionChangedListener(
//					new AdminFormListeners.AdminComboListener(this));
		retval.setEnabled(false);
//			getParent().getUniverseStore().addStoreListener(
//					new AdminFormListeners.UniverseStoreListener(this));
		LocalComboListeners.ModuleLoadAdapter loadAdapter = 
			new LocalComboListeners.ModuleLoadAdapter(controller, retval, property, fieldValue);
		LocalComboListeners.SelectionListener selectionListener = new LocalComboListeners.SelectionListener(formPanel, property, retval);
		retval.addSelectionChangedListener(selectionListener);
		loadAdapter.load();				
		
		return retval;
	}
	protected static TextField<?> createUnvalidatedDateField(AppController controller, FormPanel formPanel, AppProperty property, Object fieldValue, final boolean allowBlank){
		DateField retval = new DateField(){
			 @Override
			 protected void onFocus(ComponentEvent be) {
				 super.onFocus(be);
				 if(getAllowBlank()!=allowBlank){
					 setAllowBlank(allowBlank);
				 }
			 }
			 @Override
			  protected void onRender(Element target, int index) {
				 super.onRender(target, index);
				 clearInvalid();
			 }				
		};
		if(fieldValue != null){
			try{
				Date dateValue = (Date)fieldValue;
				retval.setValue(dateValue);
			}catch(Exception ignored){
				ignored.printStackTrace(System.err);
			}
		}		
		return retval;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static TextField<?> createUnvalidatedTextField(AppController controller, FormPanel formPanel, AppProperty property, Object fieldValue, final boolean allowBlank){
		TextField retval = new TextField(){
			 @Override
			 protected void onFocus(ComponentEvent be) {
				 super.onFocus(be);
				 if(getAllowBlank()!=allowBlank){
					 setAllowBlank(allowBlank);
				 }
			 }
			 @Override
			  protected void onRender(Element target, int index) {
				 super.onRender(target, index);
				 clearInvalid();
			 }				
		};
		if(fieldValue != null){
			retval.setValue(fieldValue);
		}
		return retval;
	}
	
	protected static NumberField createUnvalidatedNumberField(AppController controller, FormPanel formPanel, AppProperty property, Object fieldValue, final boolean allowBlank){
		NumberField retval = new NumberField(){
		 @Override
		 protected void onFocus(ComponentEvent be) {
				 super.onFocus(be);
				 if(getAllowBlank()!=allowBlank){
					 setAllowBlank(allowBlank);
				 }
			 }
			 @Override
			  protected void onRender(Element target, int index) {
				 super.onRender(target, index);
				 clearInvalid();
			 }
		};
		if(fieldValue != null){
			try{
				Number numberValue = (Number)fieldValue;
				retval.setValue(numberValue);
			}catch(Exception ignored){
				ignored.printStackTrace(System.err);
			}
		}		
		return retval;
	}
	
	public static Integer getRowHeight() {
		if(rowHeight == null){
			rowHeight = Integer.valueOf(ROW_HEIGHT_DEFAULT);
		}
		return rowHeight;
	}

	public static void setRowHeight(Integer aRowHeight) {
		rowHeight = aRowHeight;
	}	
}
