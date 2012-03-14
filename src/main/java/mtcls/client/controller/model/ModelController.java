package mtcls.client.controller.model;

import java.util.Map;

public interface ModelController {
	
	public Map<String, Object> getModelRegistry();
	
	public <M> M getModel(String serviceName);
	
}
