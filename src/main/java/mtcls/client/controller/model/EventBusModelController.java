package mtcls.client.controller.model;

import java.util.HashMap;
import java.util.Map;

public class EventBusModelController implements ModelController{
	protected Map<String, Object> modelRegistry;

	protected Map<String, Object> createModelRegistry(){
		Map<String, Object> retval = new HashMap<String, Object>();
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <M> M getModel(String serviceName){
		M retval = null;
		try{
			retval =(M)getModelRegistry().get(serviceName);
		}catch(Exception ignored){
			ignored.printStackTrace(System.err);
		}
		return retval;
	}

	@Override
	public Map<String, Object> getModelRegistry() {
		if(modelRegistry==null){
			modelRegistry = createModelRegistry();
		}
		return modelRegistry;
	}

	public void setModelRegistry(Map<String, Object> modelRegistry) {
		this.modelRegistry = modelRegistry;
	}
	
}
