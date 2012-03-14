package mtcls.client.gxt.event;

import java.util.ArrayList;
import java.util.List;

import mtcls.client.controller.app.AppController;
import mtcls.client.controller.remote.RemoteCallController;
import mtcls.client.data.EventBusProxy;
import mtcls.client.event.remote.HasRemoteCall;
import mtcls.client.gxt.registry.GxtCustomRegistries;
import mtcls.client.registry.CustomRegistry;
import mtcls.common.model.AppModule;
import mtcls.common.model.AppProperty;
import mtcls.common.registry.AppMetadataRegistry;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.Model;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public abstract class LocalComboListeners {
	
	public static class SelectionListener extends SelectionChangedListener<Model>{		
		protected FormPanel formPanel; 
		protected AppProperty property;
		protected ComboBox<Model> combo;
		public SelectionListener(FormPanel formPanel, AppProperty property, ComboBox<Model> combo) {
			setFormPanel(formPanel);
			setProperty(property);
			setCombo(combo);
		}

		@Override
		public void selectionChanged(SelectionChangedEvent<Model> se) {
			Model model = se.getSelectedItem();
			String valueField = getCombo().getValueField();
			Object modelVal = model.get(valueField);
			ModelData formModel = getFormPanel().getModel();
			formModel.set(valueField, modelVal);
		}

		public FormPanel getFormPanel() {
			return formPanel;
		}

		public void setFormPanel(FormPanel formPanel) {
			this.formPanel = formPanel;
		}

		public AppProperty getProperty() {
			return property;
		}

		public void setProperty(AppProperty property) {
			this.property = property;
		}

		public ComboBox<Model> getCombo() {
			return combo;
		}

		public void setCombo(ComboBox<Model> combo) {
			this.combo = combo;
		}


	}	
	
	public static class ModuleLoadAdapter {
		protected ComboBox<Model> localCombo;
		protected AppProperty property;
		protected AppModule module;
		protected AppController appController;
		protected Object config;
		protected RemoteCallController remoteCallController;
		protected ListLoader<ListLoadResult<Model>> loader;
		protected EventBusProxy<ListLoadResult<Model>> eventBusProxy;
		protected HasRemoteCall<ListLoadResult<Model>> remoteCallAdapter;
		protected DataReader<ListLoadResult<Model>> reader;
		protected ListStore<Model> store;
		protected Object fieldValue;
		
		public ModuleLoadAdapter(AppController appController, ComboBox<Model> localCombo, AppProperty property, Object fieldValue){
			setAppController(appController);
			setLocalCombo(localCombo);
			setProperty(property);
			setFieldValue(fieldValue);
		}
		
		protected Object createConfig(){
			Object retval = new Object();
			return retval;
		}
		
		protected AppModule findModule(String modulePathName){
			AppMetadataRegistry metadata = getAppController().getAppRegistry().getAppMetadataRegistry();
			AppModule retval = metadata.getModuleFromPathName(modulePathName);
			return retval;
		}
		
		protected ListLoader<ListLoadResult<Model>> createLoader(){
			ListLoader<ListLoadResult<Model>> retval = 
				new BaseListLoader<ListLoadResult<Model>>(getEventBusProxy(), 
						getReader());
			return retval;
			
		}
		protected ListStore<Model> createStore(){
			ListStore<Model> retval = new ListStore<Model>(getLoader());
			retval.addStoreListener(new ComboStoreListener(getLocalCombo(), getProperty(), getFieldValue()));
			return retval;
		}
		
		@SuppressWarnings("unchecked")
		protected DataReader<ListLoadResult<Model>> createReader(){
			String registryName = GxtCustomRegistries.ReaderRegistry.getRegistryName();
			CustomRegistry<?> readerRegistry = 
				getAppController().getAppRegistry().getCustomRegistry(registryName);
			String remoteCallName = getModule().getRepository();
			DataReader<ListLoadResult<Model>> retval = (DataReader<ListLoadResult<Model>>)readerRegistry.getRegistered(remoteCallName);
			return retval;
		}
		
		protected EventBusProxy<ListLoadResult<Model>> createEventBusProxy(){
			EventBusProxy<ListLoadResult<Model>> retval = 
				new EventBusProxy<ListLoadResult<Model>>(getRemoteCallAdapter());
			return retval;
		}
		
		protected  HasRemoteCall<ListLoadResult<Model>> createRemoteCallAdapter(){
			String remoteCallName = getModule().getRepository();
			HasRemoteCall<ListLoadResult<Model>> retval = getRemoteCallController().getRemoteCallAdapter(remoteCallName);
			return retval;
		}
		
		public void load(){
//			String remoteCallName = getModule().getRepository();
			getLocalCombo().setStore(getStore());
			getLocalCombo().getStore().getLoader().load();
		}
	
		public ComboBox<Model> getLocalCombo() {
			return localCombo;
		}
		public void setLocalCombo(ComboBox<Model> localCombo) {
			this.localCombo = localCombo;
		}
		public AppProperty getProperty() {
			return property;
		}
		public void setProperty(AppProperty property) {
			this.property = property;
		}
		public AppController getAppController() {
			if(appController == null){
				appController = AppController.get();
			}
			return appController;
		}
		public void setAppController(AppController appController) {
			this.appController = appController;
		}
		public AppModule getModule() {
			if(module==null){
				module = findModule(getProperty().getLookupServiceName());
			}
			return module;
		}
		public void setModule(AppModule module) {
			this.module = module;
		}

		public Object getConfig() {
			if(config == null){
				config = createConfig();
			}
			return config;
		}

		public void setConfig(Object config) {
			this.config = config;
		}

		public RemoteCallController getRemoteCallController() {
			if(remoteCallController == null){
				remoteCallController = getAppController().getAppRegistry().getRemoteCallController();
			}
			return remoteCallController;
		}

		public void setRemoteCallController(RemoteCallController remoteCallController) {
			this.remoteCallController = remoteCallController;
		}

		public ListLoader<ListLoadResult<Model>> getLoader() {
			if(loader == null){
				loader = createLoader();
			}
			return loader;
		}

		public void setLoader(ListLoader<ListLoadResult<Model>> loader) {
			this.loader = loader;
		}

		public EventBusProxy<ListLoadResult<Model>> getEventBusProxy() {
			if(eventBusProxy == null){
				eventBusProxy = createEventBusProxy();
			}
			return eventBusProxy;
		}

		public void setEventBusProxy(EventBusProxy<ListLoadResult<Model>> eventBusProxy) {
			this.eventBusProxy = eventBusProxy;
		}

		public HasRemoteCall<ListLoadResult<Model>> getRemoteCallAdapter() {
			if(remoteCallAdapter == null){
				remoteCallAdapter = createRemoteCallAdapter();
			}
			return remoteCallAdapter;
		}

		public void setRemoteCallAdapter(HasRemoteCall<ListLoadResult<Model>> remoteCallAdapter) {
			this.remoteCallAdapter = remoteCallAdapter;
		}

		public DataReader<ListLoadResult<Model>> getReader() {
			if(reader == null){
				reader = createReader();
			}
			return reader;
		}

		public void setReader(DataReader<ListLoadResult<Model>> reader) {
			this.reader = reader;
		}

		public ListStore<Model> getStore() {
			if(store == null){
				store = createStore();
			}
			return store;
		}

		public void setStore(ListStore<Model> store) {
			this.store = store;
		}
		
		public Object getFieldValue() {
			return fieldValue;
		}

		public void setFieldValue(Object fieldValue) {
			this.fieldValue = fieldValue;
		}		
	}
	
	public static class ComboStoreListener  extends StoreListener<Model>{
		protected ComboBox<Model> localCombo;
		protected AppProperty property;
		protected Object fieldValue;
	
		public ComboStoreListener(ComboBox<Model> localCombo, AppProperty property, Object fieldValue){
			setLocalCombo(localCombo);
			setProperty(property);
			setFieldValue(fieldValue);
		}
		@Override  
		public void storeDataChanged(StoreEvent<Model> se) {
			if(se!=null &&
					se.getStore()!=null &&
					se.getStore().getModels()!=null && 
					se.getStore().getModels().size()>0){
				AppProperty prop = getProperty();
				String itemDesc = "";
				if(prop!=null && prop.getLabel()!=null){
					itemDesc = " " + prop.getLabel();
				}else{
					itemDesc = "n Item";
				}
				String selectText = "Select a" + itemDesc + " ...";
				getLocalCombo().setEmptyText(selectText);
				getLocalCombo().setEnabled(true);
				getLocalCombo().expand();
				if(prop!=null && getLocalCombo()!=null && getFieldValue()!=null){
					List<? extends Model> models = se.getStore().getModels();
					String valueField = getLocalCombo().getValueField();
					List<Model> selection = null;
					for (Model model : models) {
						if(model.get(valueField) != null && 
								model.get(valueField).equals(getFieldValue())){
							if(selection == null){
								selection = new ArrayList<Model>();
							}
							selection.add(model);
						}
					}
					if(selection ==null || selection.size()<=0){
						return;
					}
					getLocalCombo().setSelection(selection);
				}
				
			}
		}
		public ComboBox<Model> getLocalCombo() {
			return localCombo;
		}
		public void setLocalCombo(ComboBox<Model> localCombo) {
			this.localCombo = localCombo;
		}
		public AppProperty getProperty() {
			return property;
		}
		public void setProperty(AppProperty property) {
			this.property = property;
		}
		public Object getFieldValue() {
			return fieldValue;
		}
		public void setFieldValue(Object fieldValue) {
			this.fieldValue = fieldValue;
		}		
	}
}
